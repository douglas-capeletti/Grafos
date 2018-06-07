import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Maestro {

    void start(){
        String caminho = exibeEscolha();
        if(caminho == null){
            Scanner in = new Scanner(System.in);
            System.out.println("Insira o caminho completo do arquivo:");
            caminho = in.next();
        }
        System.out.println();
        Long inicial = System.currentTimeMillis();
        System.out.println("\nRESULTADO FINAL: " + calculadoraGrafo(leitura(caminho)));
        System.out.println("Tempo de Execucação ( " + (System.currentTimeMillis() - inicial) + " milissegundos )");

    }

    BigDecimal calculadoraGrafo(LinkedHashMap<String, Nodo> dados){
        BigDecimal resultado = new BigDecimal(BigInteger.ZERO);
        for(Nodo g: dados.values()) {
            BigDecimal pesoNodo = g.getPesoAcumulado();
            System.out.println(g.toString() + " -> " + pesoNodo);
            if(pesoNodo.compareTo(resultado) > 0)
                resultado = pesoNodo;
        }
        return resultado;
    }

    LinkedHashMap<String, Nodo> leitura(String caminhoArquivo){
        LinkedHashMap<String, Nodo> grafo = new LinkedHashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(caminhoArquivo), Charset.forName("utf8"))) {
            String[] conteudo;
            int index = Integer.parseInt(reader.readLine());

            // Leitura dos nodos
            for(int i = 0; i< index; i++) {
                conteudo = reader.readLine().split(" ");
                grafo.put(conteudo[0] ,new Nodo(conteudo[0], Integer.parseInt(conteudo[1])));
            }

            index = Integer.parseInt(reader.readLine());

            // Leitura das arestas
            for(int i = 0; i< index; i++) {
                conteudo = reader.readLine().split(" ");
                if(grafo.containsKey(conteudo[0]) && grafo.containsKey(conteudo[1])){
                    grafo.get(conteudo[0]).addFilho(new Aresta(Integer.parseInt(conteudo[2]), grafo.get(conteudo[1])));
                }
            }
            return grafo;
        }
        catch (IOException x) {
            System.err.println("Arquivo para leitura de dados não encontrado");
        }
        return null;
    }

    void listaValores(LinkedHashMap<String, Nodo> dados){
        for(Nodo g: dados.values()){
            System.out.print(g.toString());
            for(Aresta a: g.getFilhos().values()){
                System.out.print(a.toString());
            }
            System.out.print("\n");
        }
    }

    String exibeEscolha(){
        String caminho = null;
        Scanner in = new Scanner(System.in);
        System.out.print(
            "\nEscolha o arquivo a ser processado:" +
            "\n(0)  Exemplo" +
            "\n(1)  Caso0100" +
            "\n(2)  Caso0200" +
            "\n(4)  Caso0400" +
            "\n(6)  Caso0600" +
            "\n(8)  Caso0800" +
            "\n(10) Caso1000" +
            "\nDigite outro para inserir caminho manualmente:  "
        );
        int opcao = in.nextInt();
        switch (opcao){
            case 0: caminho = "src\\casosTeste\\exemplo";
                break;
            case 1: caminho = "src\\casosTeste\\caso0100";
                break;
            case 2: caminho = "src\\casosTeste\\caso0200";
                break;
            case 4: caminho = "src\\casosTeste\\caso0400";
                break;
            case 6: caminho = "src\\casosTeste\\caso0600";
                break;
            case 8: caminho = "src\\casosTeste\\caso0800";
                break;
            case 10: caminho = "src\\casosTeste\\caso1000";
                break;
        }
        return caminho;
    }
}
