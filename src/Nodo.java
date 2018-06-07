import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class Nodo {
    // estaticas
    private String nome;
    private int peso;
    private LinkedHashMap<String, Aresta> filhos = new LinkedHashMap<>();

    // dinamicas
    private char status;
    private BigDecimal pesoAcumulado;

    public Nodo(String nome, int peso) {
        this.status = 'N';
        this.nome = nome;
        this.peso = peso;
        this.pesoAcumulado = new BigDecimal(peso);
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

    public char getStatus() {
        return status;
    }

    public void setStatusOcupado() {
        this.status = 'O';
    }

    public void setStatusVisitado(){
        this.status = 'V';
    }

    public BigDecimal getPesoAcumulado(){
        if(status == 'O'){
            try {
                throw new CycleExpection(nome);
            } catch (CycleExpection cycleExpection) {
                System.err.println(cycleExpection);
                System.exit(1);
            }
        }
        if(status == 'N') {
            setStatusOcupado();
            if (temFilhos()) {
                for (Aresta a : filhos.values()) {
                    pesoAcumulado = pesoAcumulado.add(a.getPesoTotal());
                }
            }
            setStatusVisitado();
        }
        return pesoAcumulado; // se for folha retorna o valor dele
    }

    public void setPesoAcumulado(BigDecimal pesoAcumulado) {
        this.pesoAcumulado = pesoAcumulado;
    }

    @Override
    public String toString() {
        return nome + "(" + peso + ")";
    }
}
