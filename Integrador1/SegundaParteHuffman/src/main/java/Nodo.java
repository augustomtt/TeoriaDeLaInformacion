public class Nodo {
    private double valor;
    private Nodo izq;
    private Nodo der;

    public Nodo(double valor) {
        this.valor = valor;
        izq = der = null;
    }
    public Nodo(double valor,Nodo izq, Nodo der) {
        this.valor = valor;
        this.izq = izq;
        this.der = der;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}



