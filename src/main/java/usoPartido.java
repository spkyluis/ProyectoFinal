
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
	
	public static int buscarJugador(String[] jugadores, String nombre) {
		
		int encontrado=-1; //no encontrado
		
		 for (int i = 0; i < jugadores.length; i++) {
			 
			 if (jugadores[i]!=null) {  //no puede comparar string con null
	            if (jugadores[i].equals(nombre)) {
	                encontrado = i;
	                break; // salir porque ya lo encontró
	            }
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
    	int ronda;
    
    	int cuantasLineas = 0;
    	int indiceArchivo=0;
    	 
    	int puntaje=0; 
    	
    	String jugador;
    	
    	 // cuenta las líneas del archivo
         BufferedReader reader = new BufferedReader(new FileReader("resultados.csv"));
         while (reader.readLine() != null) {  
             cuantasLineas++;   //va contando las líneas
         }
         //cuantasLineas--;   // sacamos el encabezado
         reader.close();
         
         int cantidadParitdos=cuantasLineas;
         
         Partido [] partidos = new Partido[cantidadParitdos];
         for (int i=0;i<partidos.length;i++) {
        	 partidos[i]=new Partido("","",0,"",0,0);    //inicializamos los partidos  id, equipo1, goles1, equipo2, goles2, ronda
         }
      
         
         File resultado = new File("resultados.csv");
         Scanner archivoResultado = new Scanner(resultado);
      
        while (archivoResultado.hasNextLine()) {
        
        	String archivoPartidos = archivoResultado.nextLine();
  
        	String[] subcadenas = archivoPartidos.split(","); // separa según las comas
        	
        	
        	partidos[indiceArchivo].setPartidoID(subcadenas[0].trim()); // .trim() elimina espacios en blanco
        	equipo1 = subcadenas[1].trim();
        	goles1 = Integer.parseInt(subcadenas[2].trim());
        	goles2 = Integer.parseInt(subcadenas[3].trim());
        	equipo2 = subcadenas[4].trim();
        	ronda= Integer.parseInt(subcadenas[5].trim());
        	       	
        	partidos[indiceArchivo].setRonda(ronda);
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
   	
    	//recorremos el archivo de pronósticos para saber cuántos hay
    	//a lo sumo, si todos fueran de distintas personas, sabemos ya cuántas personas hay como máximo
    	
    	cuantasLineas = 0;
        BufferedReader reader2 = new BufferedReader(new FileReader("pronostico.csv"));
        while (reader2.readLine() != null) {   //va contando las líneas
             cuantasLineas++;

        }
        //cuantasLineas--;   // sacamos el encabezado
        reader2.close();
    	
        //creamos arreglo de jugadores
        String jugadores[] = new String [cuantasLineas];
        
        //creamos arreglo de puntajes
        int puntajes[] = new int [cuantasLineas];
        
        //en jugadores[x] tenemos el nombre del jugador y en puntajes[x] tenemos el puntaje de ese jugador
        
        
      BufferedReader readerPronostico = new BufferedReader(new FileReader("pronostico.csv"));
           
      String partidoId2;
      
      int cantidadMaxJugadores=0;
      int cantidadJugadores=0;
      String pronostico;
      
      //cuantos pronósticos tiene el archivo de acá sacamos la cantidad máxima de jugadores
      while((pronostico=readerPronostico.readLine()) != null) {
    	  cantidadMaxJugadores++;
      }
      
      readerPronostico.close();
      
      BufferedReader readerPronostico2 = new BufferedReader(new FileReader("pronostico.csv"));
      
      //armar arreglo de nombres de los jugadores

       while ((pronostico = readerPronostico2.readLine()) != null) { 	  

    	String[] subcadenas = pronostico.split(";"); // separa según los punto y comas

    	if (subcadenas.length!=1) {

    	jugador = subcadenas[6].trim(); // nombre del jugador
    	
    	if (buscarJugador(jugadores, jugador)==-1) {   // no encontrado
 
    		jugadores[cantidadJugadores]=jugador;// agrego el jugador al arreglo
    		puntajes[cantidadJugadores]=0;  // pongo en 0 el puntaje del jugador

    		cantidadJugadores++;  // voy a tener en definitiva la cantidad de jugadores
    							// NO OLVIDAR QUE ES CANTIDAD TOTAL hay que usar -1 para acceder a cada jugador
    	}
    	}
    	
      }
    	
      readerPronostico2.close();
   
      
      BufferedReader readerPronostico3 = new BufferedReader(new FileReader("pronostico.csv"));
          
      while ((pronostico = readerPronostico3.readLine()) != null) {
                
        String[] subcadenas = pronostico.split(";"); // separa según los punto y comas al cambiar por comas no funciona ¯\_(ツ)_/¯
        
        if (subcadenas.length!=1) {
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
        
        jugador = subcadenas[6].trim();
        
        
        int indiceJugador=-1;
        
        int indicePartido=-1;

        indicePartido=buscarIndicePartido(partidos,partidoId2);
        
        if (indicePartido!=-1) {
        	
        	// resutltadoPartido es gana1, gana2 o empate
        	String resultadoPartido = quienGana(partidos[indicePartido].getEquipo1(),partidos[indicePartido].getGoles1(),partidos[indicePartido].getEquipo2(),partidos[indicePartido].getGoles2());
        	
        	if ((empata && resultadoPartido=="empate") || (gana1 && resultadoPartido=="equipo1") || (gana2 && resultadoPartido.equals("equipo2") )) {
        		indiceJugador=buscarJugador(jugadores, jugador);  // indiceJugador el índice donde está el jugador del pronóstico actual
        		if (indiceJugador!=-1) {
        			// encontró el jugador
        			puntajes[indiceJugador]++;
        		} // si no encontró el jugador es un pronóstico no válido (no se sabe qué jugador lo hizo) entonces no se hace nada
        		
        	}
        	
        }
       
       //volver las variables a valor inicial
       gana1=false;
       gana2=false;
       empata=false;
     
        } 
      }
      readerPronostico3.close();

      
      //muestra el puntaje
      //por ahora, el puntaje es igual a la cantidad de resultados acertados

      for (int i=0; i < cantidadJugadores; i++) {
    	  System.out.println("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+" y acertó "+puntajes[i]+" resultados." ); 
      }
         
      
    } catch (FileNotFoundException e) {     // error en el manejo de los archivos
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
    catch (NumberFormatException errorFormato) {     // error en la conversión a int de los goles (String a int)
		System.out.println("Se intentó cargar un valor de goles no permitido");
		
	}
    
  }
}

