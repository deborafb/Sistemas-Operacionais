
public class Semaforo {
	
	 private int contador;

	    public Semaforo() {
	        this.contador = 0;
	    }
	    
	    public Semaforo (int valor){
	        this.contador = valor;
	    }

	    public synchronized void down () {
	        while (this.contador == 0) {
	            try {
	                wait();
	            }
	            catch (InterruptedException exception) {
	                System.out.println(exception.getMessage());
	            }
	        }
	        
	        this.contador--;
	    }

	    public synchronized void up () {
	        this.contador++;
	        notify();
	    }

}
