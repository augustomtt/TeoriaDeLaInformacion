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

    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream("salidaIncisoA.txt"));
        System.setOut(out);
        CargaPalabras(5, digitos5);
        CargaPalabras(7, digitos7);
        CargaPalabras(9, digitos9);

        CalculaCantInformacionEntropia(5,digitos5);
        CalculaCantInformacionEntropia(7,digitos7);
        CalculaCantInformacionEntropia(9,digitos9);

    }



    public static void CargaPalabras(int cantDigitos, TreeMap<String, Integer> map) throws IOException {
        int car;
        Integer cantActual;
        StringBuilder pal;
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
                map.put(pal.toString(), cantActual == null ? 1 : cantActual + 1);
                car = entrada.read(); //por la lectura anticipada
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA: \"anexo1-grupo2.txt\"! Verifique que exista el archivo");
        }
    }

    private static void CalculaCantInformacionEntropia(int cantDigitos, TreeMap<String, Integer> map) {
        float probactual,acumprob=0;
        double informacionactual;
        double acumulaEntropia=0;
        int cantTotalPalabras = map.values().stream().mapToInt(Integer::intValue).sum();

        System.out.println("\n === Palabras de "+ cantDigitos+" digitos === \n");
        for (String palabra: map.keySet()) {
            probactual = (float)map.get(palabra)/cantTotalPalabras;
            acumprob += probactual;
            informacionactual = -Math.log(probactual)/Math.log(2);
            acumulaEntropia += informacionactual*probactual;
            System.out.printf("%s : Cant. de apariciones: %d  | P(\"%s\") = %.5f | I(\"%s\") = %.6f \n",palabra,map.get(palabra),palabra,probactual,palabra,informacionactual);
        }
        System.out.printf("La suma de todas las probabilidades es: %.5f\n", acumprob);
        System.out.printf("La entropía de la fuente de palabras de longitud %d es: %.5f\n", cantDigitos,acumulaEntropia);
    }
}
