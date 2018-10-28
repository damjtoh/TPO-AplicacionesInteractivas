package Usuarios;

public class Cliente extends Rol {

	private String descripcion = "Cliente";
	public Cliente(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		return 1;
	}

}
