import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class usoPartidoTest {

		
	@Test
	public void testBuscarIndicePartido() {
		Partido[] partidos=new Partido[3];
		partidos[0]=new Partido(1,"Equipo 1",0,"Equipo 2",0,1);
		partidos[1]=new Partido(2,"Equipo 3",1,"Equipo 4",0,1);
		partidos[2]=new Partido(3,"Equipo 5",2,"Equipo 6",3,1);
		
		int indice=usoPartido.buscarIndicePartido(partidos, "2");
		
		assertEquals(1,indice);
		
	}
	/*@Test
	public void testCalcularPuntaje() {
		Partido[] partidos=new Partido[3];
		partidos[0]=new Partido(1,"Equipo 1",0,"Equipo 2",0,1);
		partidos[1]=new Partido(2,"Equipo 3",1,"Equipo 4",0,1);
		partidos[2]=new Partido(3,"Equipo 5",2,"Equipo 6",3,1);
		
		Pronostico[] pronosticos=new Pronostico [2];
		pronosticos[0]=new Pronostico("1","empate","Juan");
		pronosticos[1]=new Pronostico("2","ganador1","Juan");
		
		String resultado="";
		resultado=quienGana(pronosticos[0])
		assertEquals(pronosticos[0].getResultado(),1);  // acierto empate 
		assertEquals(pronosticos[1].puntos(),1);  // acierto equipo 3 ganador
		
	}*/

	@Test
	public void testQuienGana() {
		int goles1=2;
		int goles2=3;
		String resultado="";
		
		if (goles1==goles2) {
			resultado= "empate";
		}
		else {
			if (goles1>goles2) {
				resultado= "gana1";
			}
			else {
				resultado= "gana2";
			}
		} 
		assertEquals(resultado,"gana2");
	}
	
}
