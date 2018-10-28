package Usuarios;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class SistemaUsuarios {
	
	private Usuario usuarioLogueado;
	
	private static SistemaUsuarios instancia;
	
	public static SistemaUsuarios getInstancia()
	{
		if (instancia == null)
			instancia = new SistemaUsuarios();
		return instancia;
	}
	
	public SistemaUsuarios() {
		super();
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
	
	
	public boolean login(String usuario, String password)
	{
		Usuario user = buscarUsuario(usuario);
		if(user!=null)
		{
			if(user.esPassword(password))
			{
			this.usuarioLogueado = user;
			return true;
			}
		}
		return false;
		
	}
	
	public static void AltaUsuario(String nombre, String email, String password, String nombreUsuario, String domicilio, int dni, LocalDate fechaNacimiento)
	{
		Usuario user = buscarUsuario(nombreUsuario); 
		if(user == null)
		{
			user = new Usuario(0, nombre, email, password, nombreUsuario, domicilio, dni, fechaNacimiento);
			usuarios.add(user);
		}
	}
	
	
	public Usuario getUsuarioLogueado() {
		return this.usuarioLogueado;
	}
	
	public static void ModificarUsuario(Usuario usuario, String nuevoEmail, String nuevoPassword, String nuevoDomicilio)
	{
		usuario.setEmail(nuevoEmail);
		usuario.setPassword(nuevoPassword);
		usuario.setDomicilio(nuevoDomicilio);
		MapperUsuario.update(usuario);
	}
	
	
	public static void BajaUsuarioById(Usuario usuario)
	{
		MapperUsuario.delete(usuario);
	}
	
}
