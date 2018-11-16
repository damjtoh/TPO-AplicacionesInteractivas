package Usuarios;

public class AgenteComercial extends Rol {

	public AgenteComercial(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		return Rol.AGENTE_COMERCIAL_ID;
	}

}
