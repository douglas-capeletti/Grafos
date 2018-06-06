import java.util.LinkedHashMap;

public class Nodo {
    // estaticas
    private String nome;
    private int peso;
    private LinkedHashMap<String, Aresta> filhos = new LinkedHashMap<>();

    // dinamicas
    private double pesoAcumulado;
    private boolean visitado = false;

    public Nodo(String nome, int peso) {
        this.nome = nome;
        this.peso = peso;
        this.pesoAcumulado = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean temFilhos(){
        return filhos.size() > 0;
    }

    public void addFilho(Aresta a){
        this.filhos.put(a.getNodo().getNome(), a);
    }

    public LinkedHashMap<String, Aresta> getFilhos() {
        return filhos;
    }

    public void setFilhos(LinkedHashMap<String, Aresta> filhos) {
        this.filhos = filhos;
    }

    public double getPesoAcumulado() {
        if(!visitado) {
            if (temFilhos()) {
                for (Aresta a : filhos.values()) {
                    pesoAcumulado += a.getPesoTotal();
                }
            }
            setVisitado();
        }
        return pesoAcumulado; // se for folha retorna o valor dele
    }

    public void setPesoAcumulado(double pesoAcumulado) {
        this.pesoAcumulado = pesoAcumulado;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado() {
        this.visitado = true;
    }

    @Override
    public String toString() {
        return nome + "(" + peso + ")";
    }
}
