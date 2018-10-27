package Usuarios;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Vector;

public class SistemaUsuarios {
	protected static Vector<Usuario>usuarios;
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
			System.out.println("Encontre el usuario: "+ user.getNombre()+user.getEmail()+user.getNombreUsuario()+ user.getPassword());
			return(user.esPassword(password));
		}
		return false;
		//return true; //hago esto para validar que funcionen las pantallas, el codigo que va es el de arriba
	}
	
	public static void AltaUsuario(String nombre, String email, String password, String nombreUsuario, String domicilio, int dni, String fechaNacimiento)
	{
		Usuario user = buscarUsuario(nombreUsuario); 
		if(user == null)
		{
			user = new Usuario(nombre, email, password, nombreUsuario, domicilio, dni, fechaNacimiento);
			usuarios.add(user);
		}
	}
	
	public static void ModificarUsuario(String nombreUsuario, String email, String password, String domicilio)
	{
		Usuario user = buscarUsuario(nombreUsuario);
		if(user!=null)
		{
			user.setDomicilio(domicilio);
			user.setEmail(email);
			user.setPassword(password);
		}
	}
	
	public static void BajaUsuario(String nombreUsuario, String Password)
	{
		boolean verifica = validarUsuario(nombreUsuario, Password);
		if(verifica!= false)
		{
			Usuario user = buscarUsuario(nombreUsuario);
			usuarios.remove(user);
		}
	}
	
	
}
