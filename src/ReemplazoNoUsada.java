
public class ReemplazoNoUsada extends Thread{
    private int marcosPagina;
    private int[][] contadores;
    
    
    // Contructor algoritmo reemplazo menos reciente
	public ReemplazoNoUsada(int numMarcosP) {
        this.marcosPagina = numMarcosP;
        this.contadores = new int[marcosPagina][2];
	}

    // ActualizarContadores
    private void actualizarContadores() {
        synchronized (contadores) {
            for (int i = 0; i < marcosPagina; i++) {
            	contadores[i][0]=0;
            }
        }
    }

    public void marcarReferenciaPagina(int indicePagina, String pagRef) {
        synchronized (contadores) {
            if (pagRef == "R") {
            	contadores[indicePagina][0] = 1;}
            else if(pagRef == "W"){
            	contadores[indicePagina][1] = 1;}	
        }
    }

    public int pagAReemplazar() {
    	synchronized (contadores) {
            int lastPage = 0;
            for (int i = 1; i < marcosPagina; i++) {
                if (contadores[i][0]==0 && contadores[i][1]==0) {
                    lastPage = i;
                    break;
                }
                else if(contadores[i][0]==0) {
                	lastPage = i;
                    break;
                }
                else if(contadores[i][0]==0 && contadores[i][1]==1 ) {
                	lastPage = i;
                    break;
                }
            }
            return lastPage;
        }
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
