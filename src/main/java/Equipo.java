
public class Equipo {

	private String idEquipo;
	private String nombre;
	
	public Equipo (String id, String nom) {
		
		this.idEquipo=id;
		this.nombre=nom;
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

