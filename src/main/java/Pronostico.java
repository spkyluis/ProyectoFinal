

public class Pronostico {

	private String partido;
	private String resultado;
	private String jugador;
	
	public Pronostico(String part, String resul, String jug) {
		
		this.partido=part;
		this.resultado=resul;
		this.jugador=jug;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;

	}

	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

}
