package Usuarios;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class SistemaUsuarios {
	protected static Vector<Usuario>usuarios;
	private static Usuario userLog;
	
	public SistemaUsuarios(Vector<Usuario> usuarios) {
		super();
		usuarios = new Vector<Usuario>();
	}
	
	public static Usuario buscarUsuario(String Usuario)
	{
		Usuario user = MapperUsuario.getByNombreUsuario(Usuario);
		if(user!=null)
		{
			return user;
		}
		return null;
	}
	
	
	public static boolean validarUsuario(String usuario, String password)
	{
		Usuario user = buscarUsuario(usuario);
		if(user!=null)
		{
			if(user.esPassword(password))
			{
			userLog = user;
			System.out.println("Encontre el usuario: "+ user.getNombre()+user.getEmail()+user.getNombreUsuario()+ user.getPassword());
			return true;
			}
		}
		return false;
		
	}
		
	
	public static void AltaUsuario(String nombre, String email, String password, String nombreUsuario, String domicilio, 
									int dni, LocalDate fechaNacimiento)
	{
		Usuario user = buscarUsuario(nombreUsuario); 
		if(user == null)
		{
			user = new Usuario(nombre, email, password, nombreUsuario, domicilio, dni, fechaNacimiento);
			usuarios.add(user);
		}
	}
	
	
	public static void ModificarUsuario(String email, String password, String domicilio)
	{
		if(userLog!=null)
		{
			userLog.setDomicilio(domicilio);
			userLog.setEmail(email);
			userLog.setPassword(password);
		}
	}
	
	
	public static void BajaUsuario(String Password)
	{
		if(userLog!= null)
		{
			usuarios.remove(userLog);
		}
	}
	
}
