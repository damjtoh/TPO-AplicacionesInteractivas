package Usuarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

import Persistencas.PoolConnection;


public class MapperUsuario {
	private static Rol getUserRolById(int rolId, Usuario usuario) {
		Rol rol;
		switch(rolId) {
			case 1: rol = new AgenteComercial(usuario);
				break;
			case 2: rol = new Operador(usuario);
				break;
			case 3: rol = new Administrador(usuario);
				break;
			case 4: rol = new Cliente(usuario);
				break;
			default: rol = null;
				break;
			//case 5: rol = new Vendedor(usuario);
		}
		return rol;
	}
	private static Usuario parseResultSet(ResultSet result) {
		Usuario u = null;
		ArrayList<Rol> roles = new ArrayList<Rol>();
		try {
			while (result.next())
			{
				int id = result.getInt(1);
				String nombre = result.getString(2);
				String email = result.getString(3);
				String password = result.getString(4);
				String nombreUsuario1 = result.getString(5);
				int dni = Integer.parseInt(result.getString(6));
				Date fecha = result.getDate(7);
				LocalDate fechaNacimiento = fecha.toLocalDate();
			    String domicilio = result.getString(8);
			    int rolId = result.getInt(9);
			    u = new Usuario(id, nombre, email, password, nombreUsuario1, domicilio, dni, fechaNacimiento);
			    Rol rol = MapperUsuario.getUserRolById(rolId, u);
			    roles.add(rol);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		u.setRoles(roles);
		return u;
	}
	public static Usuario getByNombreUsuario(String nombreUsuario) {
		Usuario u = null;
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("SELECT Usuarios.*, UsuarioRol.rolId\r\n" + 
					"FROM Usuarios \r\n" + 
					"INNER JOIN UsuarioRol ON Usuarios.usuarioId = UsuarioRol.usuarioId\r\n" + 
					"INNER JOIN Roles ON UsuarioRol.rolId = Roles.rolId\r\n" + 
					"WHERE nombreUsuario = ?;");		
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
			PreparedStatement s = con.prepareStatement("SELECT * FROM Usuarios WHERE id = ?");		
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

	public static void insert(Object o) {
		try
		{
			System.out.println("About to create: \n");
			Usuario u = (Usuario)o;
			System.out.println(u.toString());
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into Usuarios (nombre, email, password, nombreUsuario, dni, fechaNacimiento, domicilio) values (?,?,?,?,?,?,?)");
			s.setString(1,u.getNombre());
			s.setString(2, u.getEmail());
			s.setString(3,u.getPassword());
			s.setString(4, u.getNombreUsuario());
			s.setInt(5, u.getDni());
			LocalDate fecha = u.getFechaNacimiento();
			Date fechaNacimiento = Date.valueOf(fecha);
			s.setDate(6,fechaNacimiento);
			s.setString(7,u.getDomicilio());
			System.out.println("query: "+s.toString());
			s.execute();
			PreparedStatement sId = con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet resultId = sId.executeQuery();
			if (resultId.next()) {				
				Integer userId = resultId.getInt(1);
				//SELECT LAST_INSERT_ID();
				System.out.println("Latest user id: "+userId.toString());
				ArrayList<Integer> rolesIds = u.getRolesIds();
				System.out.println(rolesIds.stream().map(Object::toString)
                        .collect(Collectors.joining(", ")));
				for (Integer rolId : rolesIds) {
					PreparedStatement sRoles = con.prepareStatement("INSERT INTO UsuarioRol (usuarioRolId, rolId, usuarioId) VALUES (null, ?, ?);");
					sRoles.setInt(1, rolId);
					sRoles.setInt(2, userId);
					System.out.println("Roles query: "+sRoles.toString());
					sRoles.execute();
				}
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println();
		}
		
	}

	public static void update(Object o) {
		try
		{
			Usuario u = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update FROM Usuarios " +
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
			LocalDate fecha = u.getFechaNacimiento();
			Date fechaNacimiento = Date.valueOf(fecha);
			s.setDate(7,fechaNacimiento);
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
	

	public static void delete(Object d) {
		try
		{
			Usuario u = (Usuario)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from Usuarios where nombreUsuario = ?");
			s.setString(1, u.getNombreUsuario());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}
	
	public Vector<Object> select(Object o) {
		return null;
	}
	
	
}
