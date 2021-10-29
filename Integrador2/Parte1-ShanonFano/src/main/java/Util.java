import java.util.ArrayList;

public class Util {

	
	public static String buscaCodigo(char letra,ArrayList<Simbolo> letras) {
		String retorno = null;
		boolean encontro = false;
		int i=0;
		while(i<letras.size() && !encontro) {
			if(letras.get(i).getC()==letra){
			encontro=true;
			retorno = letras.get(i).getCodigo();
			}
			i++;
		}
		
		return retorno;
	}
}
