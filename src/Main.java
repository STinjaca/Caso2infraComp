import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class Main {

    public static void filtro(int numC, int numF, HashMap<String, String> referencias, PrintWriter writer) {

        for (int i = 1; i < numF - 1; i++) {
            for (int j = 1; j < numC - 1; j++) {

                // M: matriz de datos Recorrer los vecinos y aplicar el filtro
                // F: matriz con el filtro (usaremos un filtro de 3x3 para resaltar bordes.)
                // R: matriz resultante

                int acum = 0;
                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {

                        int i2 = i + a;
                        int j2 = j + b;
                        int i3 = 1 + a;
                        int j3 = 1 + b;

                        // Cálculo del valor de la matriz resultante en la posición actual

                        writer.println(
                                "M[" + i2 + "][" + j2 + "]," + referencias.get("M[" + i2 + "][" + j2 + "]") + ",R");
                        writer.println(
                                "F[" + i3 + "][" + j3 + "]," + referencias.get("F[" + i3 + "][" + j3 + "]") + ",R");
                        // acum += (F[i3][j3] * M[i2][j2]);
                    }
                }

                // Asignación del valor a la matriz resultante
                if (acum >= 0 && acum <= 255) {
                    writer.println("R[" + i + "][" + j + "]," + referencias.get("R[" + i + "][" + j + "]") + ",W");
                    // R[i][j] = acum;
                } else if (acum < 0) {
                    writer.println("R[" + i + "][" + j + "]," + referencias.get("R[" + i + "][" + j + "]") + ",W");
                    // R[i][j] = 0;
                } else {
                    writer.println("R[" + i + "][" + j + "]," + referencias.get("R[" + i + "][" + j + "]") + ",W");
                    // R[i][j] = 255;
                }
            }
        }

        // Asignación de valores predefinidos a los bordes
        for (int i = 0; i < numC; i++) {
            writer.println("R[0][" + i + "]," + referencias.get("R[0][" + i + "]") + ",W");
            // R[0][i] = 0;
            writer.println(
                    "R[" + (numF - 1) + "][" + i + "]," + referencias.get("R[" + (numF - 1) + "][" + i + "]") + ",W");
            // R[numF - 1][i] = 255;
        }

        for (int i = 1; i < numF - 1; i++) {
            writer.println("R[" + i + "][0]," + referencias.get("R[" + i + "][0]") + ",W");
            // R[i][0] = 0;
            writer.println(
                    "R[" + i + "][" + (numC - 1) + "]," + referencias.get("R[" + i + "][" + (numC - 1) + "]") + ",W");
            // R[i][numC - 1] = 255;
        }

    }

    public static void generarReferencias(int tamanoP, int numF, int numC) {
        HashMap<String, String> referencias = new HashMap<String, String>();
        int tamanoInt = 4;
        int despl = 0;
        int numP = 0;

        try {
            PrintWriter writer = new PrintWriter("doc/referencias.txt");
            writer.println("TP=" + tamanoP);
            writer.println("NF=" + numF);
            writer.println("NC=" + numC);
            writer.println("NF_NC_Filtro=" + 3);

            // Filtro
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    referencias.put("F[" + i + "][" + j + "]", numP + "," + despl);
                    despl += tamanoInt;
                    if (despl >= tamanoP) {
                        numP++;
                        despl = 0;
                    }
                }
            }

            // Entrada
            for (int i = 0; i < numF; i++) {
                for (int j = 0; j < numC; j++) {
                    referencias.put("M[" + i + "][" + j + "]", numP + "," + despl);
                    despl += tamanoInt;
                    if (despl >= tamanoP) {
                        numP++;
                        despl = 0;
                    }
                }
            }
            // Resultado
            for (int i = 0; i < numF; i++) {
                for (int j = 0; j < numC; j++) {
                    referencias.put("R[" + i + "][" + j + "]", numP + "," + despl);
                    despl += tamanoInt;
                    if (despl >= tamanoP) {
                        numP++;
                        despl = 0;
                    }
                }
            }

            writer.println("NR=" + (numC * numF + (numC - 2) * (numF - 2) * 18));
            writer.println("NP=" + (numP + 1));
            filtro(numC, numF, referencias, writer);
            writer.close();
            System.out.println("Referencias generadas y guardadas en doc/referencias.txt");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }

    public static void calcularDatos(int numMarcoP, String nombreArchivo) {
        // Variables para almacenar los valores leídos del archivo
        int tamanoP = 0;
        int numF = 0;
        int numC = 0;
        int numRef = 0;
        int numP = 0;
        int tamanoF = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            // Leer las primeras 6 líneas del archivo
            tamanoP = Integer.parseInt(br.readLine().split("=")[1]);
            numF = Integer.parseInt(br.readLine().split("=")[1]);
            numC = Integer.parseInt(br.readLine().split("=")[1]);
            tamanoF = Integer.parseInt(br.readLine().split("=")[1]);
            numRef = Integer.parseInt(br.readLine().split("=")[1]);
            numP = Integer.parseInt(br.readLine().split("=")[1]);

            // Imprimir los valores leídos del archivo
            System.out.println("Valores leídos del archivo:");
            System.out.println("Tamaño de página: " + tamanoP);
            System.out.println("Número de filas de la matriz: " + numF);
            System.out.println("Número de columnas de la matriz: " + numC);
            System.out.println("Tamaño del filtro: " + tamanoF);
            System.out.println("Número de referencias: " + numRef);
            System.out.println("Número de páginas: " + numP);
        } catch (IOException e) {
            e.printStackTrace();
        } // Implementación del cálculo de datos aquí
        System.out.println("Cálculo de datos realizado con éxito.");
        TablaPaginas tablaPaginas = new TablaPaginas(numMarcoP, nombreArchivo, numRef, numP);
        tablaPaginas.start();
        try {
            tablaPaginas.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Generación de las referencias");
            System.out.println("2. Calcular datos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el tamaño de página: ");
                    int tamanoP = scanner.nextInt();
                    System.out.print("Ingrese el número de filas de la matriz de datos: ");
                    int numF = scanner.nextInt();
                    System.out.print("Ingrese el número de columnas de la matriz de datos: ");
                    int numC = scanner.nextInt();
                    generarReferencias(tamanoP, numF, numC);
                    System.out.println("");
                    break;
                case 2:
                    System.out.print("Ingrese el número de marcos de página: ");
                    int numMarcoP = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    System.out.print("Ingrese el nombre del archivo de referencias (doc/referencias.txt): ");
                    String nombreArchivo = scanner.nextLine();
                    calcularDatos(numMarcoP, nombreArchivo);
                    System.out.println("Calculando datos...");
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 3);
        scanner.close();
    }

}