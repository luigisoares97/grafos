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
        BufferedReader in = new BufferedReader(new InputStreamReader
                                (new FileInputStream("materias.in")));

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


        // Recriando BufferedReader para leitura do teclado (System.in)
        in = new BufferedReader(new InputStreamReader(System.in));

        // MENU
        out.println("========== Menu de disciplinas ==========");
        List<String> materias = grafo.getDisciplinas();
        int i = 1;
        for (String materia : materias) {
            out.println(i + ". " + materia);
            i++;
        }

        // Lendo do usuário uma matéria a ser analisada
        out.print("Insira a opção referente à matéria: ");
        int opcao = Integer.parseInt(in.readLine());

        // Mostrando pre-requisitos da disciplina lida
        String materia = materias.get(opcao - 1);
        List<String> preRequisitos = grafo.getPreRequisitos(materia);
        StringBuilder stringBuilder = new StringBuilder();

        for (String preRequisito : preRequisitos) {
            stringBuilder.append(preRequisito + ", ");
        }
        
        // Retira ", " do final
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        } else {
            stringBuilder.append("não possui");
        }
                
        out.println("\nPré-requisitos: " + stringBuilder);

        // Mostrando disciplinas que dependem da disciplina lida
        List<String> disciplinas = grafo.getDependentes(materia);
        stringBuilder = new StringBuilder();

        for (String disciplina : disciplinas) {
            stringBuilder.append(disciplina + ", ");
        }

        // Retira ", " do final
        // Necessário verificar tamanho para casos de nenhuma disciplina
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        } else {
            stringBuilder.append("não possui");
        }

        out.println("Matérias depentes: " + stringBuilder);
    }
}
