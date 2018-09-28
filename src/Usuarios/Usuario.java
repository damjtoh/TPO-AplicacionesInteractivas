package Usuarios;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private String email;
	private String password;
	private String nombreUsuario;
	private String domicilio;
	private int dni;
	private LocalDate fechaNacimiento;
	private ArrayList<Rol> roles; // Falta definir la interfaz del rol 

	public Usuario(String nombre, String email, String password,
			String nombreUsuario, String domicilio, int dni,
			LocalDate fechaNacimiento, ArrayList roles) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.nombreUsuario = nombreUsuario;
		this.domicilio = domicilio;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.roles = roles;
	}

}
