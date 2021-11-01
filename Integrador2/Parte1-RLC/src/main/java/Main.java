import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        aplicarRLC("imagen.raw");
        aplicarRLC("Argentina.txt");
        aplicarRLC("Danes.txt");
    }

    private static void aplicarRLC(String nombre) throws IOException {
        int cont;
        char letraactual;
        FileReader entrada;
        PrintStream out = new PrintStream(new FileOutputStream(nombre.substring(0, nombre.length() - 3) + "rlc"));
        System.setOut(out);
        entrada = new FileReader(nombre);
        int car = entrada.read();
        while (car != -1) {
            letraactual = (char) car;
            cont = 0;
            while (car == letraactual) {
                do {
                    car = entrada.read();
                } while ((car == 10 || car == 13) && nombre.equals("imagen.raw"));
                cont++;
            }
            System.out.print(cont + "" + letraactual + (nombre.equals("imagen.raw")?"\n":"")); //comillas necesarias
        }
    }
}
