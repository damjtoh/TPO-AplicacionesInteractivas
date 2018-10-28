package Usuarios;

public abstract class Rol {
	public static String asCombo[] = {"Vendedor", "Cliente", "Operador", "Administrador", "Agente Comercial"};
	private Usuario usuario;
	private String descripcion;
	public Rol(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public abstract int Identificarme();

}