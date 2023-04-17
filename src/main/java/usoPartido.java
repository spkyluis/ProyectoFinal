
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class usoPartido {
	
	public static int buscarIndicePartido(Partido[] partidos, String partidoId) {
		
		int encontrado=-1; //no encontrado
		int partidoIdInt=Integer.parseInt(partidoId);
		for (int i = 0; i < partidos.length; i++) {
	        if (partidos[i].getPartidoID()==partidoIdInt){
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
				return "gana1";
			}
			else {
				return "gana2";
			}
		} 
		
		
	}
	
	
  public static void main(String[] args) throws IOException, SQLException {
    try {
    	// variables para obtener los datos

    
    	int cuantasLineas = 0;
    	int indiceArchivo=0;
    	 
    	int puntaje=0; 
    	
    	String jugador;
    	
    	// variables de configuración
    	int puntosGanador=0;
    	int puntosExtraRonda=0;
    	int puntosExtraFase=0;
    	String baseDatos="";
    	String configuracion;
    	
    		
    	// lee archivo de configuración y carga los valores
    	BufferedReader readerconfig = new BufferedReader(new FileReader("config.csv"));
        while ((configuracion=readerconfig.readLine()) != null) {   // lee el archivo de configuración
        	// base de datos, puntos ganador, puntos extra ronda, puntos extra fase
        	String[] subcadenas = configuracion.split(",");
        	
        	baseDatos=subcadenas[0].trim();
        	puntosGanador=Integer.parseInt(subcadenas[1].trim());
        	puntosExtraRonda=Integer.parseInt(subcadenas[2].trim());
        	puntosExtraFase=Integer.parseInt(subcadenas[3].trim());

        }
        
        readerconfig.close();
    	
    	
        
        // Establece datos de la base de datos, el usuario y la contraseña
        String usuario = "root";
        String contrasena = "";
        String consultaSQL = "";
        int cantidadRegistros=0;

        // Establece la conexión a la base de datos
        Connection conexion = DriverManager.getConnection(baseDatos, usuario, contrasena);
        
        // busca todos los partidos
        consultaSQL="SELECT * FROM partido";
        
        // Crea un objeto Statement
        Statement statement = conexion.createStatement();

        // Ejecuta la consulta y obtiene un ResultSet con los datos buscados
        ResultSet resultSet = statement.executeQuery(consultaSQL);
        
        // recorre el resultset para saber cuántos registros hay
        while (resultSet.next()) {
        	cantidadRegistros++;
        }
        System.out.println("Cantidad de partidos "+cantidadRegistros);
        
        // inicializa arreglo de partidos
        int cantidadParitdos=cantidadRegistros;
        
        Partido [] partidos = new Partido[cantidadParitdos];
        for (int i=0;i<partidos.length;i++) {
       	 partidos[i]=new Partido(0,"",0,"",0,0);    //inicializa los partidos  id, equipo1, goles1, equipo2, goles2, ronda
        }
        
        // ejecuta de nuevo la consulta 
        resultSet = statement.executeQuery(consultaSQL);
        
        // recorrer ResultSet y leer los datos
        while (resultSet.next()) {
            // leer los datos de cada columna del ResultSet
        	int partidoId = resultSet.getInt("idPartido");
            String equi1 = resultSet.getString("equipo1");
            int gol1 = resultSet.getInt("goles1");
            String equi2 = resultSet.getString("equipo2");
            int gol2 = resultSet.getInt("goles2");
            int rond = resultSet.getInt("idRonda");
            
            //guardar los datos en el arreglo
            partidos[indiceArchivo].setRonda(rond);
            partidos[indiceArchivo].setPartidoID(partidoId);
            partidos[indiceArchivo].setEquipo1(equi1);
        	partidos[indiceArchivo].setGoles1(gol1);
        	partidos[indiceArchivo].setEquipo2(equi2);
        	partidos[indiceArchivo].setGoles2(gol2);
        
        	// calcula el resultado puede ser gana1, gana2, empate
        	partidos[indiceArchivo].setResultado(quienGana(equi1,gol1,equi2,gol2));

          	indiceArchivo++;
        	}
                 
         
        // CONSULTAS SQL
        //
        // jugador, idPartido, resultado
        // SELECT U.nombre, partido.idPartido, P.resultado FROM pronostico AS P INNER JOIN usuario AS U ON P.idUsuario=U.idUsuario INNER JOIN partido as partido ON P.IdPartido=partido.idPartido;
        //
        // cuántos jugadores hay
        // SELECT COUNT(DISTINCT idUsuario) AS cantidad FROM pronostico;
        //
        // nombres de los jugadores
        // SELECT DISTINCT usuario.nombre FROM pronostico INNER JOIN usuario ON pronostico.idUsuario=usuario.idUsuario;
        //
        // cuántos pronósticos hay
        // SELECT COUNT(*) AS cantidad FROM pronostico
        //
        // jugador, resultado pronosticado, idPartido
        // SELECT usuario.nombre, partido.idPartido, pronostico.resultado FROM pronostico INNER JOIN partido ON pronostico.IdPartido=partido.idPartido INNER JOIN usuario ON pronostico.idUsuario=usuario.idUsuario
        
        int cantidadJugadores=0;
        
        consultaSQL="SELECT COUNT(DISTINCT idUsuario) AS cantidad FROM pronostico"; // cuantos jugadores hay
        resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
              
        while (resultSet.next()) {
        	cantidadJugadores=resultSet.getInt("cantidad");

        }
        
    	    	    	
        //crea arreglo de jugadores
        String jugadores[] = new String [cantidadJugadores];
        
        //crea arreglo de puntajes
        int puntajes[] = new int [cantidadJugadores];
        
        //crea arreglo de cantidad de aciertos
        int aciertos[] = new int [cantidadJugadores];
        
        //en jugadores[x] tenemos el nombre del jugador, en puntajes[x] tenemos el puntaje de ese jugador y en aciertos[x] la cantidad de pronósticos acertados
        
        
      String nombreJugador;
      int indiceJugadores = 0;
      int cantidadPronosticos=0;
      int indicePronosticos=0;
  
      
      // arma arreglo de jugadores
      consultaSQL="SELECT DISTINCT usuario.nombre FROM pronostico INNER JOIN usuario ON pronostico.idUsuario=usuario.idUsuario"; // nombre de los jugadores
      resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
      
      while (resultSet.next()) {
      	nombreJugador=resultSet.getString("nombre");
      	jugadores[indiceJugadores]=nombreJugador;   // nombre
      	puntajes[indiceJugadores]=0;  // puntaje
      	aciertos[indiceJugadores]=0;  // cantidad de aciertos
      	indiceJugadores++;
      }
      
      // cantidad de pronósticos
      consultaSQL="SELECT COUNT(*) AS cantidad FROM pronostico";  // cuenta cantidad de pronósticos
      resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
      while (resultSet.next()) {
        	cantidadPronosticos=resultSet.getInt("cantidad");
        }
      System.out.println("Cantidad de pronósticos "+cantidadPronosticos);
      
      
      Pronostico pronosticoJugador[] = new Pronostico [cantidadPronosticos];
      
      // inicializar el arreglo
      for (int i=0;i<pronosticoJugador.length;i++) {
        	 pronosticoJugador[i]=new Pronostico("","","");    // inicializa los pronósticos: partido, resultado, jugador
         }
      
      
      consultaSQL="SELECT usuario.nombre, pronostico.idPartido, pronostico.resultado FROM pronostico INNER JOIN usuario ON pronostico.idUsuario=usuario.idUsuario"; // jugador, resultado pronosticado, idPartido
      resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
      
      while (resultSet.next()) {
    	    pronosticoJugador[indicePronosticos].setJugador(resultSet.getString("nombre"));
        	pronosticoJugador[indicePronosticos].setPartido(resultSet.getString("idPartido"));
        	pronosticoJugador[indicePronosticos].setResultado(resultSet.getString("resultado"));
        	
        	indicePronosticos++;
        }
      
      // donde está la información en este momento
      // pronosticoJugador[] -> nombre jugador, idPartido, resultado pronosticado
      // partidos[] -> partidoId, resultado real
     
      
      for (int i=0;i<indicePronosticos;i++) {   // recorre los pronósticos
    	  int iJugador=buscarJugador(jugadores,pronosticoJugador[i].getJugador());  // busca el indice del arreglo de jugador
    	  if (iJugador!=-1) {    // encontró el jugador
    		  
    		  int iPartido=buscarIndicePartido(partidos,pronosticoJugador[i].getPartido());  // busca el indice del arreglo de partidos
 
    		  if (iPartido!=-1) {  // encontró el partido
 
    			  if (pronosticoJugador[i].getResultado().equals(partidos[iPartido].getResultado())) {   // el resultado real es igual al pronosticado
        			  
        			puntajes[iJugador]=puntajes[iJugador]+puntosGanador;  // suma los puntos segun el archivo de configuración inicial
        			aciertos[iJugador]++;  // suma 1 a cantidad de aciertos
        		  }  
    			  
    		  } else {
        		  System.out.println("No se encuentra el partido");
        	  }
   		  
    	  } else {
    		  System.out.println("No se encuentra el jugador");
    	  }
    	  
    	  
      }
      
       
      //muestra el puntaje

      for (int i=0; i < cantidadJugadores; i++) {
    	  System.out.println("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+" y acertó "+aciertos[i]+" resultados." ); 
      }
         
      conexion.close();  // cierra la conexión
      
    } catch (FileNotFoundException e) {     // error en el manejo de los archivos
      System.out.println("A ocurrido un error.");
      e.printStackTrace();
    }
    catch (NumberFormatException errorFormato) {     // error en la conversión a int de los goles (String a int)
		System.out.println("Se intentó cargar un valor de goles no permitido");
		
	}
    
  }
}

