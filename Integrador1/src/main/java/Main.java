import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    static HashMap<Integer,Integer> digitos5 = new HashMap<>();
    static HashMap<Integer,Integer> digitos7 = new HashMap<>();
    static HashMap<Integer,Integer> digitos9 = new HashMap<>();

    public static void main(String[] args) throws IOException {

            CargaPalabras(5, digitos5);
            //    CargaPalabras(7,digitos7);
            //    CargaPalabras(9,digitos9);

    }

    private static int CalculaCantidadDigitos() throws IOException { //Funciona probada y devuelve bien
        FileReader entrada;
        int cont = 0;

        try {
            entrada = new FileReader("anexo1-grupo2.txt");
            while(entrada.read() != -1)
                cont++;
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA! Verifique que exista el archivo");
            return -1;
        }
        return cont;
    }


    public static void CargaPalabras(int cantDigitos, HashMap<Integer,Integer> map) throws IOException {
        int c=0;
        try {
            FileReader entrada = new FileReader("anexo1-grupo2.txt");
            while(c != -1){
                System.out.println("el char fue: "+ c);
                c = entrada.read();
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA! Verifique que exista el archivo");
        }
    }
}
