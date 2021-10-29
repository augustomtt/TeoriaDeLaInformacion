import java.util.ArrayList;
import java.util.Collections;


public class Main {
    public static void main(String[] args) {
ArrayList<Simbolo> caracteres  = new ArrayList<>();
		
		caracteres.add(new Simbolo('a',0.22));
		caracteres.add(new Simbolo('b',0.28));
		caracteres.add(new Simbolo('c',0.15));
		caracteres.add(new Simbolo('d',0.3));
		caracteres.add(new Simbolo('e',0.05));
		
		Collections.sort(caracteres);
		Collections.reverse(caracteres);
		
		ShannonFano.codificar(caracteres,0,caracteres.size()-1);
		for(int i=0;i<caracteres.size();i++) {
			System.out.println(caracteres.get(i).getC()+"   "+caracteres.get(i).getCodigo());
		}
    }
}
