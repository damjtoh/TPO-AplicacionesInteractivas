package Usuarios;

public class Operador extends Rol {

	public Operador(Usuario usuario) {
		super(usuario);
	}

	@Override
	public int Identificarme() {
		return Rol.OPERADOR_ID;
	}
}
