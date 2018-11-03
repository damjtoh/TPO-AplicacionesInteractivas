package SistemaFacturacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Persistencas.PoolConnection;
import Usuarios.MapperUsuario;
import Usuarios.Usuario;

public class FacturacionMapper {

	public static Descuento parseResult(ResultSet result) {
		Descuento descuento = null;
		try {
			int id = result.getInt(1);
			int creadoPor = result.getInt(2);
			Date fechaInicio = result.getDate(3);
			LocalDate fechaInicioLocalDate = fechaInicio.toLocalDate();
			Date fechaFin = result.getDate(4);
			LocalDate fechaFinLocalDate = fechaFin.toLocalDate();
			String nombre = result.getString(5);
			ETipoDescuento tipo = ETipoDescuento.valueOf(result.getString(6));
			Float porcentaje = result.getFloat(7);
			int establecimientoCuit = result.getInt(8);
			int activo = result.getInt(9);
			int estaCombo = result.getInt(10);

			Usuario usuarioCreadoPor = MapperUsuario.getById(creadoPor);
			if (tipo == ETipoDescuento.PROMO_2x1) {
				descuento = (Descuento) new Promo2x1(id, usuarioCreadoPor, fechaInicioLocalDate, fechaFinLocalDate,
						nombre, establecimientoCuit, activo, estaCombo);
			} else if (tipo == ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA) {
				descuento = (Descuento) new xPorcentajePrecioVenta(id, usuarioCreadoPor, fechaInicioLocalDate,
						fechaFinLocalDate, porcentaje, nombre, establecimientoCuit, activo, estaCombo);
			} else if (tipo == ETipoDescuento.COMBO) {
				ArrayList<Descuento> descuentos = FacturacionMapper.listDescuentosByComboId(id);
				descuento = (Descuento) new Combo(id, usuarioCreadoPor, fechaInicioLocalDate,
						fechaFinLocalDate, nombre, establecimientoCuit, activo, estaCombo, descuentos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descuento;
	}
	
	public static ArrayList<Descuento> listDescuentosByComboId(int comboId) {
		ArrayList<Descuento> descuentos = new ArrayList<Descuento>();
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("SELECT Descuentos.*\n" + 
					"FROM Descuentos \n" + 
					"INNER JOIN DescuentosCombos on Descuentos.descuentoId = DescuentosCombos.descuentoId\n" + 
					"WHERE DescuentosCombos.comboId = ?;");
			s.setInt(1, comboId);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				Descuento descuento = FacturacionMapper.parseResult(result);
				descuentos.add(descuento);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descuentos;
	}

	public static ArrayList<Descuento> listDescuentosByEstablecimiento(int establecimientoCuit) {
		ArrayList<Descuento> descuentos = new ArrayList<Descuento>();
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM APIDB.Descuentos WHERE estaCombo = 0 AND establecimientoCuit = ?;");
			s.setInt(1, establecimientoCuit);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				Descuento descuento = FacturacionMapper.parseResult(result);
				descuentos.add(descuento);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return descuentos;
	}
	
	public static void crearComboDescuento(int comboId, Descuento descuento) {
		
	}
	public static void crearDescuento(Descuento descuento) {
		try {
			System.out.println("About to create: \n");
			System.out.println(descuento.toString());
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement(
					"INSERT INTO Descuentos (usuarioId, fechaInicio, fechaFin, nombre, tipo, porcentaje, establecimientoCuit, activo, estaCombo) values (?,?,?,?,?,?,?,?,?)");

			s.setInt(1, descuento.getCreadoPor().getId());
			s.setDate(2, Date.valueOf(descuento.fechaInicio));
			s.setDate(3, Date.valueOf(descuento.fechaFin));
			s.setString(4, descuento.getNombre());
			s.setString(5, String.valueOf(descuento.GetTipo()));
			if (descuento.GetTipo() == ETipoDescuento.X_PORCENTAJE_PRECIO_VENTA) {
				xPorcentajePrecioVenta xPorcentaje = (xPorcentajePrecioVenta)descuento;
				s.setFloat(6, xPorcentaje.getPorcentaje());
			}
			s.setInt(7, descuento.getEstablecimientoCuit());
			s.setInt(8, descuento.getActivo());
			s.setInt(9, descuento.getEstaCombo());
			System.out.println("query: " + s.toString());
			s.execute();
			if (descuento.GetTipo() == ETipoDescuento.COMBO) {
				Combo combo = (Combo)descuento;
				PreparedStatement sId = con.prepareStatement("SELECT LAST_INSERT_ID();");
				ResultSet resultId = sId.executeQuery();
				if (resultId.next()) {
					Integer comboId = resultId.getInt(1);
					for (Descuento _descuento: combo.getDescuentos()) {
						FacturacionMapper.crearDescuento(_descuento);
						PreparedStatement sId2 = con.prepareStatement("SELECT LAST_INSERT_ID();");
						ResultSet resultId2 = sId.executeQuery();
						if (resultId2.next()) {
							Integer subDescuentoId = resultId.getInt(1);
							PreparedStatement sDescuentoRelacion = con.prepareStatement(
									"INSERT INTO DescuentosCombos (comboId, descuentoId) values (?,?)");
							sDescuentoRelacion.setInt(1, comboId);
							sDescuentoRelacion.setInt(2, subDescuentoId);
							ResultSet resultId3 = sId.executeQuery();
							resultId3.next();
						}
					}
				}
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void activateDescuento(Descuento descuento) {
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("UPDATE Descuentos SET activo = 1 WHERE descuentoId = ?;");
			s.setInt(1, descuento.GetId());
			System.out.println("query: " + s.toString());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void desactivateDescuento(int id) {

	}

	public static void updateDescuento(Descuento descuento) {

	}

}
