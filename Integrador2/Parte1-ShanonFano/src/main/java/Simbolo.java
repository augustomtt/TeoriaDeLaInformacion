

public class Simbolo implements Comparable{
	 private char c;
	    private double probabilidad;
	    private String codigo;
	    public Simbolo(char c, Double probabilidad){
	        this.c=c;
	        this.probabilidad=probabilidad;
	        codigo="";
	    }
		public char getC() {
			return c;
		}
		public double getProbabilidad() {
			return probabilidad;
		}
		public String getCodigo() {
			return codigo;
		}
		public void setC(char c) {
			this.c = c;
		}
		public void setProbabilidad(double probabilidad) {
			this.probabilidad = probabilidad;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		@Override
		public int compareTo(Object o) {
			Simbolo s = (Simbolo) o;
			if(this.probabilidad>s.probabilidad)
				return 1;
			else {
					if(this.probabilidad==s.probabilidad)
						return 0;
				}
			return -1;
			
		}
	    
}
