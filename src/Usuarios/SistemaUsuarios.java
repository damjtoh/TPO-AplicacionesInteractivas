package Usuarios;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

public class SistemaUsuarios {
	
	protected Vector<Usuario>usuarios;
	private Usuario usuarioLogueado;
	private static SistemaUsuarios instancia = null;
	
	public static SistemaUsuarios GetInstancia() {
		if(instancia==null)
			instancia = new SistemaUsuarios();
		return instancia;
	}
	
	private SistemaUsuarios() {
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
	
	public Usuario GetUsuarioLogueado() {
		return usuarioLogueado;
	}
	
	public boolean UsuarioLogueadoEsAgenteComercial() {
		return true;
		/*
		ArrayList<Rol> rolesUsuario = usuarioLogueado.getRoles();
		for(Rol rol : rolesUsuario) {
			if(rol instanceof AgenteComercial)
				return true;
		}
		return false;*/
	}
	
	public AgenteComercial GetAgenteComercial() {
		return new AgenteComercial(null);
		/*
		ArrayList<Rol> rolesUsuario = usuarioLogueado.getRoles();
		for(Rol rol : rolesUsuario) {
			if(rol instanceof AgenteComercial) {
				return (AgenteComercial) rol;
			}
		}
		return null;*/
	}
	
	public boolean validarUsuario(String usuario, String password)
	{
		Usuario user = buscarUsuario(usuario);
		if(user!=null)
		{
			boolean ok = user.esPassword(password);
			if(ok)
				usuarioLogueado = user;
			return ok;
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
