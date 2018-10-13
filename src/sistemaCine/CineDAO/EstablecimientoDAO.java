package sistemacine.CineDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.crypto.CipherInputStream;

import Persistencas.AdministradorPersistencia;
import Persistencas.PoolConnection;
import sistemacine.cinesClases.Establecimiento;
import sistemacine.cinesClases.Sala;

public class EstablecimientoDAO {

	public void insert(Object object) 
	{
		try
		{
			Establecimiento establecimiento = (Establecimiento) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("insert into establecimientos values (?,?,?)");

			query.setInt(1, establecimiento.getCuit());
			query.setString(2, establecimiento.getNombre());
			query.setString(3, establecimiento.getDomicilio());
			query.execute();
//			query = coneccion.prepareStatement("insert into establecimientos_salas values (?,?)");
//			for (String sala : establecimiento.getSalas().keySet()) {
//				query.setInt(1, establecimiento.getCuit());
//				query.setString(2, sala);
//				query.execute();
//			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		

	}

	public void update(Object object) {
		try
		{
			Establecimiento establecimiento = (Establecimiento) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("update establecimientos set nombre = ?,domicilio = ? where cuit = ?");
			query.setString(1, establecimiento.getNombre());
			query.setString(2, establecimiento.getDomicilio());
			query.setInt(3, establecimiento.getCuit());
			query.execute();
//			query = coneccion.prepareStatement("delete from establecimientos_salas where cuit = ?");
//			query.setInt(1, establecimiento.getCuit());
//			query.execute();
//			for (String sala : establecimiento.getSalas().keySet()) {
//				query.setInt(1, establecimiento.getCuit());
//				query.setString(2, sala);
//				query.execute();
//			}
//			
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
		}
		catch (Exception e)
		{
			System.out.println();
		}
	}

	public void delete(Object object) {
		
		try {
			Establecimiento establecimiento = (Establecimiento) object;
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("delete from establecimientos where cuit = ?");
			query.setInt(1, establecimiento.getCuit());
			query.execute();
//			query = coneccion.prepareStatement("delete from establecimientos_salas where cuit = ?");
//			query.setInt(1, establecimiento.getCuit());
//			query.execute();
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Establecimiento selectEstablecimieto(int cuit) {
		try {
			Connection coneccion = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement query = coneccion.prepareStatement("select * from establecimientos where cuit = ?");
			query.setInt(1, cuit);
			ResultSet rs = query.executeQuery();
			Establecimiento establecimiento = null;
			if (rs.next()) {
				establecimiento = new Establecimiento(rs.getInt(1), rs.getString(2), rs.getString(2));
			}
			PoolConnection.getPoolConnection().realeaseConnection(coneccion);
			return establecimiento;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Vector<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
}
