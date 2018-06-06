public class Aresta {
    private double pesoTotal;
    private int pesoAresta;
    private Nodo nodo;

    public Aresta(int pesoAresta, Nodo nodo) {
        this.pesoAresta = pesoAresta;
        this.nodo = nodo;
    }

    public double getPesoTotal() {
        return this.pesoTotal = pesoAresta * nodo.getPesoAcumulado();
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
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

    @Override
    public String toString() {
        return " <" + pesoAresta + ">-{" + nodo.toString();
    }
}
