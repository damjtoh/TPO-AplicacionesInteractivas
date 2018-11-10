package Usuarios;

public class AgenteComercial extends Rol {

	public AgenteComercial(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		return 3;
	}

}
