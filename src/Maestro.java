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
    private String caminho = "src\\casosTeste\\";

    void start(){
        exibeEscolha();
        System.out.println();
        Long inicial = System.currentTimeMillis();
        System.out.println("\nRESULTADO FINAL: " + calculadora(leitura()));
        System.out.println("Tempo de Execucação ( " + (System.currentTimeMillis() - inicial) + " milissegundos )");
    }

    BigDecimal calculadora(LinkedHashMap<String, Nodo> dados){
        BigDecimal resultado = new BigDecimal(BigInteger.ZERO);
        for(Nodo g: dados.values()) {
            BigDecimal pesoNodo = g.getPesoAcumulado();
            System.out.println(g.toString() + " -> " + pesoNodo);
            if(pesoNodo.compareTo(resultado) > 0)
                resultado = pesoNodo;
        }
        return resultado;
    }

    LinkedHashMap<String, Nodo> leitura(){
        LinkedHashMap<String, Nodo> grafos = new LinkedHashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.caminho), Charset.forName("utf8"))) {
            String[] conteudo;
            int index = Integer.parseInt(reader.readLine());
            for(int i = 0; i< index; i++) {
                conteudo = reader.readLine().split(" ");
                grafos.put(conteudo[0] ,new Nodo(conteudo[0], Integer.parseInt(conteudo[1])));
            }
            index = Integer.parseInt(reader.readLine());
            for(int i = 0; i< index; i++) {
                conteudo = reader.readLine().split(" ");
                if(grafos.containsKey(conteudo[0]) && grafos.containsKey(conteudo[1])){
                    grafos.get(conteudo[0]).addFilho(new Aresta(Integer.parseInt(conteudo[2]), grafos.get(conteudo[1])));
                }
            }
            return grafos;
        }
        catch (IOException x) {
            x.printStackTrace();
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

    void exibeEscolha(){
        Scanner in = new Scanner(System.in);
        System.out.println("Escolha o arquivo a ser processado:" +
                "\n(0)  Exemplo" +
                "\n(1)  Caso0100" +
                "\n(2)  Caso0200" +
                "\n(4)  Caso0400" +
                "\n(6)  Caso0600" +
                "\n(8)  Caso0800" +
                "\n(10) Caso1000" +
                "\nEscolha :  "
        );
        int opcao = in.nextInt();
        switch (opcao){
            case 0:caminho += "exemplo";
                break;
            case 1:caminho += "caso0100";
                break;
            case 2:caminho += "caso0200";
                break;
            case 4:caminho += "caso0400";
                break;
            case 6:caminho += "caso0600";
                break;
            case 8:caminho += "caso0800";
                break;
            case 10:caminho += "caso1000";
                break;
            default: caminho += "exemplo";
        }
        // listaValores(leitura()); // exibicao da lista de dependencias na tela
    }
}
