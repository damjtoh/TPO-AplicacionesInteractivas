package Usuarios;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Usuario {
	private Integer id;
	private String nombre;
	private String email;
	private String password;
	private String nombreUsuario;
	private String domicilio;
	private int dni;
	private LocalDate fechaNacimiento;
	private boolean activo = true;
	private ArrayList<Rol> roles;

 

	public Usuario(Integer id, String nombre, String email, String password, String nombreUsuario, String domicilio, int dni, LocalDate fechaNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.nombreUsuario = nombreUsuario;
		this.domicilio = domicilio;
		this.dni = dni;
		this.fechaNacimiento = fechaNac;
		this.roles = new ArrayList<Rol>();
	}

	public Integer getId() {
		return this.id;
	}

	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getDni() {
		return dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public ArrayList<Rol> getRoles() {
		return roles;
	}
	
	public void addRol(Rol rol) {
		this.roles.add(rol);
	}

	public void setRoles(ArrayList<Rol> roles) {
		this.roles = roles;
	}
	
	//Metodos
	
	public boolean esUsuario(String Usuario)
	{
		return (Usuario == nombreUsuario);
	}

	public boolean esPassword(String Password) {
		return (Password.equals(password));
	}

	public void desactivar() {
		this.activo = false;
	}
	
	public void activar() {
		this.activo = true;
	}
	
	public void editar(String nombre, String email, String password, String domicilio) {
		
	}
	
	public ArrayList<Integer> getRolesIds() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Rol rol : this.roles) {
			ids.add(rol.Identificarme());
		};
		return ids;
	}
	
	public String toString() {
        return "ID:" + String.valueOf(this.id) + "\n"
        		+ "Nombre:" + this.nombre + "\n"
                + "Email:" + this.email + "\n"
                + "Password:" + this.password + "\n"
                + "Usuario:" + this.nombreUsuario + "\n"
                + "Domicilio:" + this.domicilio + "\n"
                + "DNI:" + String.valueOf(this.dni) + "\n"
        		+ "Fecha nac:" + String.valueOf(this.fechaNacimiento) + "\n"
        		+ "Roles:"+ this.roles.stream().map(Object::toString)
                .collect(Collectors.joining(", "))+"\n";
    }
	
}
