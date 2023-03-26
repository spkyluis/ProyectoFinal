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
    	String partidoId;
    	String equipo1;
    	String goles1;
    	String equipo2;
    	String goles2;
    
    	 int cuantasLineas = 0;
         BufferedReader reader = new BufferedReader(new FileReader("resultadosMod.csv"));
         while (reader.readLine() != null) {   //va contando las líneas
             cuantasLineas++;
         }
         cuantasLineas--;   // sacamos el encabezado
         reader.close();
    	
    	
      File resultado = new File("resultadosMod.csv");
      Scanner archivoResultado = new Scanner(resultado);
      while (archivoResultado.hasNextLine()) {
        String Partido = archivoResultado.nextLine();
        
        String[] subcadenas = Partido.split(","); // separa según las comas
        
        partidoId = subcadenas[0].trim(); // .trim() elimina espacios en blanco
        equipo1 = subcadenas[1].trim();
        goles1 = subcadenas[2].trim();
        equipo2 = subcadenas[3].trim();
        goles2 = subcadenas[4].trim();
        
        
        
        System.out.println(partidoId + " " +equipo1+" "+goles1+" "+equipo2+ " "+ goles2 );
        
        System.out.println(Partido);
        
        System.out.println("El archivo tiene "+ cuantasLineas+" partidos");
        
      }
      archivoResultado.close();
    } catch (FileNotFoundException e) {
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
  }
}

