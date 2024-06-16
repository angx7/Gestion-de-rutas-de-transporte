import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GrafoNoDirigido {
    // private ArrayList<ArrayList<String>> listaAdyacencia;
    private int numVertices;
    NodoGrafo cabeza;
    private List<NodoGrafo> listaAdyacencia;

    public GrafoNoDirigido(int tamanoInicial) {
        listaAdyacencia = new ArrayList<>(tamanoInicial);

        // Inicializar la lista con nodos vacíos para cada vértice potencial
        for (int i = 0; i < tamanoInicial; i++) {
            listaAdyacencia.add(new NodoGrafo("", null));
        }
    }

    public void agregarVertice(String nombreVertice) {
        try {
            if (buscarIndice(nombreVertice) != -1) {
                throw new Exception("La ciudad que intentas agregar ya existe");
            } else {
                if (numVertices <= listaAdyacencia.size()) {
                    listaAdyacencia.add(new NodoGrafo(nombreVertice, null));
                    numVertices++;
                }
                throw new Exception("Ciudad agregada correctamente");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Regresando al menú principal...");
        }
    }

    public int buscarIndice(String nombreVertice) {
        for (int i = 0; i < numVertices; i++) {
            if (i < listaAdyacencia.size() && listaAdyacencia.get(i).getId().equals(nombreVertice)) {
                return i;
            }
        }
        return -1; // Vertice no encontrado
    }

    public void agregarArista(String origen, String destino) {
        try {
            int indiceOrigen = buscarIndice(origen);
            int indiceDestino = buscarIndice(destino);

            if (indiceOrigen == -1 || indiceDestino == -1) {
                throw new Exception("No se encontró una o ambas ciudades.");
            }

            listaAdyacencia.get(indiceOrigen).getAdyacentes().add(new NodoGrafo(destino, null));
            listaAdyacencia.get(indiceDestino).getAdyacentes().add(new NodoGrafo(origen, null)); // Para grafos no
                                                                                                 // dirigidos

            throw new Exception("Ruta agregada correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Regresando al menú principal...");
        }
    }

    public void eliminarVertice(String nombreVertice) {
        try {
            int indice = buscarIndice(nombreVertice);
            if (indice == -1) {
                throw new Exception("No se encontró la ciudad que intentas eliminar");
            }

            listaAdyacencia.remove(indice);
            numVertices--;
            for (NodoGrafo nodo : listaAdyacencia) {
                nodo.getAdyacentes().removeIf(n -> n.getId().equals(nombreVertice));
            }
            throw new Exception("Ciudad eliminada correctamente");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Regresando al menú principal...");
        }
    }

    public void eliminarArista(String origen, String destino) {
        try {
            int indiceOrigen = buscarIndice(origen);
            int indiceDestino = buscarIndice(destino);

            if (indiceOrigen == -1 || indiceDestino == -1) {
                throw new Exception("No se encontró una o ambas ciudades.");
            }

            listaAdyacencia.get(indiceOrigen).getAdyacentes().removeIf(nodo -> nodo.getId().equals(destino));
            listaAdyacencia.get(indiceDestino).getAdyacentes().removeIf(nodo -> nodo.getId().equals(origen));
            throw new Exception("Ruta eliminada correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Regresando al menú principal...");
        }
    }

    public void realizarRecorridos(String verticeInicio) {
        try {
            bfs(verticeInicio); // Asume que bfs ahora lanza la excepción
            dfs(verticeInicio); // Ya está configurado para lanzar la excepción
        } catch (Exception e) {
            System.out.println("Error al realizar los recorridos" + e.getMessage());
        } finally {
            System.out.println("\nRegresando al menú principal...");
        }
    }

    public void dfs(String verticeInicio) {
        boolean[] visitado = new boolean[numVertices];
        Stack<String> stack = new Stack<>();
        stack.push(verticeInicio);

        while (!stack.isEmpty()) {
            String vertice = stack.pop();
            int indiceVertice = buscarIndice(vertice);

            if (!visitado[indiceVertice]) {
                visitado[indiceVertice] = true;
                System.out.print(vertice + " ");

                for (NodoGrafo nodo : listaAdyacencia.get(indiceVertice).getAdyacentes()) {
                    if (!visitado[buscarIndice(nodo.getId())]) {
                        stack.push(nodo.getId());
                    }
                }
            }
        }
    }

    public void bfs(String verticeInicio) {
        boolean[] visitado = new boolean[numVertices];
        Queue<String> queue = new LinkedList<>();
        queue.add(verticeInicio);

        while (!queue.isEmpty()) {
            String vertice = queue.poll();
            int indiceVertice = buscarIndice(vertice);

            if (!visitado[indiceVertice]) {
                visitado[indiceVertice] = true;
                System.out.print(vertice + " ");

                for (NodoGrafo nodo : listaAdyacencia.get(indiceVertice).getAdyacentes()) {
                    if (!visitado[buscarIndice(nodo.getId())]) {
                        queue.add(nodo.getId());
                    }
                }
            }
        }
    }

}
