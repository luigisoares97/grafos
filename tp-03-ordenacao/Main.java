/**
 * Programa principal referente ao trabalho extra
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
        /* 
         * Criando BufferedReader para leitura do arquivo "pub.in".
         * O arquivo de entrada deve conter os dados na formatação:
         * Matéria;Pré-requisito 1,Pré-requisito 2,Pré-requisito 3...
         */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Criando PrintStream para escrita, com autoFlush
        PrintStream out = new PrintStream(System.out, true);

        // Criando grafo
        Grafo grafo = new Grafo();

        // Lendo disciplinas
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            // Lista de disciplina e pre-requisitos
            List<String> disciplinas = Arrays.asList(line.split("[;,]"));

            // Inserindo no grafo
            grafo.inserir(disciplinas);
        }

        // Fecha BufferedReader
        in.close();

        List<String> listaOrdenada = grafo.ordenacaoKahn();
        for (String disciplina : listaOrdenada) {
            if (listaOrdenada.indexOf(disciplina) < listaOrdenada.size() - 1) {
                out.print(disciplina + " - ");
            } else {
                out.print(disciplina);
            }
        }
    }
}
