package Usuarios;

import java.sql.Timestamp;
import java.util.Vector;

public class SistemaUsuarios {
	protected Vector<Usuario>usuarios;
	public SistemaUsuarios(Vector<Usuario> usuarios) {
		super();
		usuarios = new Vector<Usuario>();
	}
	
	public Usuario buscarUsuario(String Usuario)
	{
		Usuario user = MapperUsuario.getInstancia().buscarUsuario(Usuario);
		if(user!=null)
		{
			usuarios.add(user);
			return user;
		}
		return null;
	}
	
	public boolean validarUsuario(String usuario, String password)
	{
		Usuario user = buscarUsuario(usuario);
		if(user!=null)
		{
			return(user.esPassword(password));
		}
		return false;
	}
	
	public void AltaUsuario(String nombre, String email, String password, String nombreUsuario, String domicilio, int dni, Timestamp fechaNacimiento)
	{
		Usuario user = buscarUsuario(nombreUsuario); 
		if(user == null)
		{
			user = new Usuario(nombre, email, password, nombreUsuario, domicilio, dni, fechaNacimiento);
			usuarios.add(user);
		}
	}
	
	public void ModificarUsuario(String nombre, String email, String password, String nombreUsuario, String domicilio, int dni, Timestamp fechaNacimiento)
	{
		Usuario user = buscarUsuario(nombreUsuario);
		if(user!=null)
		{
			user.setDomicilio(domicilio);
			user.setEmail(email);
			user.setPassword(password);
		}
	}
	
	public void BajaUsuario(String nombreUsuario)
	{
		Usuario user = buscarUsuario(nombreUsuario);
		if(user!=null)
		{
			usuarios.remove(user);
		}
	}
	
	
}
