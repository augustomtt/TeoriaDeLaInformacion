import java.util.Comparator;

public class ComparaNodos implements Comparator<Nodo> {
    @Override
    public int compare(Nodo o1, Nodo o2) {
        if (o1.getValor() > o2.getValor())
            return 1;
        else if (o1.getValor() < o2.getValor())
            return -1;
        return 0;

    }
}
