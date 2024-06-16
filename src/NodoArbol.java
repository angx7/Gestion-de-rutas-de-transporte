public class NodoArbol {
    String palabra;
    NodoArbol izquierda;
    NodoArbol derecha;

    public NodoArbol(String palabra) {
        this.palabra = palabra;
        this.izquierda = this.derecha = null;
    }
}