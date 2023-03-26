package ProyectoFinal;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class usoPartido {
  public static void main(String[] args) {
    try {
    	// variables para obtener los datos
    	String partidoId;
    	String equipo1;
    	String goles1;
    	String equipo2;
    	String goles2;
    
      File resultado = new File("resultadosMod.csv");
      Scanner archivoResultado = new Scanner(resultado);
      while (archivoResultado.hasNextLine()) {
        String Partido = archivoResultado.nextLine();
        
        String[] subcadenas = Partido.split(","); // separa según las comas
        
        partidoId = subcadenas[0].trim(); // .trim() elimina espacios en blanco
        equipo1 = subcadenas[1].trim();
        
        System.out.println(partidoId + equipo1);
        System.out.println(Partido);
      }
      archivoResultado.close();
    } catch (FileNotFoundException e) {
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
  }
}

