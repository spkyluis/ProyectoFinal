package ProyectoFinal;

public class Pronostico {

	private Partido partido;
	private String /*Equipo*/ equipo; 
	//private Equipo equipo;
	

	private Resultado resultado;
	
	public Pronostico(Partido part, /*Equipo*/ String equip, Resultado resul) {
		
		this.partido=part;
		this.equipo=equip;
		this.resultado=resul;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String /*Equipo*/ getEquipo() {
		return equipo;
	}

	public void setEquipo(String /*Equipo*/ equipo) {
		this.equipo = equipo;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
	int puntos() {
		
		int punt=0;
		if (this.partido.resultado(this.equipo)==this.resultado) {
			
			punt=1;
			}
		return punt;
	}
	
}
