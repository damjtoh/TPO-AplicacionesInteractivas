package Usuarios;

public class Vendedor extends Rol {

	private String descripcion = "Vendedor";
	public Vendedor(Usuario usuario) {
		super(usuario);
		
	}
	@Override
	public int Identificarme() {
		return Rol.VENDEDOR_ID;
	}

}