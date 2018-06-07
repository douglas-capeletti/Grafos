import java.math.BigDecimal;

public class Aresta {
    private int pesoAresta;
    private Nodo nodo;

    private BigDecimal pesoTotal;

    public Aresta(int pesoAresta, Nodo nodo) {
        this.pesoAresta = pesoAresta;
        this.nodo = nodo;
    }

    public int getPesoAresta() {
        return pesoAresta;
    }

    public void setPesoAresta(int pesoAresta) {
        this.pesoAresta = pesoAresta;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public BigDecimal getPesoTotal() {
        return this.pesoTotal = nodo.getPesoAcumulado().multiply(new BigDecimal(pesoAresta));
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    @Override
    public String toString() {
        return " <" + pesoAresta + ">-{" + nodo.toString();
    }
}
