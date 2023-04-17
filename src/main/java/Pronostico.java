

public class Pronostico {

	//private Partido partido;
	//private String /*Equipo*/ equipo; 
	//private Equipo equipo;
	private String partido;
	private String /* Resultado*/ resultado;
	private String jugador;
	
	public Pronostico(/*Partido part,*/ /*Equipo*/ String part, String resul, String jug) {
		
		//this.partido=part;
		this.partido=part;
		this.resultado=resul;
		this.jugador=jug;
	}
/*
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
*/
	
	//public String /*Equipo*/ getEquipo() {
	//	return equipo;
	//}

	//public void setEquipo(String /*Equipo*/ equipo) {
	//	this.equipo = equipo;
	//}
	
	

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;

	}

	public String /*Resultado*/ getResultado() {
		return resultado;
	}
	
	public void setResultado(String /*Resultado*/ resultado) {
		this.resultado = resultado;
	}
	
	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	/*int puntos() {
		
		int punt=0;
		if (this.partido.resultado(this.equipo)==this.resultado) {
			
			punt=1;
			}
		return punt;
	}*/
	
}
