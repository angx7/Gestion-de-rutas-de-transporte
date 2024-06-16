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
            if (i < listaAdyacencia.size() && listaAdyacencia.get(i).getDestino().equals(nombreVertice)) {
                return i;
            }
        }
        return -1; // Vertice no encontrado
    }

    public int buscarCamino(String origen, String destino) {
        int indiceOrigen = buscarIndice(origen);
        int indiceDestino = buscarIndice(destino);

        if (indiceOrigen == -1 || indiceDestino == -1) {
            return -1;
        }

        for (NodoGrafo nodo : listaAdyacencia.get(indiceOrigen).getAdyacentes()) {
            if (nodo.getDestino().equals(destino)) {
                return 1;
            }
        }

        return 0;
    }

    public void agregarArista(String origen, String destino) {
        try {
            int indiceOrigen = buscarIndice(origen);
            int indiceDestino = buscarIndice(destino);

            if (buscarCamino(origen, destino) == -1) {
                throw new Exception("No se encontró una o ambas ciudades.");
            }

            if (buscarCamino(origen, destino) == 0) {

                listaAdyacencia.get(indiceOrigen).getAdyacentes().add(new NodoGrafo(destino, null));
                listaAdyacencia.get(indiceDestino).getAdyacentes().add(new NodoGrafo(origen, null));

                throw new Exception("Ruta agregada correctamente");
            } else {
                throw new Exception("Error: La ruta ya existe");
            }

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
                nodo.getAdyacentes().removeIf(n -> n.getDestino().equals(nombreVertice));
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

            if (buscarCamino(origen, destino) == -1) {
                throw new Exception("No se encontró una o ambas ciudades.");
            }

            if (buscarCamino(origen, destino) == 1) {
                listaAdyacencia.get(indiceOrigen).getAdyacentes().removeIf(nodo -> nodo.getDestino().equals(destino));
                listaAdyacencia.get(indiceDestino).getAdyacentes().removeIf(nodo -> nodo.getDestino().equals(origen));
                throw new Exception("Ruta eliminada correctamente");
            } else {
                throw new Exception("Error: La ruta no existe");
            }

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
            System.out.println("Error al realizar los recorridos: " + e.getMessage());
        } finally {
            System.out.println("\nRegresando al menú principal...");
        }
    }

    private void dfs(String verticeInicio) throws Exception {
        boolean[] visitado = new boolean[numVertices];
        Stack<String> stack = new Stack<>();
        stack.push(verticeInicio);

        if (buscarIndice(verticeInicio) == -1) {
            throw new Exception("No se encontró el vértice de inicio " + verticeInicio + " en el grafo.");
        }

        System.out.println("\nRecorrido DFS desde el origen " + verticeInicio + ":");

        while (!stack.isEmpty()) {
            String vertice = stack.pop();
            int indiceVertice = buscarIndice(vertice);

            if (!visitado[indiceVertice]) {
                visitado[indiceVertice] = true;
                System.out.print(vertice + " ");

                for (NodoGrafo nodo : listaAdyacencia.get(indiceVertice).getAdyacentes()) {
                    if (!visitado[buscarIndice(nodo.getDestino())]) {
                        stack.push(nodo.getDestino());
                    }
                }
            }
        }
    }

    private void bfs(String verticeInicio) throws Exception {
        boolean[] visitado = new boolean[numVertices];
        Queue<String> queue = new LinkedList<>();
        queue.add(verticeInicio);

        if (buscarIndice(verticeInicio) == -1) {
            throw new Exception("No se encontró el vértice de inicio " + verticeInicio + " en el grafo.");
        }

        System.out.println("\nRecorrido BFS desde el origen " + verticeInicio + ":");
        while (!queue.isEmpty()) {
            String vertice = queue.poll();
            int indiceVertice = buscarIndice(vertice);

            if (!visitado[indiceVertice]) {
                visitado[indiceVertice] = true;
                System.out.print(vertice + " ");

                for (NodoGrafo nodo : listaAdyacencia.get(indiceVertice).getAdyacentes()) {
                    if (!visitado[buscarIndice(nodo.getDestino())]) {
                        queue.add(nodo.getDestino());
                    }
                }
            }
        }
    }
}

class Exception extends java.lang.Exception {
    public Exception(String message) {
        super(message);
    }
}