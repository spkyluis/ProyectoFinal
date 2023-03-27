package ProyectoFinal;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class usoPartido {
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
    	 
         BufferedReader reader = new BufferedReader(new FileReader("resultadosMod.csv"));
         while (reader.readLine() != null) {   //va contando las líneas
             cuantasLineas++;
         }
         //cuantasLineas--;   // sacamos el encabezado
         reader.close();
         
         int cantidadParitdos=cuantasLineas;
         
         System.out.println("El archivo tiene "+ cuantasLineas+" partidos");
         
         Partido [] partidos = new Partido[cantidadParitdos];
         for (int i=0;i<partidos.length;i++) {
        	 partidos[i]=new Partido("",0,"",0);
         }
      File resultado = new File("resultadosMod.csv");
      Scanner archivoResultado = new Scanner(resultado);
      
      
      while (archivoResultado.hasNextLine()) {
        String archivoPartidos = archivoResultado.nextLine();
        
        String[] subcadenas = archivoPartidos.split(","); // separa según las comas
        
        partidoId = Integer.parseInt(subcadenas[0].trim()); // .trim() elimina espacios en blanco
        equipo1 = subcadenas[1].trim();
        goles1 = Integer.parseInt(subcadenas[2].trim());
        equipo2 = subcadenas[3].trim();
        goles2 = Integer.parseInt(subcadenas[4].trim());
        
        partidos[indiceArchivo].setEquipo1(equipo1);
        partidos[indiceArchivo].setGoles1(goles1);
        partidos[indiceArchivo].setEquipo2(equipo2);
        partidos[indiceArchivo].setGoles2(goles2);
        
        indiceArchivo++;
        //System.out.println(partidoId + " " +equipo1+" "+goles1+" "+equipo2+ " "+ goles2 );
        
        //System.out.println(archivoPartidos);
        
 
        
      }
      
      for (int i=0; i<partidos.length;i++) {
      	
    	  int nroPartido=i+1;
    	  System.out.println("Partido "+nroPartido+"\nEquipo 1: "+partidos[i].getEquipo1()+" Goles: "+partidos[i].getGoles1()+"\nEquipo 2: "+partidos[i].getEquipo2()+" Goles: "+partidos[i].getGoles2()+"\n");
      	
      }
      
      
      archivoResultado.close();
    } catch (FileNotFoundException e) {
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
    
    
    try {
    	// variables para obtener los datos
    	int partidoId;
    	String equipo1;
    	boolean gana1;
    	boolean empata;
    	boolean gana2;
    	String equipo2;
    	
  
    	 int cuantasLineas = 0;
         BufferedReader reader = new BufferedReader(new FileReader("pronostico.csv"));
         while (reader.readLine() != null) {   //va contando las líneas
             cuantasLineas++;
         }
         //cuantasLineas--;   // sacamos el encabezado
         reader.close();
         System.out.println("El archivo tiene "+ cuantasLineas+" partidos");
    	
      File resultado = new File("pronostico.csv");
      Scanner archivoResultado = new Scanner(resultado);
      while (archivoResultado.hasNextLine()) {
        String Pronostico = archivoResultado.nextLine();
        
        String[] subcadenas = Pronostico.split(","); // separa según las comas
        
        partidoId = Integer.parseInt(subcadenas[0].trim()); // .trim() elimina espacios en blanco
        equipo1 = subcadenas[1].trim();
        gana1 = Boolean.valueOf(subcadenas[2].trim());
        empata = Boolean.valueOf(subcadenas[3].trim());
        gana2 = Boolean.valueOf(subcadenas[4].trim());
        equipo2 = subcadenas[5].trim();
        
        
        
        //System.out.println(partidoId + " " +equipo1+" "+goles1+" "+equipo2+ " "+ goles2 );
        
        System.out.println(Pronostico);
        
        
        
      }
      archivoResultado.close();
    } catch (FileNotFoundException e) {
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
    
    
  
    
  }
}

