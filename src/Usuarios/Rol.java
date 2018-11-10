package Usuarios;

public abstract class Rol {
	public static String asCombo[] = {"Vendedor", "Cliente", "Operador", "Administrador", "Agente Comercial"};
	public static int CLIENTE_ID = 1;
	public static int OPERADOR_ID = 2;
	public static int AGENTE_COMERCIAL_ID = 3;
	public static int ADMINISTRADOR_ID = 4;
	public static int VENDEDOR_ID = 5;
	private Usuario usuario;
	private String descripcion;
	public Rol(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public abstract int Identificarme();

}