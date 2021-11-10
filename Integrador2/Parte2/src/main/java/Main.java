public class Main {
    public static void main(String[] args) {
       // Cambiar datos de n, m entrada, y matriz.
        int n = 2,m = 3;
        double[] entrada = new double[n];
        entrada[0] = 0.25;entrada[1] = 0.75;
        double[][] matriz = new double[n][m];
        matriz[0][0] = 0.5;
        matriz[0][1] = 0.5;
        matriz[0][2] = 0;
        matriz[1][0] = 0;
        matriz[1][1] = 0.3333;
        matriz[1][2] =0.6666;
        double[] salida = probSalida(matriz,entrada,m);

        for(int i=0;i<salida.length;i++) {
            System.out.println("P(b="+i+"): "+salida[i]);
        }
        System.out.println("P(a=0;b=0) : "+probabilidad(0,0,matriz,entrada,salida));
        System.out.println("H(A/b=1) = "+entropiaPosteriori(1,matriz,entrada,salida));
        System.out.println("Equivocacion: "+equivocacion(matriz,entrada,salida));
        System.out.println("Informacion Mutua: "+informacionMutua(matriz,entrada,salida));
        double[][] matrizS = matrizSalida(matriz,entrada,salida);
        System.out.println("Equivocacion: "+equivocacion(matrizS,salida,entrada));
        System.out.println("Informacion Mutua: "+informacionMutua(matrizS,salida,entrada));
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
