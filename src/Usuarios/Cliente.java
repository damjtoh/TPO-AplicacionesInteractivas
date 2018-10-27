package Usuarios;

public class Cliente extends Rol {

	public Cliente(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		return 1;
	}

}
