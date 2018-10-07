package Usuarios;

public abstract class Rol {
	public static String asCombo[] = {"Vendedor", "Cliente", "Operador", "Administrador", "Agente Comercial"};
	private Usuario usuario;
	public Rol(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}

}
