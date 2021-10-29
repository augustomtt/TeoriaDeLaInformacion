import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class Main {
    static TreeMap<Character, Integer> letras;
    static PriorityQueue<Nodo> probabilidades;
    static Nodo raiz;


    public static void main(String[] args) throws IOException {
        //aplicarHuffman("imagen.txt",probabilidades,letras); //TODO Revisar que hacer con los dos primeros datos (256x256) Son la longitud de la foto
        aplicarHuffman("Argentina.txt", probabilidades, letras);
        aplicarHuffman("Danes.txt", probabilidades, letras);
        System.out.println("fin - todos los archivos huffman creados sin errores");


    }

    private static void aplicarHuffman(String nombre, PriorityQueue<Nodo> probabilidades, TreeMap<Character, Integer> letras) throws IOException {
        letras = new TreeMap<>();
        probabilidades = new PriorityQueue<>(120, new ComparaNodos());
        cargaProbabilidades(nombre, probabilidades, letras);
        raiz = GeneraArbol(probabilidades);
        huffman(raiz);
        recontruir(nombre, raiz);
    }

    private static void cargaProbabilidades(String nombrearch, PriorityQueue<Nodo> probabilidades, TreeMap<Character, Integer> letras) throws IOException {
        FileReader entrada;
        Integer cantActual = 0;
        int cantTotalLetras;

        try {
            entrada = new FileReader(nombrearch);
            int car = entrada.read();
            while (car != -1) {
                cantActual = letras.get((char) car);
                if (cantActual != null)
                    letras.put((char) car, cantActual + 1);
                else
                    letras.put((char) car, 1);
                car = entrada.read(); //por la lectura anticipada
            }

            cantTotalLetras = letras.values().stream().mapToInt(Integer::intValue).sum();
            for (Character x : letras.keySet()) {
                double prob = letras.get(x) / (double) cantTotalLetras;
                Nodo aux = new Nodo(prob, x);
                probabilidades.add(aux);
            }
        } catch (FileNotFoundException e) {
            System.out.println("archivo no encontrado");
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

    public static void huffman(Nodo raiz) {
        if (raiz.getIzq() != null) {
            raiz.getIzq().setCadNueva(raiz.getCadNueva() + '0');
            huffman(raiz.getIzq());
        }
        if (raiz.getDer() != null) {
            raiz.getDer().setCadNueva(raiz.getCadNueva() + '1');
            huffman(raiz.getDer());
        }
    }

    private static void recontruir(String nombrearch, Nodo raiz) throws IOException {
        int car;
        String cadenabinaria = "";
        String nuevocod;

        try {
            FileOutputStream fos = new FileOutputStream(nombrearch.substring(0, nombrearch.length() - 3) + "huf");
            BufferedOutputStream salida = new BufferedOutputStream(fos);

            FileReader entrada = new FileReader(nombrearch);
            car = entrada.read();
            while (car != -1) {
                nuevocod = Util.buscaArbol(raiz, (char) car);
                if (nuevocod != null) { //Nunca debería ser null
                    cadenabinaria += nuevocod;
                }
                car = entrada.read(); //por la lectura anticipada
            }
            byte[] bval = new BigInteger(cadenabinaria, 2).toByteArray();
            salida.write(bval);
            entrada.close();
            salida.close();
        } catch (FileNotFoundException e) {
            System.out.println("NO SE ENCONTRADO EL ARCHIVO DE ENTRADA: \"anexo1-grupo2.txt\"! Verifique que exista el archivo");
        }
    }
}

