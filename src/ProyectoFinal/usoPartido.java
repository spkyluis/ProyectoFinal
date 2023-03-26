package ProyectoFinal;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class usoPartido {
  public static void main(String[] args) {
    try {
      File resultado = new File("resultadosMod.csv");
      Scanner archivoResultado = new Scanner(resultado);
      while (archivoResultado.hasNextLine()) {
        String Partido = archivoResultado.nextLine();
        System.out.println(Partido);
      }
      archivoResultado.close();
    } catch (FileNotFoundException e) {
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
  }
}

