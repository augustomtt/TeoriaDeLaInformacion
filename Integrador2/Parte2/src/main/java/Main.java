import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        PrintStream out = new PrintStream(new FileOutputStream("salidaCanales.txt"));
        System.setOut(out);

        //-----------------------
        int n1 = 5, m1= 3;
        int n2 = 4, m2= 4;
        int n3 = 6, m3= 4;
        double[] entrada1 = {0.1,0.2,0.2,0.3,0.2};
        double[] entrada2 = {0.1,0.3,0.2,0.4};
        double[] entrada3 = {0.1,0.2,0.1,0.3,0.2,0.1};


        double[][] canal1 = {{0.3,0.3,0.4},{0.4,0.4,0.2},{0.3,0.3,0.4},{0.3,0.4,0.3},{0.3,0.4,0.3}};
        double[][] canal2 = {{0.2,0.3,0.2,0.3},{0.3,0.3,0.2,0.2},{0.3,0.2,0.2,0.3},{0.3,0.3,0.3,0.1}};
        double[][] canal3 = {{0.2,0.3,0.2,0.3},{0.3,0.3,0.3,0.1},{0.2,0.2,0.3,0.3},{0.3,0.3,0.2,0.2},{0.2,0.3,0.3,0.2},{0.2,0.3,0.3,0.2}};

        System.out.println("========== CANAL 1 ==========");
        calculosCanales(m1, entrada1, canal1);


        System.out.println("========== CANAL 2 ==========");
        calculosCanales(m2, entrada2, canal2);


        System.out.println("========== CANAL 3 ==========");
        calculosCanales(m3, entrada3, canal3);
    }

    private static void calculosCanales(int m, double[] entrada, double[][] canal) {
        double[] salida1;
        double[][] matrizS;
        salida1 = probSalida(canal,entrada,m);
        for(int i=0;i<salida1.length;i++) {
            System.out.print("P(b"+i+") = "+salida1[i]+ "    ");
        }
        System.out.println();
        System.out.println("Equivocacion H(A/B) = "+equivocacion(canal,entrada,salida1));
        System.out.println("Informacion Mutua I(A/B) = "+informacionMutua(canal,entrada,salida1));
        matrizS = matrizSalida(canal,entrada,salida1);
        System.out.println("Equivocacion: H(B/A) =  "+equivocacion(matrizS,salida1,entrada));
        System.out.println("Informacion Mutua: I(B/A) = "+informacionMutua(matrizS,salida1,entrada));
        System.out.println();
    }

    public static double[][] matrizSalida(double[][] matriz,double[] entrada,double[] salida){
        double[][] invertida = new double[salida.length][entrada.length];

        for(int i=0;i<salida.length;i++){
            for(int j=0;j<entrada.length;j++){
                invertida[i][j] = (matriz[j][i]*entrada[j])/salida[i];
            }
        }

        return invertida;
    }
    public static double[] probSalida(double[][] matriz,double[] alfabeto,int m) {
        double[] salida = new double[m];

        for(int i=0;i<m;i++) {
            salida[i]=0;
            for(int j=0;j<alfabeto.length;j++) {
                salida[i] += alfabeto[j]*matriz[j][i];
            }
        }
        return salida;
    }
    public static double probabilidad(int i,int j,double[][] matriz,double[] entrada,double[] salida) {
        double resultado;

        resultado = (matriz[i][j]*entrada[i])/salida[j];

        return resultado;
    }
    public static double entropiaPosteriori(int j,double[][] matriz,double[] entrada,double[] salida) {
        double acum = 0;
        for(int i=0;i<entrada.length;i++){
            double prob = probabilidad(i,j,matriz,entrada,salida);
            if(prob!=0)
            acum+=prob*(-Math.log(prob)/Math.log(2));
        }
        return acum;
    }
    public static double entropia(double[] entrada) {
        double entropia = 0;
        for (double prob : entrada) {
            entropia += prob * (-Math.log(prob) / Math.log(2));
        }
        return entropia;
    }
    public static double equivocacion(double[][] matriz,double[] entrada,double[] salida) {
        double acum = 0;
        for(int i=0;i<salida.length;i++) {
            acum+= entropiaPosteriori(i,matriz,entrada,salida)*salida[i];
        }
        return acum;
    }
    public static double informacionMutua(double[][] matriz,double[] entrada,double[] salida) {
        return entropia(entrada) - equivocacion(matriz,entrada,salida);
    }
}
