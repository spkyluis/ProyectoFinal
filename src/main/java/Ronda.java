
public class Ronda {

	private String idRonda;
	private String partido1;
	private String partido2;
	private String partido3;
	private String partido4;
	
	public Ronda(String rond,String part1, String part2, String part3, String part4) {
		
		this.idRonda=rond;
		this.partido1=part1;
		this.partido2=part2;
		this.partido3=part3;
		this.partido4=part4;
	}

	public String getPartido1() {
		return partido1;
	}

	public void setPartido1(String partido1) {
		this.partido1 = partido1;
	}

	public String getPartido2() {
		return partido2;
	}

	public void setPartido2(String partido2) {
		this.partido2 = partido2;
	}

	public String getPartido3() {
		return partido3;
	}

	public void setPartido3(String partido3) {
		this.partido3 = partido3;
	}

	public String getPartido4() {
		return partido4;
	}

	public void setPartido4(String partido4) {
		this.partido4 = partido4;
	}

	public String getIdRonda() {
		return idRonda;
	}

	public void setIdRonda(String idRonda) {
		this.idRonda = idRonda;
	}
	
		
}

