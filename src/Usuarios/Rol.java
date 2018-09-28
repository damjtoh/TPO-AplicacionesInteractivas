package Usuarios;

public abstract class Rol {
	private Usuario usuario;
	public Rol(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}

}
