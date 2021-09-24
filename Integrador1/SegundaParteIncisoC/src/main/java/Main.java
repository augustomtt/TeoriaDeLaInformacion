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
        double rendimiento =0,redundancia=0;
        PrintStream out = new PrintStream(new FileOutputStream("salidaIncisoC.txt"));
        System.setOut(out);
        CargaPalabras(5, digitos5);
        CargaPalabras(7, digitos7);
        CargaPalabras(9, digitos9);
        System.out.println("------Palabras de 5 digitos------");
        rendimiento =(CalculaEntropia(digitos5)/longitudMedia(digitos5));
        redundancia = 1-rendimiento;
        System.out.println("Rendimiento: "+rendimiento*100+"%");
        System.out.println("Redundancia: "+redundancia*100+"%");
        System.out.println("------Palabras de 7 digitos------");
        rendimiento =(CalculaEntropia(digitos7)/longitudMedia(digitos7));
        redundancia = 1-rendimiento;
        System.out.println("Rendimiento: "+rendimiento*100+"%");
        System.out.println("Redundancia: "+redundancia*100+"%");
        System.out.println("------Palabras de 9 digitos------");
        rendimiento =(CalculaEntropia(digitos9)/longitudMedia(digitos9));
        redundancia = 1-rendimiento;
        System.out.println("Rendimiento: "+rendimiento*100+"%");
        System.out.println("Redundancia: "+redundancia*100+"%");

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
    private static double CalculaEntropia( TreeMap<String, Integer> map) {
        float probactual,acumprob=0;
        double informacionactual;
        double acumulaEntropia=0;
        int cantTotalPalabras = map.values().stream().mapToInt(Integer::intValue).sum();


        for (String palabra: map.keySet()) {
            probactual = (float)map.get(palabra)/cantTotalPalabras;
            acumprob += probactual;
            if (probactual !=0) {
                informacionactual = -Math.log(probactual) / Math.log(2);
                acumulaEntropia += informacionactual * probactual;
            } else {
                informacionactual = 0; // por definicion si P(a) = 0 --> I(a) =0
            }

        }
        return acumulaEntropia;
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

