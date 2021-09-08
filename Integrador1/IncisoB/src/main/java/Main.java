import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Main {

    static int[][] probsmat = new int[4][4];
    static final String[] binario = {"00","01","10","11"};

    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream("salidaIncisoB.txt"));
        System.setOut(out);
        CargaProbabilidades(probsmat);
        MuestraDatos(probsmat);
    }


    public static void CargaProbabilidades(int[][] probsmat) throws IOException {
        String ant="",actual;
        int car;


        try {
            FileReader entrada = new FileReader("anexo1-grupo2.txt");
            car = entrada.read();
            ant += (char)car;
            car = entrada.read();
            ant += (char)car;

            car = entrada.read(); //Lectura anticipada de actual
            while (car != -1) {
                actual="";
                actual += (char)car;
                car = entrada.read();
                actual+= (char)car;
                probsmat[binToInt(actual)][binToInt(ant)]++;
                ant = actual;
                car = entrada.read(); //Por la lectura anticipada
            }

        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA: \"anexo1-grupo2.txt\"! Verifique que exista el archivo");
        }
    }

    public static void MuestraDatos(int[][] probsmat){
        int sumafila;
        int totaldatos = sumaMat(probsmat);

        for (int i=0;i<4;i++){
            sumafila = probsmat[i][0]+probsmat[i][1]+probsmat[i][2]+probsmat[i][3];
            System.out.println("Probabilidad de ocurrencia del símbolo "+binario[i]+" es: "+ (float)sumafila/totaldatos + " ("+sumafila+" ocurrencias total)");
        }
        System.out.println("\n");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println("P("+binario[i]+" | anterior es "+ binario[j]+") es: "+ probsmat[i][j]/(float)totaldatos + " ("+probsmat[i][j]+" ocurrencias total)");

            }

        }


    }

    private static int sumaMat(int[][] probsmat) {
        int acum = 0;
        for (int i = 0; i < probsmat.length; i++) {
            for (int j = 0; j < probsmat.length; j++) {
                acum += probsmat[i][j];
            }
        }
        return acum;
    }

    public static int binToInt(String bin) {
        switch (bin) {
            case "00":
                return 0;
            case "01":
                return 1;
            case "10":
                return 2;
            case "11":
                return 3;
            default:
                throw new NumberFormatException("Cadena binaria errónea, se esperaba 00,01,10 o 11");
        }
    }
}