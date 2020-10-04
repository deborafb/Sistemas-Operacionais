public class Filosofo implements Runnable{
	
	private int numeroID;	
	
	public Filosofo(int numeroID) {
		
		this.numeroID = numeroID;	
		new Thread((Runnable) this, "Filosofo").start();
	}
	
	public void comFome() {	
		
		Jantar.estado[this.numeroID] = "com fome";
		System.out.println("Filósofo " + this.numeroID + " está com fome");
	}
	
	public void comer() {	
		
		Jantar.estado[this.numeroID] = "comendo";
		System.out.println("Filósofo " + this.numeroID + " está comendo");
		
		 try {
	        Thread.sleep(1000L);
	     }
	     catch (InterruptedException exception) {
	        System.out.println(exception.getMessage());
	     }
	}
	
	public void pensar() {	
		
		Jantar.estado[this.numeroID] = "pensando";
		System.out.println("Filósofo " + this.numeroID + " está pensando");

		try {
	        Thread.sleep(1000L);
	     }
	     catch (InterruptedException exception) {
	        System.out.println(exception.getMessage());
	     }
	}
	
	public int vizinhoDireita (int id) {		
        return (id + 1) % 5;
    }

    public int vizinhoEsquerda (int id) {    	
        if (id == 0) {	           
            return 4;
        }
        else {
        	
            return (id - 1) % 5;
        }
    }
	
	public void pegarTalheres() { 
		
        Jantar.mutex.down();        
        comFome();
        talheres();
        Jantar.mutex.up();
        Jantar.semaforos[this.numeroID].down();
    }
	
	 public void largarTalheres() {

	        Jantar.mutex.down();
	        pensar();
	        Jantar.filosofos[vizinhoEsquerda(this.numeroID)].talheres();
	        Jantar.filosofos[vizinhoDireita(this.numeroID)].talheres();      
	        Jantar.mutex.up();
	    }
	 
	 public void talheres() {
		 if(Jantar.estado[vizinhoDireita(this.numeroID)] != "comendo" &&
		    Jantar.estado[vizinhoEsquerda(this.numeroID)] != "comendo") {
		    comer();
		    Jantar.semaforos[this.numeroID].up();	
		 }
	 }
	 
	 @Override
	    public void run () {		 
	       while (true) {
	    	   try {	       
	              pensar();	           
	              pegarTalheres();
	              Thread.sleep(1000L);
	              largarTalheres();            
	        }
	        catch (InterruptedException excepetion){	            
	            System.out.println(excepetion.getMessage());
	            return;
	        }
	    }	 
    }
}	

