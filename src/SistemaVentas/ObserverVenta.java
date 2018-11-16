package SistemaVentas;

public class ObserverVenta {
	private Terminal terminal;

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public ObserverVenta(Terminal terminal) {
		super();
		this.terminal = terminal;
	}

	public void NotifyTerminal() {
		terminal.setEstaNotificado(true);
	}
}
