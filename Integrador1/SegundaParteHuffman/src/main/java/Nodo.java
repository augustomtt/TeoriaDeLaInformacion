public class Nodo {
    private String cadOriginal; //Si la cadena es null, quiere decir que no es nodo hoja
    private String cadNueva ="";
    private double valor;
    private Nodo izq;
    private Nodo der;

    public Nodo(double valor, String cadOriginal) {
        this.cadOriginal = cadOriginal;
        this.valor = valor;
        izq = der = null;
    }
    public Nodo(double valor,Nodo izq, Nodo der) {
        this.valor = valor;
        this.izq = izq;
        this.der = der;
        this.cadOriginal = null;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "valor=" + valor +
                '}';
    }

    public Nodo getIzq() {
        return izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setCadNueva(String cadNueva) {
        this.cadNueva = cadNueva;
    }

    public String getCadNueva() {
        return cadNueva;
    }

    public String getCadOriginal() {
        return cadOriginal;
    }
}




