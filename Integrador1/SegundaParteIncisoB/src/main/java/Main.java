import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeMap;

public class Main {
	   static TreeMap<String, Integer> digitos5 = new TreeMap<>();
	    static TreeMap<String, Integer> digitos7 = new TreeMap<>();
	    static TreeMap<String, Integer> digitos9 = new TreeMap<>();
	
	    public static void main(String[] args) throws IOException{
		PrintStream out = new PrintStream(new FileOutputStream("salidaIncisoB.txt"));
        System.setOut(out);
        CargaPalabras(5, digitos5);
        CargaPalabras(7, digitos7);
        CargaPalabras(9, digitos9);
        System.out.println("-------Palabras de 5 Digitos-------:");
        System.out.println("Kraft: "+Kraft(digitos5));
        System.out.println("Longitud Media: "+longitudMedia(digitos5));
        System.out.println("-------Palabras de 7 Digitos-------:");
        System.out.println("Kraft: "+Kraft(digitos7));
        System.out.println("Longitud Media: "+longitudMedia(digitos7));
        System.out.println("-------Palabras de 9 Digitos:-------:");
        System.out.println("Kraft: "+Kraft(digitos9));
        System.out.println("Longitud Media: "+longitudMedia(digitos9));
        
	}
	public static void CargaPalabras(int cantDigitos, TreeMap<String, Integer> map) throws IOException {
        int car;
        Integer cantActual;
        StringBuilder pal;
        String ceros ="";
        for (int p=0;p<cantDigitos;p++)
            ceros += 0;


        for(int i=0;i<Math.pow(2,cantDigitos);i++){
            String binario = Integer.toBinaryString(i);
            map.put((ceros + binario).substring(binario.length()),0);
        }

        try {
            FileReader entrada = new FileReader("anexo1-grupo2.txt");
            car = entrada.read();
            while (car != -1) {
                pal = new StringBuilder("" + (char) car);
                for (int k = 0; k < cantDigitos - 1; k++) {
                    car = entrada.read();
                    pal.append((char) car);
                }
                cantActual = map.get(pal.toString());
                map.put(pal.toString(), cantActual+1);
                car = entrada.read(); //por la lectura anticipada
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA: \"anexo1-grupo2.txt\"! Verifique que exista el archivo");
        }
    }
	public static double Kraft(TreeMap<String, Integer> map) {
		double acum = 0;
		 for (String palabra: map.keySet()) {
			 acum += Math.pow(2,-palabra.length());
		 }
		return acum;
	}
	public static double longitudMedia(TreeMap<String, Integer> map) {
		double acum = 0;
		 int cantTotalPalabras = map.values().stream().mapToInt(Integer::intValue).sum();
		 
		 for (String palabra: map.keySet()) {
			 acum += ((float)map.get(palabra)/cantTotalPalabras)*palabra.length();
		 }
		return acum;
	}
	
}
