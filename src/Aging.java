
public class Aging extends Thread{
    private final int MARCOS_P;
    private int[] contadores;
    
    
    // Contructor método envejecimiento
	public Aging(int numMarcosP) {
        this.MARCOS_P = numMarcosP;
        this.contadores = new int[MARCOS_P];
	}

    // ActualizarContadores
    private void actualizarContadores() {
    	//TODO
    }

    public void marcarReferenciaPagina(int indicePagina) {
    	//TODO
    }

    public int pagAReemplazar() {
    	//TODO
    	return 0;
    }

    @Override
    public void run() {
        while (true) {
            actualizarContadores();
            try {
                Thread.sleep(4); //4ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
