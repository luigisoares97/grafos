/**
 * Grafo para implementação e busca em um labirinto,
 * utilizando matriz de adjacência. Considera-se o
 * vértice 0 como a entrada do labirinto, e o último
 * vértice como a saída.
 *
 * @author Luigi D. C. Soares
 * @version 1.0
 */

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Labirinto {
    // Matriz de adjacência
    private int[][] grafo;

    // Valor nulo para pesos das arestas
    private final int NULO = Integer.MIN_VALUE;

    /**
     * Construtor do labirinto, que cria a matriz de adjacência.
     *
     * @param tamanho Quantidade de vértices existentes no grafo
     */
    public Labirinto(int tamanho) {
        this.grafo = new int[tamanho][tamanho];

        // Inicializa todas as posições (pesos das arestas) como NULO
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                this.grafo[i][j] = this.NULO;
            }
        }
    }

    /**
     * Insere um peso referente à uma aresta do grafo.
     * Inserção realizada na posição [origem][destino] e
     * na posição espelhada [destino][origem], levando em
     * consideração apenas grafos não direcionados.
     *
     * @param origem Vértice de origem
     * @param destino Vértice de destino
     * @param peso Peso da aresta
     */
    public void inserir(int origem, int destino, int peso) {
        this.grafo[origem][destino] = peso;
        this.grafo[destino][origem] = peso;
    }

    /**
     * Retorna os vértices adjacentes de determinado vértice.
     *
     * @param vertice Vértice a ser analisado
     * @return adjacentes Vértices adjacentes
     */
    private List<Integer> getAdjacentes(int vertice) {
        // ArrayList para vértices adjacentes
        List<Integer> adjacentes = new ArrayList<>();

        // Percoresre linha referente ao vértice na matriz de adjacência
        for (int i = 0; i < this.grafo.length; i++) {

            // Se aresta existir, adiciona vertice adjacente à lista
            if (this.grafo[vertice][i] != this.NULO) {
                adjacentes.add(i);
            }
        }
        
        return adjacentes;
    }

    /**
     * Algoritmo de busca em largura, para encontrar o caminho
     * do labirinto. Considera-se o vértice inicial como o vértice
     * 0 e o final como a última posição da matriz (último vértice).
     *
     * @return caminho Caminho do labirinto
     * @throws NoSuchElementException Em caso de lista vazia
     */
    public List<Integer> getCaminho() 
        throws NoSuchElementException {

        // Array para setar cores de cada vértice durante a busca
        String[] cores = new String[this.grafo.length];

        // Array para distâncias
        int[] distancias = new int[this.grafo.length];

        // Array para manter o pais de cada vértice.
        int[] pais = new int[this.grafo.length];

        // LinkedList utilizada como fila, para utilização na busca
        List<Integer> fila = new LinkedList<>();

        /*
         * Seta cores, distância e pai iniciais para cada vértice diferente
         * do vértice de origem (início do labirinto).
         */
        for (int i = 1; i < this.grafo.length; i++) {
            cores[i] = "branco";
            distancias[i] = Integer.MAX_VALUE;
            pais[i] = this.NULO;
        }

        // Seta cores, distância e pai para vértice inicial
        cores[0] = "cinza";
        distancias[0] = 0;
        pais[0] = this.NULO;

        // Coloca vértice inicial na fila
        fila.add(0);

        /*
         * Busca por largura, para encontrar caminho do labirinto
         * Enquanto fila não estiver vazia, realiza processo de busca.
         */
        while (fila.isEmpty() == false) {
            // Vértice a ser analisado
            int vertice = fila.remove(0);

            // Lista de vértices adjacentes
            List<Integer> adjacentes = this.getAdjacentes(vertice);

            // Para cada vértice adjacente, realizar processo de busca
            for (int adjacente : adjacentes) {

                // Se vértice não foi analisado ainda (cores branca)
                if (cores[adjacente].equals("branco")) {
                    // Seta cores cinza
                    cores[adjacente] = "cinza";

                    // Seta distância como distância do vértice + peso da aresta
                    distancias[adjacente] = distancias[vertice] + this.grafo[vertice][adjacente];

                    // Seta pais do vértice adjacente
                    pais[adjacente] = vertice;

                    // Coloca vértice adjacente na fila
                    fila.add(adjacente);
                }
            }

            // Seta cores do vértice analisado para preto
            cores[vertice] = "preto";
        }

        // Criando caminho para vértice final
        List<Integer> caminho = new LinkedList<>();

        // Inicia caminho com vértice final
        int vertice = this.grafo.length - 1;

        while (vertice != this.NULO) {
            // Inserção feita sempre no início
            caminho.add(0, vertice);

            // Próximo vértice do caminho
            vertice = pais[vertice];
        }

        return caminho;
    }
}