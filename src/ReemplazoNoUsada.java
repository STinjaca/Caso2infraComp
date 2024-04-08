
public class ReemplazoNoUsada extends Thread{
    private int marcosPagina;
    private int[] contadores;
    
    
    // Contructor método reemplazo
	public ReemplazoNoUsada(int numMarcosP) {
        this.marcosPagina = numMarcosP;
        this.contadores = new int[marcosPagina];
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
