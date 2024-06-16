import java.util.ArrayList;
import java.util.List;

public class NodoGrafo {
    String destino;
    List<NodoGrafo> adyacentes;

    public NodoGrafo(String destino, NodoGrafo siguiente) {
        this.destino = destino;
        this.adyacentes = new ArrayList<>();
    }

    // Getters y setters
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<NodoGrafo> getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(List<NodoGrafo> adyacentes) {
        this.adyacentes = adyacentes;
    }
}