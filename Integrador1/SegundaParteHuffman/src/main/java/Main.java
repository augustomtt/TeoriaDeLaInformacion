import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {


    static PriorityQueue<Nodo> longitud5 = new PriorityQueue<>(32, new ComparaNodos());
    static PriorityQueue<Nodo> longitud7 = new PriorityQueue<>(128, new ComparaNodos());
    static PriorityQueue<Nodo> longitud9 = new PriorityQueue<>(512, new ComparaNodos());

    static Nodo raiz5;
    static Nodo raiz7;
    static Nodo raiz9;


    public static void main(String[] args) throws IOException {

        CargaArbolInicial(5, longitud5);
        CargaArbolInicial(7, longitud7);
        CargaArbolInicial(9, longitud9);

        raiz5 = GeneraArbol(longitud5);
        raiz7 = GeneraArbol(longitud7);
        raiz9 = GeneraArbol(longitud9);

        Huffman(raiz5);
        Huffman(raiz7);
        Huffman(raiz9);

        Recontruir(5, raiz5);
        Recontruir(7, raiz7);
        Recontruir(9, raiz9);

    }


    public static void CargaArbolInicial(int cantDigitos, PriorityQueue<Nodo> arbol) {
        String cadaux;
        File arch = new File("probabilidades" + cantDigitos + ".txt");
        Scanner entrada = null;
        try {
            entrada = new Scanner(arch);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo probabilidades" + cantDigitos + ".txt - Verifique que exista");
        }

        if (entrada != null) {
            while (entrada.hasNextDouble()) {
                cadaux = entrada.next();
                double probactual = entrada.nextDouble();
                arbol.add(new Nodo(probactual, cadaux));
            }
            entrada.close();
        }

    }

    public static Nodo GeneraArbol(PriorityQueue<Nodo> arbol) {
        Nodo aux1, aux2, nuevo;
        while (arbol.size() >= 2) {
            aux1 = arbol.poll();
            aux2 = arbol.poll();
            nuevo = new Nodo(aux1.getValor() + aux2.getValor(), aux1, aux2);
            arbol.add(nuevo);
        }
        return arbol.poll(); //La estructura queda vacia y el arbol quedó armado en raiz.
    }

    public static void Huffman(Nodo raiz) {
        if (raiz.getIzq() != null) {
            raiz.getIzq().setCadNueva(raiz.getCadNueva() + '0');
            Huffman(raiz.getIzq());
        }
        if (raiz.getDer() != null) {
            raiz.getDer().setCadNueva(raiz.getCadNueva() + '1');
            Huffman(raiz.getDer());
        }
    }

    private static void Recontruir(int cantDigitos, Nodo raiz) throws IOException {
        int car;
        String nuevapal;

        try {
            BufferedWriter salida = new BufferedWriter(new FileWriter("nuevoArch" + cantDigitos + ".txt"));
            FileReader entrada = new FileReader("anexo1-grupo2.txt");
            car = entrada.read();
            while (car != -1) {
                StringBuilder pal = new StringBuilder("" + (char) car);
                for (int k = 0; k < cantDigitos - 1; k++) {
                    car = entrada.read();
                    pal.append((char) car);
                }
                nuevapal = Util.buscaArbol(raiz, pal.toString());
                if (nuevapal != null) //Nunca debería ser null
                    salida.write(nuevapal);
                car = entrada.read(); //por la lectura anticipada
            }
            entrada.close();
            salida.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA: \"anexo1-grupo2.txt\"! Verifique que exista el archivo");
        }
    }


}

