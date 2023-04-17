

public class Partido {
	
	private int partidoID;
	private String equipo1;
	private String equipo2;
	private int goles1;
	private int goles2;
	private int ronda;
	private String resultado; 
	
	public Partido(int partId, String equi1, int gol1, String equi2, int gol2, int rond) {
		
		this.ronda=rond;
		this.partidoID=partId;
		this.equipo1=equi1;
		this.goles1=gol1;
		this.equipo2=equi2;
		this.goles2=gol2;
		this.resultado="desconocido";
	}

	public int getPartidoID() {
		return partidoID;
	}

	public void setPartidoID(int partidoID) {
		this.partidoID = partidoID;
	}

	public String /*Equipo*/ getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String /*Equipo*/ equipo1) {
		this.equipo1 = equipo1;
	}

	public String /*Equipo*/ getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String /*Equipo*/ equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGoles1() {
		return goles1;
	}

	public void setGoles1(int goles1) {
		this.goles1 = goles1;
	}

	public int getGoles2() {
		return goles2;
	}

	public void setGoles2(int goles2) {
		this.goles2 = goles2;
	}
	
		
	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Resultado resultado(String /*Equipo*/ equipo) {
		
		// empate?
		if (this.goles1==this.goles2) {
			return Resultado.empate;
		}
		
		// resultado del equipo 1
		if (equipo/*.getNombre()*/==this.equipo1/*.getNombre()*/) {
			if (this.goles1>this.goles2) {
				return Resultado.ganador;
			}
			else {
				return Resultado.perdedor;
			}
		}
		
		//resultado del equipo 2
		if (equipo/*.getNombre()*/==this.equipo2/*.getNombre()*/) {
			if (this.goles2>this.goles1) {
				return Resultado.ganador;
			}
			else {
				return Resultado.perdedor;
			}
		}
		
		//error, el equipo no coincide
		return Resultado.desconocido;
	} 
}
