import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionRutas {
    static Scanner scanner = new Scanner(System.in);
    static String nombreCiudad, origen, destino;
    public static void main(String[] args) throws Exception {
        GrafoNoDirigido grafo = new GrafoNoDirigido(0);
        ArbolBinario arbol = new ArbolBinario();

        System.out.println("Menú de Gestion de Rutas de Transporte: ");
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        // Agregar ciudad
                        System.out.print("Ingrese el nombre de la ciudad: ");
                        nombreCiudad = scanner.next();
                        grafo.agregarVertice(nombreCiudad);
                        arbol.insertar(nombreCiudad);
                        break;
                    case 2:
                        // Eliminar ciudad
                        System.out.print("Ingrese el nombre de la ciudad: ");
                        nombreCiudad = scanner.next();
                        grafo.eliminarVertice(nombreCiudad);
                        arbol.eliminar(nombreCiudad);
                        break;
                    case 3:
                        // Agregar ruta
                        System.out.print("Ingrese el nombre de la ciudad de origen: ");
                        origen = scanner.next();
                        System.out.print("Ingrese el nombre de la ciudad de destino: ");
                        destino = scanner.next();
                        grafo.agregarArista(origen, destino);
                        break;
                    case 4:
                        // Eliminar ruta
                        System.out.print("Ingrese el nombre de la ciudad de origen: ");
                        origen = scanner.next();
                        System.out.print("Ingrese el nombre de la ciudad de destino: ");
                        destino = scanner.next();
                        grafo.eliminarArista(origen, destino);
                        break;
                    case 5:
                        // Mostrar recorridos del Árbol
                        System.out.println("Recorridos del árbol:");
                        System.out.println("InOrden: ");
                        arbol.recorrerInOrden();
                        System.out.println("\nPreOrden: ");
                        arbol.recorrerPreOrden();
                        System.out.println("\nPostOrden: ");
                        arbol.recorrerPostOrden();
                        break;
                    case 6:
                        // Mostar recorridos del Grafo
                        System.out.print("Ingrese el nombre de la ciudad de origen: ");
                        nombreCiudad = scanner.next();
                        System.out.println("Recorridos del grafo:");
                        grafo.realizarRecorridos(nombreCiudad);
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");

                        break;
                    default:
                        throw new OpcionInvalidaException("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: No se ingresó un número entero, por favor intente de nuevo.");
                scanner.nextLine(); // Clear the input buffer
            } catch (OpcionInvalidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 7);

    }

    public static void mostrarMenu() {
        System.out.print(
                "\n\n\tMenú de Gestión de Rutas de Transporte:\n\n1. Agregar ciudad\n2. Eliminar Ciudad\n3. Añadir Ruta\n4. Eliminar ruta\n5. Mostrar Recorridos del Árbol\n6. Mostrar Recorridos del Grafo\n7. Salir\nIngrese una opción: ");
    }
}

class OpcionInvalidaException extends Exception {
    public OpcionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
