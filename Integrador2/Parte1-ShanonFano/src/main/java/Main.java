import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) throws IOException {
     ArrayList<Simbolo> letras  = new ArrayList<>();
      aplicarShannon("imagen.raw",letras);
      letras  = new ArrayList<>();
	  aplicarShannon("Argentina.txt",letras);
      letras  = new ArrayList<>();
	  aplicarShannon("Danes.txt",letras);
	  System.out.println("Compresion terminada sin errores");
    }
    private static void aplicarShannon(String nombrearch,ArrayList<Simbolo> letras) throws IOException {
          cargaProbabilidades(nombrearch,letras);
          
          Collections.sort(letras);
          Collections.reverse(letras);
          ShannonFano.codificar(letras,0,letras.size()-1);
          double entropia = calculaEntropia(letras);
          double longitud = longitudMedia(letras);
          double rendimiento = entropia/longitud;
          System.out.println("Rendimiento: "+rendimiento);
          System.out.println("Redundancia: "+(1-rendimiento));
          comprimir(nombrearch,letras);
    }
    private static void cargaProbabilidades(String nombrearch, ArrayList<Simbolo> letras) throws IOException {
        FileReader entrada;
        Integer cantActual = 0;
        int cantTotalLetras;
        TreeMap<Character, Integer> letras_aux  = new TreeMap<Character,Integer>();

        try {
            entrada = new FileReader(nombrearch);
            int car = entrada.read();
            while (car != -1) {
                cantActual = letras_aux.get((char) car);
                if (cantActual != null)
                    letras_aux.put((char) car, cantActual + 1);
                else
                    letras_aux.put((char) car, 1);
                car = entrada.read(); 
            }

            cantTotalLetras = letras_aux.values().stream().mapToInt(Integer::intValue).sum();
            for (Character x : letras_aux.keySet()) {
            	 double prob = letras_aux.get(x) / (double) cantTotalLetras;
            	 Simbolo s = new Simbolo(x,prob);
            	 letras.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo "+nombrearch+" no fue encontrado");
        }
    }
    public static void comprimir(String nombrearch,ArrayList<Simbolo> letras) throws IOException{
    	   int car;
           String cadenabinaria = "";
           String nuevocod;

           try {
               FileOutputStream fos = new FileOutputStream(nombrearch.substring(0, nombrearch.length() - 3) + "fan");
               BufferedOutputStream salida = new BufferedOutputStream(fos);

               FileReader entrada = new FileReader(nombrearch);
               car = entrada.read();
               while (car != -1) {
                   nuevocod = Util.buscaCodigo((char) car,letras);
                   if (nuevocod != null) { //Nunca debería ser null
                       cadenabinaria += nuevocod;
                   }
                   car = entrada.read(); 
               }
               byte[] bval = new BigInteger(cadenabinaria, 2).toByteArray();
               salida.write(bval);
               entrada.close();
               salida.close();
           } catch (FileNotFoundException e) {
               System.out.println("No se encontro el archivo: "+nombrearch);
           }
    }
    public static double calculaEntropia(ArrayList<Simbolo> letras) {
    	double entropia = 0,informacion=0;
    	for(int i=0;i<letras.size();i++) {
    		informacion = -Math.log(letras.get(i).getProbabilidad())/Math.log(2);
    		entropia+=informacion*letras.get(i).getProbabilidad();
    	}
    	return entropia;
    }
    public static double longitudMedia(ArrayList<Simbolo> letras) {
    	double acum = 0;
    	for(int i=0;i<letras.size();i++) {
    		acum+= (double)(letras.get(i).getProbabilidad()*letras.get(i).getCodigo().length());
    	}
    	return acum;
    }
}
