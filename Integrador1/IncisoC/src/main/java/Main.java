import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.la4j.LinearAlgebra;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.linear.LinearSystemSolver;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;

public class Main {
    public static double[] vectorEstacionario = new double[4];

    public static double[][] mat = new double[4][4];

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("salidaIncisoC.txt"));
        System.setOut(out);

        if (CargaArchivo(mat)) {
            ResuelveSistema(mat, vectorEstacionario);
            CalcEntropia(mat, vectorEstacionario);
        }
    }

    private static void CalcEntropia(double[][] mat, double[] vectorEstacionario) {
        double entropia = 0;
        double auxcol;

        for (int i = 0; i < 4; i++) {
            auxcol = 0;
            for (int j = 0; j < 4; j++) {
                if (mat[j][i] != 0)
                    auxcol += mat[j][i] * Math.log(mat[j][i]) / -Math.log(2);
            }
            entropia += vectorEstacionario[i] * auxcol;
        }
        System.out.println("La entropía de esta fuente de orden 1 es: " + entropia);

    }


    private static boolean CargaArchivo(double[][] mat) {
        Scanner scan;
        try {
            File entrada = new File("matriz.txt");
            scan = new Scanner(entrada);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    mat[i][j] = scan.nextDouble();
                }
            }
            scan.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo matriz.txt no existe. Verifique que esté creado");
            return false;
        }
    }

    private static void ResuelveSistema(double[][] mat, double[] vectorEstacionario) {
        double[][] mataux = new double[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mataux[i][j] = mat[i][j];
                if (i == j)
                    mataux[i][j]--;
            }
        }

        for (int i = 0; i < 4; i++) {
            mataux[3][i] = 1;
        }


        Matrix a = new Basic2DMatrix(mataux); //Arma la matriz para que pueda resolverse con la libreria

        Vector b = new BasicVector(new double[]{0, 0, 0, 1});


        LinearSystemSolver solver =
                a.withSolver(LinearAlgebra.FORWARD_BACK_SUBSTITUTION);

        Vector vectorAuxiliar;
        vectorAuxiliar = solver.solve(b);

        for (int i = 0; i < 4; i++) {
            vectorEstacionario[i] = vectorAuxiliar.get(i);
        }


        System.out.println("Para la matriz cargada, el vector estacionario es: V* = [" + vectorAuxiliar + "]");
    }
}
