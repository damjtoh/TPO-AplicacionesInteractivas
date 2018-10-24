package Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;


public class MapperUsuario extends AdministradorPersistencia
{

	private static MapperUsuario instancia;
	
	private MapperUsuario()
	{
		
	}
	public static MapperUsuario getInstancia()
	{
		if (instancia == null)
			instancia = new MapperUsuario();
		return instancia;
	}

	public Usuario buscarUsuario(String nombreUsuario)
	{
		try
		{
			Usuario u = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from A_Interactivas_01.dbo.Usuarios where nombreUsuario = ?");		
			s.setString(1,nombreUsuario);
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				String nombre = result.getString(1);
				String email = result.getString(2);
				String password = result.getString(3);
				String nombreUsuario1 = result.getString(4);
				String domicilio = result.getString(5);
				int dni = result.getInt(6);
			    LocalDate fechaNacimiento = result.get;
				u = new Usuario(nombre, email, password, nombreUsuario1, domicilio, dni, fechaNacimiento);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return u;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
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
			s.setTimestamp(7,u.getFechaNacimiento());
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
					"set fechaNaccimiento = ?,"
					);
			s.setString(1,u.getNombre());
			s.setString(2, u.getEmail());
			s.setString(3,u.getPassword());
			s.setString(4, u.getNombreUsuario());
			s.setString(5,u.getDomicilio());
			s.setInt(6, u.getDni());
			s.setTimestamp(7,u.getFechaNacimiento());
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
