
public class ReemplazoNoUsada extends Thread{
    private int marcosPagina;
    private int[][] contadores;
    
    
    // Contructor algoritmo reemplazo menos reciente
	public ReemplazoNoUsada(int numMarcosP) {
        this.marcosPagina = numMarcosP;
        this.contadores = new int[numMarcosP][2];
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
            if (pagRef.equals("R")) {
            	contadores[indicePagina][0] = 1;}
            else if(pagRef.equals("W")){
            	contadores[indicePagina][0] = 1;
                contadores[indicePagina][1] = 1;}	
        }
    }

    public int pagAReemplazar() {
    	synchronized (contadores) {
            int[] candidato = new int[4];
            for (int i = marcosPagina -1; i >= 0 ; i--) {
                if (contadores[i][0]==0 && contadores[i][1]==0) {
                    candidato[0] = i+1;
                }
                else if(contadores[i][0]==0 && contadores[i][1]==1) {
                	candidato[1] = i+1;
                }
                else if(contadores[i][0]==1 && contadores[i][1]==0 ) {
                	candidato[2] = i+1;
                }
                else if(contadores[i][0]==1 && contadores[i][1]==1 ) {
                	candidato[3] = i+1;
                }
            }
            for(int i = 0; i<4;i++){
                if(candidato[i] != 0){
                    return candidato[i]-1;
                }
            }
            return 0;
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
