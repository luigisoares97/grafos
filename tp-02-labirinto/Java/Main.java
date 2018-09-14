/**
 * Programa principal referente ao trabalho do labirinto
 * de Grafos.
 *
 * @author Luigi D. C. Soares
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws Exception, IOException {
        // Criando BufferedReader para leitura do arquivo "pub.in".
        BufferedReader in = new BufferedReader(new InputStreamReader
                                (System.in));

        // Criando PrintStream para escrita, com autoFlush
        PrintStream out = new PrintStream(System.out, true);

        /*
         * Lendo boolean para definir se é grafo ou dígrafo.
         * OBS: sem utilidade para esse trabalho, já que o
         * labirinto é um grafo não-direcionado. 
         */
        boolean digrafo = Boolean.parseBoolean(in.readLine());

        // Lendo número de vértices do labirinto
        int numVertices = Integer.parseInt(in.readLine());

        // Criando grafo
        Labirinto labirinto = new Labirinto(numVertices);

        // Lendo arestas e inserindo no grafo
        for (String aresta = in.readLine(); aresta.equals("FIM") == false; aresta = in.readLine()) {
            // Array com dados da aresta
            String[] dadosAresta = aresta.split(",");

            // Dados da aresta a serem inseridos
            int origem = Integer.parseInt(dadosAresta[0]);
            int destino = Integer.parseInt(dadosAresta[1]);
            int peso = Integer.parseInt(dadosAresta[2]);

            // Inserindo aresta no grafo
            labirinto.inserir(origem, destino, peso);
        }

        // Fechando BufferedReader
        in.close();

        // Recuperando caminho do labirinto
        List<Integer> caminho = labirinto.getCaminho();

        // Mostrando caminho
        for (int i = 0; i < caminho.size() - 1; i++) {
            out.println(caminho.get(i) + " --> " + caminho.get(i + 1));
        }

        // Fechando PrintStream
        out.close();
    }
}
