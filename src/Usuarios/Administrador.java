package Usuarios;

public class Administrador extends Rol {
	public Administrador(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		// TODO Auto-generated method stub
		return Rol.ADMINISTRADOR_ID;
	}

}
