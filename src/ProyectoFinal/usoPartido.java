package ProyectoFinal;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class usoPartido {
	
	public static int buscarIndicePartido(Partido[] partidos, String partidoId) {
		
		int encontrado=-1; //no encontrado
		for (int i = 0; i < partidos.length; i++) {
	        if (partidos[i].getPartidoID().equals(partidoId)){
	        	encontrado= i;
	           }
	    }
		
	    return encontrado; 
	}	
	
	public static String quienGana (String equipo1,int goles1,String equipo2, int goles2) {

		if (goles1==goles2) {
			return "empate";
		}
		else {
			if (goles1>goles2) {
				return "equipo1";
			}
			else {
				return "equipo2";
			}
		} 
		
		
	}
	
  public static void main(String[] args) throws IOException {
    try {
    	// variables para obtener los datos
    	int partidoId;
    	String equipo1;
    	int goles1;
    	String equipo2;
    	int goles2;
    
    	int cuantasLineas = 0;
    	int indiceArchivo=0;
    	 
    	int puntaje=0; 
    	 
    	 
         BufferedReader reader = new BufferedReader(new FileReader("resultadosMod.csv"));
         while (reader.readLine() != null) {   //va contando las líneas
             cuantasLineas++;
         }
         //cuantasLineas--;   // sacamos el encabezado
         reader.close();
         
         int cantidadParitdos=cuantasLineas;
         
         //System.out.println("El archivo tiene "+ cuantasLineas+" partidos");
         
         Partido [] partidos = new Partido[cantidadParitdos];
         for (int i=0;i<partidos.length;i++) {
        	 partidos[i]=new Partido("",0,"",0);    //inicializamos los partidos
         }
      
         
         File resultado = new File("resultadosMod.csv");
         Scanner archivoResultado = new Scanner(resultado);
      
      
        while (archivoResultado.hasNextLine()) {
        
        	String archivoPartidos = archivoResultado.nextLine();
        
        	String[] subcadenas = archivoPartidos.split(","); // separa según las comas
        
        	partidos[indiceArchivo].setPartidoID(subcadenas[0].trim()); // .trim() elimina espacios en blanco
        	equipo1 = subcadenas[1].trim();
        	goles1 = Integer.parseInt(subcadenas[2].trim());
        	equipo2 = subcadenas[3].trim();
        	goles2 = Integer.parseInt(subcadenas[4].trim());
        
        	partidos[indiceArchivo].setEquipo1(equipo1);
        	partidos[indiceArchivo].setGoles1(goles1);
        	partidos[indiceArchivo].setEquipo2(equipo2);
        	partidos[indiceArchivo].setGoles2(goles2);
        
        	indiceArchivo++;
          
        }
      
        archivoResultado.close();
      
    	// variables para obtener los datos
    	boolean gana1=false;
    	boolean empata=false;
    	boolean gana2=false;

    	
    	Resultado res=Resultado.desconocido;
    
    	/*cuantasLineas = 0;
        BufferedReader reader2 = new BufferedReader(new FileReader("pronostico.csv"));
        while (reader2.readLine() != null) {   //va contando las líneas
             cuantasLineas++;
        }*/
         //cuantasLineas--;   // sacamos el encabezado
        //reader2.close();
         //System.out.println("El archivo tiene "+ cuantasLineas+" partidos");
    	
      File pronostico = new File("pronostico.csv");
      Scanner archivoPronostico = new Scanner(pronostico);
      
      String partidoId2;
      
      while (archivoPronostico.hasNextLine()) {
        String Pronostico = archivoPronostico.nextLine();
        
        String[] subcadenas = Pronostico.split(","); // separa según las comas
        
        partidoId2 = subcadenas[0].trim(); // .trim() elimina espacios en blanco
        equipo1 = subcadenas[1].trim();
        
        if (subcadenas[2].trim().equals("X")){
        	gana1=true;
        }
        if (subcadenas[3].trim().equals("X")) {
        	empata=true;
        }
        if (subcadenas[4].trim().equals("X")) {
        	gana2=true;
        }
        
        equipo2 = subcadenas[5].trim();
        
        int indicePartido=-1;

        indicePartido=buscarIndicePartido(partidos,partidoId2);
        
        if (indicePartido!=-1) {
        	
        	// resutltadoParitdo es gana1, gana2 o empate
        	String resultadoPartido = quienGana(partidos[indicePartido].getEquipo1(),partidos[indicePartido].getGoles1(),partidos[indicePartido].getEquipo2(),partidos[indicePartido].getGoles2());
        	//System.out.println("resultadoPartido " +resultadoPartido+ " gana1 "+ gana1 + " gana2 " + gana2 + " empata " + empata);
        	if ((empata && resultadoPartido=="empate") || (gana1 && resultadoPartido=="equipo1") || (gana2 && resultadoPartido.equals("equipo2") )) {
        		
        		puntaje=puntaje+1;
        		//System.out.println("Suma puntos");
        	}
        	
        }
       
       //volver las variables a valor inicial
       gana1=false;
       gana2=false;
       empata=false;
     
        
      }
      archivoPronostico.close();
      
      //muestra el puntaje
      System.out.println("El puntaje obtenido por el usuario es "+puntaje);
      
      
    } catch (FileNotFoundException e) {
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
    
  }
}

