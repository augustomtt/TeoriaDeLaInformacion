import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {


    static PriorityQueue<Nodo> longitud5 = new PriorityQueue<Nodo>(32, new ComparaNodos());
    static PriorityQueue<Nodo> longitud7 = new PriorityQueue<>(128,new ComparaNodos());
    static PriorityQueue<Nodo> longitud9 = new PriorityQueue<>(512,new ComparaNodos());

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("salidaHuffman.txt"));
     //   System.setOut(out);
        CargaArbolInicial(5, longitud5);
        //CargaArbolInicial(7, longitud7);
        //CargaArbolInicial(9, longitud9);


        Huffman(longitud5,31,31);
    }


    public static void CargaArbolInicial(int cantDigitos, PriorityQueue<Nodo> arbol)  {
        int i = 0;
        File arch = new File("probabilidades" + cantDigitos + ".txt");
        Scanner entrada = null;
        try {
            entrada = new Scanner(arch);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo probabilidades" + cantDigitos +".txt - Verifique que exista");
        }

        if (entrada != null) {
            while (entrada.hasNextDouble()){
                double probactual = entrada.nextDouble();
                arbol.add(new Nodo(probactual));
                i++;
            }
        }
    }
    static void Huffman(PriorityQueue<Nodo> arbol, int n, int tope){
        double probultimo;
        if(n>=1){ //Cuando le queden menos de dos elementos es la condicion de corte
            //probultimo = arbol[n].getProb();
            //arbol[n-1].addProb(probultimo);
            //ordenar
            //Huffman(arbol,n-1);
        }
    }
}

