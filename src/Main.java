import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner s = new Scanner(System.in);
    	System.out.println("Tamaño pagina:");
    	int tamanoP = s.nextInt();
    	
    	System.out.println("Tamaño Matriz Datos:");
    	System.out.println("Tamaño Fila Datos:");
    	int numF = s.nextInt();
    	System.out.println("Tamaño Columna Datos:");
    	int numC = s.nextInt();
    	generarReferencias(tamanoP,numF, numC);
    	
    
    }
    public static void imprimirMatriz(int[][] matriz) {
    	for (int i = 0; i < matriz.length; i++) {
    		for (int j = 0; j < matriz[i].length; j++) {
    			System.out.print(matriz[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    public void filtro () {
    	
    	int[][] mat1 = {
		    		    {1, 0, 0, 0, 0},
		    		    {0, 0, 0, 0, 0},
		    		    {0, 0, 0, 0, 0},
		    		    {0, 0, 0, 0, 0}
    					};
    	int[][] mat2 = {
    			{1, 0, 0},
    			{0, 1, 0},
    			{0, 0, 1}
    	};
    	
    	int[][] mat3 = {
						{1, 0, 0, 0, 0},
						{0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0}
    	};
    	
    	
    	int nf = 4;
    	int nc = 5;
    	
    	
    	
    	for (int i = 1; i < nf - 1; i++) {
    		  for (int j = 1; j < nc - 1; j++) {

    		    // mat1: matriz de datos           Recorrer los vecinos y aplicar el filtro
    		    // mat2: matriz con el filtro (usaremos un filtro de 3x3 para resaltar bordes.)
    		    // mat3: matriz resultante

    		    int acum = 0;
    		    for (int a = -1; a <= 1; a++) {
    		      for (int b = -1; b <= 1; b++) {

    		        int i2 = i + a;
    		        int j2 = j + b;
    		        int i3 = 1 + a;
    		        int j3 = 1 + b;

    		        // Cálculo del valor de la matriz resultante en la posición actual
    		        acum += (mat2[i3][j3] * mat1[i2][j2]);
    		      }
    		    }

    		    // Asignación del valor a la matriz resultante
    		    if (acum >= 0 && acum <= 255) {
    		      mat3[i][j] = acum;
    		    } else if (acum < 0) {
    		      mat3[i][j] = 0;
    		    } else {
    		      mat3[i][j] = 255;
    		    }
    		  }
    		}

    		// Asignación de valores predefinidos a los bordes
    		for (int i = 0; i < nc; i++) {
    		  mat3[0][i] = 0;
    		  mat3[nf - 1][i] = 255;
    		}

    		for (int i = 1; i < nf - 1; i++) {
    		  mat3[i][0] = 0;
    		  mat3[i][nc - 1] = 255;
    		}
    		System.out.println("mat1");
    		imprimirMatriz(mat1);
    		System.out.println("mat2");
    		imprimirMatriz(mat2);
    		System.out.println("mat3");
    		imprimirMatriz(mat3);
    }
    
    public static void generarReferencias(int tamanoP, int numF, int numC) {
    	int tR =  tamanoP/4;
    	int despl = 0;
    	int numP = 0;
    	//Filtro
    	for (int i = 0; i<3; i++) {
    		for (int j = 0; j<3; j++) {
    			System.out.println("F["+i+"]["+j+"],"+numP+","+despl);
    			despl+= tR;
    			if (despl>=tamanoP) {
    				numP++;
    				despl = 0;
    			}
    		}
    	}
    	
    	// entrada
    	for (int i = 0; i<numF; i++) {
    		for (int j = 0; j<numC; j++) {
    			System.out.println("M["+i+"]["+j+"],"+numP+","+despl);
    			despl+= tR;
    			if (despl>=tamanoP) {
    				numP++;
    				despl = 0;
    			}
    		}
    	}
    	
    	for (int i = 0; i<numF; i++) {
    		for (int j = 0; j<numC; j++) {
    			System.out.println("R["+i+"]["+j+"],"+numP+","+despl);
    			despl+= tR;
    			if (despl>=tamanoP) {
    				numP++;
    				despl = 0;
    			}
    		}
    	}
    	
    	System.out.println("Numero paginas:" + (numP+1));
    	System.out.println("Numero Referencias:" + (numC*numF + (numC-2)*(numF-2)*18));
    	
    	
    }


}