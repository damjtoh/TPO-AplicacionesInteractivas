package Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Vector;


public class MapperUsuario extends AdministradorPersistencia
{
	private static Usuario parseResultSet(ResultSet result) {
		Usuario u = null;
		try {
			while (result.next())
			{
				String id = result.getString(1);
				String nombre = result.getString(2);
				String email = result.getString(3);
				String password = result.getString(4);
				String nombreUsuario1 = result.getString(5);
				//String domicilio = result.getString(6);
				int dni = Integer.parseInt(result.getString(6));
			    String fechaNacimiento = result.getString(7);
			    String domicilio = "Calle siempre viva 123";
			    u = new Usuario(nombre, email, password, nombreUsuario1, domicilio, dni, fechaNacimiento);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public static Usuario getByNombreUsuario(String nombreUsuario) {
		Usuario u = null;
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM dbo.Usuarios WHERE nombreUsuario = ?");		
			s.setString(1,nombreUsuario);
			ResultSet result = s.executeQuery();
			u = MapperUsuario.parseResultSet(result);
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	public static Usuario getById(int id)
	{
		Usuario u = null;
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("SELECT * FROM dbo.Usuarios WHERE id = ?");		
			s.setInt(1,id);
			ResultSet result = s.executeQuery();
			u = MapperUsuario.parseResultSet(result);
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public void insert(Object o) {
		try
		{
			Usuario u = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into A_Interactivas_01.dbo.Usuarios values (?,?,?,?,?,?,?)");
			s.setString(1,u.getNombre());
			s.setString(2, u.getEmail());
			s.setString(3,u.getPassword());
			s.setString(4, u.getNombreUsuario());
			s.setString(5,u.getDomicilio());
			s.setInt(6, u.getDni());
			s.setString(7,u.getFechaNacimiento().toString());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
	@Override
	public void update(Object o) {
		try
		{
			Usuario u = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update A_Interactivas_01.dbo.Usuarios " +
					"set nombre = ?," +
					"set email = ?," +
					"set password =?," +
					"set nombreUsuario =?," +
					"set domicilio =?," +
					"set dni =?," +
					"set fechaNacimiento = ?,"
					);
			s.setString(1,u.getNombre());
			s.setString(2, u.getEmail());
			s.setString(3,u.getPassword());
			s.setString(4, u.getNombreUsuario());
			s.setString(5,u.getDomicilio());
			s.setInt(6, u.getDni());
			s.setString(7,u.getFechaNacimiento().toString());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
	
	@Override
	public void delete(Object d) {
		try
		{
			Usuario u = (Usuario)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from A_Interactivas_01.dbo.Usuarios where nombreUsuario = ?");
			s.setString(1, u.getNombreUsuario());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
	
	@Override
	public Vector<Object> select(Object o) {
		return null;
	}
	
	
}
