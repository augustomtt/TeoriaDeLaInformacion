

import java.util.ArrayList;

public class ShannonFano {
	
	
	public static void codificar(ArrayList<Simbolo> caracteres, int inicio,int fin){
		if(inicio!=fin) {
		    int y=inicio;
	        int z=fin;
	        double suma_izq=0;
	        double suma_der=0;
	        //Primero divide en dos conjuntos de aproximadamente la misma probabilidad
	        while(y<=z){
	            if(suma_izq<=suma_der){
	                    suma_izq+=caracteres.get(y).getProbabilidad();
	                    y++;
	            }
	            else{
	                    suma_der+=caracteres.get(z).getProbabilidad();
	                    z--;
	            }
	            }
	        //Agrega  un 0 a los de la izquierda, y un 1 a los de la derecha
	            for(int i=inicio;i<y;i++){
	            	String codigo = caracteres.get(i).getCodigo();
	                caracteres.get(i).setCodigo(codigo+"0"); 
	            }
	            for(int i=y;i<=fin;i++){
	            	String codigo = caracteres.get(i).getCodigo();
	                caracteres.get(i).setCodigo(codigo+"1"); 
	            }
	            //Llama recursivamente con el conjunto izquierdo y con el derecho
	            codificar(caracteres,inicio,y-1);
	            codificar(caracteres,y,fin);
		}
	    
	    }
}
