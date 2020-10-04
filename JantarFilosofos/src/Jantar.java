
public class Jantar {
	
	public static String[] estado = new String[5];
	public static Semaforo[] semaforos = new Semaforo[5];
	public static Filosofo[] filosofos = new Filosofo[5];
	public static Semaforo mutex = new Semaforo(1);
	
	public Jantar() {
		for(int i = 0; i < estado.length; i++) {
			estado[i] = "pensando";
			semaforos[i] = new Semaforo(0);
			filosofos[i] = new Filosofo(i);
		}
		
	}
	
	public static void main(String[] args) {
        new Jantar();
    }
}
