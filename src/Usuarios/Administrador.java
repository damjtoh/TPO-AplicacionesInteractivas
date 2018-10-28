package Usuarios;

public class Administrador extends Rol {
	private String descripcion = "Administrador";
	public Administrador(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		// TODO Auto-generated method stub
		return 4;
	}

}
