public class Util {


    public static String buscaArbol(Nodo raiz, String x){
        if (raiz!=null && raiz.getCadOriginal()!=null &&raiz.getCadOriginal().equals(x))
            return raiz.getCadNueva();
        else if (raiz == null)
            return null;
        else{
            String izq = buscaArbol(raiz.getIzq(),x);
            if (izq != null)
                return izq;
            else return buscaArbol(raiz.getDer(),x);
        }
    }
}
