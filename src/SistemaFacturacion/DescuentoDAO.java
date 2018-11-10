package SistemaFacturacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Persistencas.PoolConnection;
import Usuarios.Usuario;

public class DescuentoDAO {

	private static DescuentoDAO instancia;

	private DescuentoDAO() {

	}

	public static DescuentoDAO getInstancia() {
		if (instancia == null)
			instancia = new DescuentoDAO();
		return instancia;
	}

	public static void deleteDescuento(int id) {
		try {
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = conection
					.prepareStatement("UPDATE Descuentos SET activo = 0 where descuentoId = ?");
			query.setInt(1, id);
			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(conection);
		} catch (Exception e) {
			System.out.println();
		}

	}

	public static void Save(Descuento descuento) {
		try {
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			if (descuento.getEstaCombo() == 1) {
				Combo c = (Combo) descuento;
				PreparedStatement query2 = conection.prepareStatement("SELECT max(comboId)+1 FROM DescuentosCombos");
				ResultSet rs = query2.executeQuery();
				rs.next();
				int id = rs.getInt(1);
				for (Descuento d : c.getDescuentos()) {
					PreparedStatement query = conection.prepareStatement("INSERT INTO Descuentos(usuarioId,fechaInicio,fechaFin,nombre,tipo,porcentaje,establecimientoCuit,activo,estaCombo) VALUES (?,?,?,?,?,?,?,?,?)");
					query.setInt(1, d.getCreadoPor().getId());
					query.setDate(2, d.GetFechaInicio());
					query.setDate(3, d.GetFechaFin());
					query.setString(4, d.getNombre());
					query.setString(5, d.GetTipo().toString());
					
					if(d.GetTipo().equals("X_PORCENTAJE_PRECIO_VENTA")){
						xPorcentajePrecioVenta j = (xPorcentajePrecioVenta) d;
						query.setFloat(6, j.getPorcentaje());
					}
					else {
						
						query.setFloat(6, 0);
					}
					query.setInt(7, d.getEstablecimientoCuit());
					query.setInt(8, d.getActivo());
					query.setInt(9, 1);
					query.executeUpdate();
					int id2 = 0;
					try (ResultSet generatedKeys = query.getGeneratedKeys()){
						if(generatedKeys.next()){
							id2 = generatedKeys.getInt(1);
						}
						else
						{
							throw new SQLException("No se obtuvo id");
						}
					}

					PreparedStatement query3 = conection.prepareStatement("INSERT INTO DescuentosCombos (comboId,descuentoId) VALUES (?,?)");
					query3.setInt(1, id);
					query3.setInt(2, id2);
					query3.execute();

				}

			} else {
				PreparedStatement query = conection.prepareStatement("INSERT INTO Descuentos(usuarioId,fechaInicio,fechaFin,nombre,tipo,porcentaje,establecimientoCuit,activo,estaCombo) VALUES (?,?,?,?,?,?,?,?,?)");
				query.setInt(1, descuento.getCreadoPor().getId());
				query.setDate(2, descuento.GetFechaInicio());
				query.setDate(3, descuento.GetFechaFin());
				query.setString(4, descuento.getNombre());
				query.setString(5, descuento.GetTipo().toString());
				
				if(descuento.GetTipo().toString().equals("X_PORCENTAJE_PRECIO_VENTA")){
					
					xPorcentajePrecioVenta j = (xPorcentajePrecioVenta) descuento;
					query.setFloat(6, j.getPorcentaje());
				}
				else {
					query.setFloat(6, 0);
				}
				query.setInt(7, descuento.getEstablecimientoCuit());
				query.setInt(8, descuento.getActivo());
				query.setInt(9, 0);
				query.execute();
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(conection);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static List<Descuento> selectAllDescuentosSinCombo() {
		try {
			Descuento desc = null;
			List<Descuento> descuentos = new ArrayList<>();
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = conection
					.prepareStatement("Select * from Descuentos where activo = 1 and estaCombo = 0");
			ResultSet result = query.executeQuery();
			while (result.next()) {
				if (result.getString(6).equals("PROMO_2x1")) {
					desc = new Promo2x1(result.getInt(1), new Usuario(1, null, null, null, null, null, 38951444, null),
							result.getDate(3), result.getDate(4), result.getString(5), result.getInt(8),
							result.getInt(9), result.getInt(10));

				} else
					desc = new xPorcentajePrecioVenta(result.getInt(1),
							new Usuario(1, null, null, null, null, null, 38951444, null), result.getDate(3),
							result.getDate(4), result.getFloat(7), result.getString(5), result.getInt(8),
							result.getInt(9), result.getInt(10));

				descuentos.add(desc);
			}
			PoolConnection.getPoolConnection().realeaseConnection(conection);
			return descuentos;
		} catch (Exception e) {

		}
		return null;
	}

	public static List<Descuento> selectAllDescuentosConCombo() {
		try {
			List<Descuento> descuentosCombo = new ArrayList<>();
			Descuento desc = null;
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = conection
					.prepareStatement("Select * from Descuentos where activo = 1 and estaCombo = 1");
			ResultSet result = query.executeQuery();
			while (result.next()) {
				int id = result.getInt(1);
				List<Descuento> descuentos = new ArrayList<>();
				PreparedStatement query2 = conection.prepareStatement(
						"SELECT * FROM Descuentos a INNER JOIN DescuentosCombos b on a.descuentoId = b.descuentoId WHERE b.comboId = ?");
				query2.setInt(1, id);
				ResultSet result2 = query2.executeQuery();
				while (result2.next()) {

					if (result2.getString(6).equals("PROMO_2x1")) {
						desc = new Promo2x1(result.getInt(1),
								new Usuario(1, null, null, null, null, null, 38951444, null), result.getDate(3),
								result.getDate(4), result.getString(5), result.getInt(8), result.getInt(9),
								result.getInt(10));

					} else
						desc = new xPorcentajePrecioVenta(result.getInt(1),
								new Usuario(1, null, null, null, null, null, 38951444, null), result.getDate(3),
								result.getDate(4), result.getFloat(7), result.getString(5), result.getInt(8),
								result.getInt(9), result.getInt(10));

					descuentos.add(desc);
				}

				descuentosCombo.add(new Combo(id, new Usuario(1, null, null, null, null, null, 38951444, null),
						result.getDate(3), result.getDate(4), result.getString(5), result.getInt(8), result.getInt(9),
						1, descuentos));
			}

			PoolConnection.getPoolConnection().realeaseConnection(conection);
			return descuentosCombo;
		} catch (Exception e) {

		}
		return null;
	}

	public static List<Descuento> selectAllDescuentos() {
		List<Descuento> descuentosTotales = new ArrayList<>();
		for (Descuento d : selectAllDescuentosConCombo()) {
			descuentosTotales.add(d);
		}
		for (Descuento d : selectAllDescuentosSinCombo()) {
			descuentosTotales.add(d);
		}
		return descuentosTotales;
	}

	public static Descuento FindByPk(int id) {
		try {
			Descuento desc = null;
			Connection conection = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = conection
					.prepareStatement("Select * from Descuentos where activo = 1 and descuentoId = ?");
			query.setInt(1, id);
			ResultSet result = query.executeQuery();
			if (result.next()) {
				if (result.getString(6).equals("PROMO_2x1")) {
					desc = new Promo2x1(result.getInt(1), new Usuario(1, null, null, null, null, null, 38951444, null),
							result.getDate(3), result.getDate(4), result.getString(5), result.getInt(8),
							result.getInt(9), result.getInt(10));

				} else
					desc = new xPorcentajePrecioVenta(result.getInt(1),
							new Usuario(1, null, null, null, null, null, 38951444, null), result.getDate(3),
							result.getDate(4), result.getFloat(7), result.getString(5), result.getInt(8),
							result.getInt(9), result.getInt(10));
			}
			PoolConnection.getPoolConnection().realeaseConnection(conection);
			return desc;
		} catch (Exception e) {

		}
		return null;
	}
}
