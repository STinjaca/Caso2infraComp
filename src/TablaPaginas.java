import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TablaPaginas extends Thread {
	private BufferedReader br;
    private ArrayList<Integer> tablaPaginas = new ArrayList<Integer>();
    private ReemplazoNoUsada algoritmo;
    private int fallosPagina;
    private int numReferencias;
    private int numMarcosP;
    private int paginasOcupadas;
    private int numPaginas;

    // Constructor de la tabla de páginas

    public TablaPaginas(int numMarcoP, String nombreArchivo, int numReferencias, int numPaginas) {
        this.numMarcosP = numMarcoP;
        fallosPagina = 0;
        this.numReferencias = numReferencias;
        this.numPaginas = numPaginas;
        algoritmo = new ReemplazoNoUsada(numMarcosP);
        algoritmo.start();
        try {
            this.br = new BufferedReader(new FileReader(nombreArchivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Metodo que lee cada linea del archivo y las referencias, a la vez que corre el algoritmo de envejecimiento
    public void leerRef() {
        try {
            String line;
            if ((line = br.readLine()) != null) {
                if (line.startsWith("M[") || line.startsWith("F[") || line.startsWith("R[")) {
                    String[] parts = line.split(",");
                    int pag = Integer.parseInt(parts[1]);
                    if (tablaPaginas.get(pag) == -404) {
                        fallosPagina++;
                        if (paginasOcupadas == numMarcosP) {
                        	//escoger pagina a reemplazar con el algoritmo paginaReemplazar;
                        	int paginaReemplazar = algoritmo.pagAReemplazar(); 
                        	tablaPaginas.set(tablaPaginas.indexOf(paginaReemplazar), -404); //deja de existir en la tabla
                        	tablaPaginas.set(pag, paginaReemplazar); // a la pag nueva le doy su posición
                        } else {
	                    	for (int i = 0; i < numPaginas;i++){
	                    		if(tablaPaginas.get(i)== -404){
	                    			tablaPaginas.set(pag, i);
	                                paginasOcupadas++;}
	                    	}
                        }
                    }
                    algoritmo.marcarReferenciaPagina(tablaPaginas.get(pag)); //Se ajusta la pag al ser referenciada si se encuentra cargada
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        for (int n = 0; n < numReferencias + 6; n++) {
            leerRef();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        //System.out.println("Fallos: " + fallosPagina);
        System.exit(0);
    }
}
