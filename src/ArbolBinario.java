public class ArbolBinario {
    private NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar(String palabra) {
        raiz = insertarRec(raiz, palabra);
    }

    private NodoArbol insertarRec(NodoArbol raiz, String palabra) {
        if (raiz == null) {
            raiz = new NodoArbol(palabra);
            return raiz;
        }

        if (palabra.compareTo(raiz.palabra) < 0) {
            raiz.izquierda = insertarRec(raiz.izquierda, palabra);
        } else if (palabra.compareTo(raiz.palabra) > 0) {
            raiz.derecha = insertarRec(raiz.derecha, palabra);
        }

        return raiz;
    }

    public boolean buscar(String palabra) {
        return buscarRec(raiz, palabra) != null;
    }

    public NodoArbol buscarRec(NodoArbol raiz, String palabra) {
        if (raiz == null || raiz.palabra.equals(palabra)) {
            return raiz;
        }

        if (palabra.compareTo(raiz.palabra) < 0) {
            return buscarRec(raiz.izquierda, palabra);
        }else{
            return buscarRec(raiz.derecha, palabra);
        }
    }

    public void eliminar(String palabra) {
        raiz = eliminarRec(raiz, palabra);
    }

    private NodoArbol eliminarRec(NodoArbol raiz, String palabra) {
        if (raiz == null) {
            return raiz;
        }

        if (palabra.compareTo(raiz.palabra) < 0) {
            raiz.izquierda = eliminarRec(raiz.izquierda, palabra);
        } else if (palabra.compareTo(raiz.palabra) > 0) {
            raiz.derecha = eliminarRec(raiz.derecha, palabra);
        } else {
            if (raiz.izquierda == null) {
                return raiz.derecha;
            } else if (raiz.derecha == null) {
                return raiz.izquierda;
            }

            raiz.palabra = minimoValor(raiz.derecha);
            raiz.derecha = eliminarRec(raiz.derecha, raiz.palabra);
        }

        return raiz;
    }

    private String minimoValor(NodoArbol raiz) {
        String min = raiz.palabra;
        while (raiz.izquierda != null) {
            min = raiz.izquierda.palabra;
            raiz = raiz.izquierda;
        }
        return min;
    }

    public void recorrerInOrden() {
        recorrerInOrdenRec(raiz);
    }

    private void recorrerInOrdenRec(NodoArbol raiz) {
        if (raiz != null) {
            recorrerInOrdenRec(raiz.izquierda);
            System.out.print(raiz.palabra + " ");
            recorrerInOrdenRec(raiz.derecha);
        }
    }

    public void recorrerPreOrden() {
        recorrerPreOrdenRec(raiz);
    }

    private void recorrerPreOrdenRec(NodoArbol raiz) {
        if (raiz != null) {
            System.out.print(raiz.palabra + " ");
            recorrerPreOrdenRec(raiz.izquierda);
            recorrerPreOrdenRec(raiz.derecha);
        }
    }

    public void recorrerPostOrden() {
        recorrerPostOrdenRec(raiz);
    }

    private void recorrerPostOrdenRec(NodoArbol raiz) {
        if (raiz != null) {
            recorrerPostOrdenRec(raiz.izquierda);
            recorrerPostOrdenRec(raiz.derecha);
            System.out.print(raiz.palabra + " ");
        }
    }
    
}
