package Usuarios;

public class Operador extends Rol {

	private String descripcion = "Operador";
	public Operador(Usuario usuario) {
		super(usuario);
	}
	
	@Override
	public int Identificarme() {
		return Rol.OPERADOR_ID;
	}
}
