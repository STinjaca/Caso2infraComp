
public class ReemplazoNoUsada extends Thread{
    private int marcosPagina;
    private int[][] contadores;
    
    
    // Contructor algoritmo reemplazo menos reciente
	public ReemplazoNoUsada(int numMarcosP) {
        this.marcosPagina = numMarcosP;
        this.contadores = new int[numMarcosP][3];
	}

    // ActualizarContadores
    private void actualizarContadores() {
        synchronized (contadores) {
            for (int i = 0; i < marcosPagina; i++) {
            	contadores[i][0]=0;
            }
        }
    }

    public void marcarReferenciaPagina(int pR, int pV, String pagRef) {
        synchronized (contadores) {
        	contadores[pR][2] = pV;
            if (pagRef.equals("R")) {
            	contadores[pR][0] = 1;}
            else if(pagRef.equals("W")){
            	contadores[pR][0] = 1;
                contadores[pR][1] = 1;}	
        }
    }

    public int[] pagAReemplazar() {
    	int[] result = new int[2];
    	synchronized (contadores) {
            for (int i = 0 ; i < marcosPagina ; i++) {
                if (contadores[i][0]==0 && contadores[i][1]==0) {
                	result[0] = i;
                	result[1] = contadores[i][2];
                    return result;
                }
            }
            for (int i = 0 ; i < marcosPagina ; i++) {
                if (contadores[i][0]==0 && contadores[i][1]==1) {
                	result[0] = i;
                	result[1] = contadores[i][2];
                    return result;
                }
            }
            for (int i = 0 ; i < marcosPagina ; i++) {
            	if(contadores[i][0]==1 && contadores[i][1]==0 ) {
            		result[0] = i;
                	result[1] = contadores[i][2];
                    return result;
                }
            }
            for (int i = 0 ; i < marcosPagina ; i++) {
                if(contadores[i][0]==1 && contadores[i][1]==1 ) {
                	result[0] = i;
                	result[1] = contadores[i][2];
                    return result;
                }
            }
        }
    	return result;
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
