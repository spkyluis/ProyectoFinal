

public class Partido {
	
	private String partidoID;
	//private Equipo equipo1;
	private String equipo1;
	//private Equipo equipo2;
	private String equipo2;
	private int goles1;
	private int goles2;
	
	//public Partido(Equipo equi1, int gol1, Equipo equi2, int gol2) {
	public Partido(String equi1, int gol1, String equi2, int gol2) {
		
		this.equipo1=equi1;
		this.goles1=gol1;
		this.equipo2=equi2;
		this.goles2=gol2;
	}

	public String getPartidoID() {
		return partidoID;
	}

	public void setPartidoID(String partidoID) {
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
