// *************************************************************************************
// *                                                                                   *
// *  Se asume de antemano que cada ronda son 4 partidos y cada fase son 4 rondas      *
// *                                                                                   *
// ************************************************************************************* 

      
        // CONSULTAS SQL UTILIZADAS
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
        //
        // información de las rondas
        // SELECT * FROM ronda
        //
        // a qué ronda pertenece un partido
        // SELECT ronda.idRonda FROM ronda WHERE ronda.partido1=7 OR ronda.partido2=7 OR ronda.partido3=7 OR ronda.partido4=7;

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
    
    	int cuantasLineas = 0;
    	int indiceArchivo=0;
    	 
   	
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
    	
        // variables de configuración globales 
        int cantidadPartidosPorRonda=4;
    	int cantidadRondasPorFase=4;
        
        // establece datos de la base de datos, el usuario y la contraseña
        String usuario = "root";
        String contrasena = "";
        String consultaSQL = "";
        int cantidadRegistros=0;

        // establece la conexión a la base de datos
        Connection conexion = DriverManager.getConnection(baseDatos, usuario, contrasena);
        
        // crea un objeto Statement
        Statement statement = conexion.createStatement();
        
        // busca todos los partidos
        consultaSQL="SELECT * FROM partido";
        
        // ejecuta la consulta y obtiene un ResultSet con los datos buscados
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
                 
        int cantidadJugadores=0;
        
        consultaSQL="SELECT COUNT(DISTINCT idUsuario) AS cantidad FROM pronostico"; // cuantos jugadores hay
        resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
              
        while (resultSet.next()) {
        	cantidadJugadores=resultSet.getInt("cantidad");

        }
    	
        System.out.println("Cantidad de jugadores "+cantidadJugadores);
        
        // crea arreglo de jugadores
        String jugadores[] = new String [cantidadJugadores];
        
        // crea arreglo de puntajes
        int puntajes[] = new int [cantidadJugadores];
        
        // crea arreglo de cantidad de aciertos
        int aciertos[] = new int [cantidadJugadores];
        
        
        //en jugadores[x] tenemos el nombre del jugador, en puntajes[x] tenemos el puntaje de ese jugador y en aciertos[x] la cantidad de pronósticos acertados
        
       
        int cantidadRondas=0;        
       
        consultaSQL="SELECT COUNT(*) AS cantidad FROM ronda";    // cuántas rondas hay
        resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
        
        while (resultSet.next()) {
        	cantidadRondas=resultSet.getInt("cantidad");

        }
        
        // crea arreglo de rondas
        Ronda [] rondas = new Ronda[cantidadRondas]; 
        
        for (int i=0;i<rondas.length;i++) {
          	 rondas[i]=new Ronda("","","","","");    //inicializa las rondas
           }
        
       
        // crea arreglo para controlar los aciertos por ronda de cada jugador
        // en rondasJugador[que jugador][que ronda] tenemos los aciertos de esa ronda para ese jugador
        int [][] rondasJugador = new int [cantidadJugadores][cantidadRondas];
        
        // inicializa 
        for (int i = 0; i < cantidadJugadores; i++) {
            for (int j = 0; j < cantidadRondas; j++) {
                rondasJugador[i][j] = 0;
            }
        }
        
        int indiceRonda=0;
        
        /*
        // crea arreglo para controlar los aciertos por fase de cada jugador
        // en fasesJugador[que jugador][que ronda] tenemos los aciertos de esa ronda para ese jugador
        int [][] fasesJugador = new int [cantidadJugadores][cantidadFases];   **** falta contar las fases
        
        // inicializa 
        for (int i = 0; i < cantidadJugadores; i++) {
            for (int j = 0; j < cantidadFases; j++) {
                rondasJugador[i][j] = 0;
            }
        }
         */
        
        
        
        consultaSQL="SELECT * FROM ronda"; // trae la información de las rondas
        resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
        
        while (resultSet.next()) {
        	
        	rondas[indiceRonda].setIdRonda(resultSet.getString("idRonda"));
        	rondas[indiceRonda].setPartido1(resultSet.getString("partido1"));
        	rondas[indiceRonda].setPartido2(resultSet.getString("partido2"));
        	rondas[indiceRonda].setPartido3(resultSet.getString("partido3"));
        	rondas[indiceRonda].setPartido4(resultSet.getString("partido4"));
        	
        	indiceRonda++;

        }
       
      String nombreJugador;
      int indiceJugadores = 0;
      int cantidadPronosticos=0;
      int indicePronosticos=0;
      int ronda=0;
      // int fase=0;
      
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
      
      // **************************************************************************
      // donde está la información en este momento
      // pronosticoJugador[] -> nombre jugador, idPartido, resultado pronosticado
      // partidos[] -> partidoId, resultado real
      // rondas[] -> qué partidos pertenecen a cada ronda
      // rondasJugador[][] -> qué partidos acertó el jugador de cada ronda 
      // **************************************************************************
     
      
      for (int i=0;i<indicePronosticos;i++) {   // recorre los pronósticos
    	  int iJugador=buscarJugador(jugadores,pronosticoJugador[i].getJugador());  // busca el indice del arreglo de jugador
    	  if (iJugador!=-1) {    // encontró el jugador
    		  
    		  int iPartido=buscarIndicePartido(partidos,pronosticoJugador[i].getPartido());  // busca el indice del arreglo de partidos
 
    		  if (iPartido!=-1) {  // encontró el partido
 
    			  // de qué ronda es el partido?
    			  // busca la ronda de un partidoId específico
    			  consultaSQL="SELECT ronda.idRonda FROM ronda WHERE ronda.partido1="+pronosticoJugador[i].getPartido() +" OR ronda.partido2="+pronosticoJugador[i].getPartido() +" OR ronda.partido3="+pronosticoJugador[i].getPartido() +" OR ronda.partido4="+pronosticoJugador[i].getPartido();
    			  resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
    			  while (resultSet.next()) {
    		    	  ronda=resultSet.getInt("idRonda");  
    				  
    		        }  
    			  
    			  if (pronosticoJugador[i].getResultado().equals(partidos[iPartido].getResultado())) {   // el resultado real es igual al pronosticado
        			  
        			puntajes[iJugador]=puntajes[iJugador]+puntosGanador;  // suma los puntos segun el archivo de configuración inicial
        			aciertos[iJugador]++;  // suma 1 a la cantidad de aciertos totales
        			rondasJugador[iJugador][ronda]++;  // suma 1 a la cantidad de aciertos de la ronda
        			
        			// si suma 4 (cantidadPartidosPorRonda) aciertos en la ronda -> toda la ronda acertada
        			// if (rondasJugador[iJugador][ronda]==cantidadPartidosPorRonda) {  
        			//		// busca la fase de una ronda específica
        			// 		consultaSQL="SELECT fase.idFase FROM fase WHERE fase.ronda1="+ronda +" OR fase.ronda2="+ronda +" OR fase.ronda3="+ronda +" OR fase.ronda4="+ronda;
        			// 		resultSet = statement.executeQuery(consultaSQL); // ejecuta la consulta
      			  	//		while (resultSet.next()) {
      		    	// 			fase=resultSet.getInt("idFase");  
      				//      }  
        			//		
        			//		fasesJugador[iJugador][fase]++; // suma una ronda completa acertada a la fase
        			//}
        			
        		  }  
    			  
    		  } else {
        		  System.out.println("No se encuentra el partido");
        	  }
   		  
    	  } else {
    		  System.out.println("No se encuentra el jugador");
    	  }
    	  
      }
      
       //muestra el puntaje
      
      boolean sumaPuntosPorRonda=false;  // para saber si sumó puntos extras por acertar todos los partidos de la ronda
      //  boolean sumaPuntosPorFase=false;  // para saber si sumó puntos extra por acertar una fase completa

      for (int i=0; i < cantidadJugadores; i++) {  
    	   
    	  for (int j=1; j<cantidadRondas;j++) { 
    		  
    		  if (rondasJugador[i][j]==cantidadPartidosPorRonda) {    // si la cantidad de aciertos por ronda es igual al nro de partidos por ronda, suma puntos extra
    			  puntajes[i]=puntajes[i]+puntosExtraRonda;
    			  sumaPuntosPorRonda=true;  // suma puntos extra
    			  
    			  /*
    			   if (fasesJugador[i][j]==cantidadRondasPorFase) {   // si la cantidad de aciertos por fase es igual a la cantidad de rondas por fse, acertó todo y suma puntos extra
    			   		puntajes[i]=puntjaes[i]+puntosExtraFase;
    			   		sumaPuntosPorFase=true;
    			   		}
    			  */
    			  
    			  
    		  }
    		  
    	  }
    	  
    	  // si suma puntos por fase (obviamente sumó por ronda también) se muestra mensaje que sumó extra por ronda y por fase
    	  //	System.out.println("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+", acertó "+aciertos[i]+" resultados y sumó puntos extras por haber acertado al menos una fase completa");
    	  //  si no, si suma puntos por ronda muestra mensje que sumó extra por ronda
    	  //		System.out.println(("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+", acertó "+aciertos[i]+" resultados y sumó puntos extras por haber acertado al menos una ronda completa");
    	  //   si no, se muestra mensaje sumó tantos puntos
    	  //			System.out.println("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+" y acertó "+aciertos[i]+" resultados." );
    	  
    	  if (sumaPuntosPorRonda) {   //  
    		  System.out.println("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+", acertó "+aciertos[i]+" resultados y sumó puntos extras." ); // + 
    	  } else {
    		  System.out.println("El puntaje del jugador "+jugadores[i]+" es "+puntajes[i]+" y acertó "+aciertos[i]+" resultados." );  
    	  }
    	  
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
