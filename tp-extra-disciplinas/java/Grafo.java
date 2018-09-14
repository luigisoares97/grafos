/**
 * Grafo referente à disciplinas e seus pré-requisitos,
 * implementado com listas.
 *
 * A Lista contém Listas de String que carregam as disciplinas
 * e seus pré requisitos. Cada lista de disciplinas possui o 
 * formato:
 * posição 0 -> matéria
 * restante -> pré-requisitos
 *
 * @author Luigi D. C. Soares
 * version 1.0
 */

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Grafo {
    private List<List<String>> grafo;

    /**
     * Construtor do Grafo
     */
    public Grafo() {
        // Inicia um ArrayList vazio
        grafo = new ArrayList<>();
    }

    /**
     * Insere uma lista de disciplinas no grafo,
     * sendo a 1º posição referente à disciplina,
     * e as demais referentes aos pré-requisitos
     *
     * @param disciplinas List<String> Lista de disciplinas
     */
    public void inserir(List<String> disciplinas) {
        grafo.add(disciplinas);
    }

    /**
     * Retorna todas as matérias do grafo
     * @return grafo List<String>
     */
    public List<String> getDisciplinas() {
        List<String> disciplinas = new ArrayList<>();

        for (List<String> lista : grafo) {
            disciplinas.add(lista.get(0));
        }

        return disciplinas;
    }

    /**
     * Mostra a lista, com suas matérias e respectivos
     * pré-requisitos
     */
    public void mostrar() {
        // Criando PrintStream para escrita, com autoFlush
        PrintStream out = new PrintStream(System.out, true);

        // Disciplinas mostradas no formato: disciplina: pré-requisitos
        for (List<String> disciplinas : grafo) {
            out.print(disciplinas.get(0) + ": ");

            // StringBuilder para guardar pre-requisitos
            StringBuilder preRequisitos = new StringBuilder();

            // Criando Iterator a partir da primeira posição
            ListIterator<String> iterator = disciplinas.listIterator(1);
            while (iterator.hasNext()) {
                String disciplina = iterator.next();
                preRequisitos.append(disciplina + ", "); // Preenche StringBuilder
            }

            // Retira ", " do final
            if (disciplinas.size() > 1) {
                preRequisitos.delete(preRequisitos.length() - 2, preRequisitos.length());
            }

            // Mostra pre-requisitos
            out.println(preRequisitos);
        }
    }

    /**
     * Retorna o indice do array que contem uma determinada disciplina,
     * ou -1 caso não exista
     *
     * @param disciplina String Disciplina procurada
     * @return indice do array referente à disciplina ou -1 se não encontrar
     */
    public int indexOf(String disciplina) {
        int index = -1;
        
        // Percorre grafo para procurar disciplina
        for (List<String> disciplinas : grafo) {
            // Verifica se disciplina foi encontrada
            if (disciplinas.get(0).equals(disciplina)) {
                index = grafo.indexOf(disciplinas); // Index da disciplina no grafo
                break; // Sai do loop
            }
        }
                
        return index;
    }

    /**
     * Retorna os pre-requisitos de uma determinada disciplina
     * @param disciplina String Disciplina a ser analisada
     * @return Lista contendo os pre-requisitos da disciplina
     * @throws Exception se disciplina não existe
     */
    public List<String> getPreRequisitos(String disciplina) throws Exception {
        // Indice da disciplina na lista
        int index = indexOf(disciplina);

        // Se disciplina não encontrada, lança uma Exception
        if (index == -1) {
            throw new Exception("Disciplina não encontrada!!!");
        }

        // Se disciplina existe, recupera lista
        List<String> preRequisitos = grafo.get(index);

        // Recupera apenas pre-requisitos (a partir da posição 1)
        preRequisitos = preRequisitos.subList(1, preRequisitos.size());
        
        return preRequisitos;
    }

    /**
     * Retorna as disciplinas que dependem de determinado pre-requisito
     * @param preRequisito String Pre-requisito a ser analisado
     * @return Lista contendo disciplinas dependentes
     */
    public List<String> getDependentes(String preRequisito) {
        // Lista vazia para disciplinas
        List<String> dependentes = new ArrayList<>();

        // Percorre grafo, procurando disciplinas que necessitem do pre-requisito
        for (List<String> lista : grafo) {
            // Se lista tiver o pre-requisito, adiciona à lista de disciplinas dependentes
            // OBS: pre-requisitos estão a partir da posição 1 da lista
            if (lista.contains(preRequisito) && lista.indexOf(preRequisito) != 0) {
                dependentes.add(lista.get(0)); // Matéria depende do pre-requisito
            }
        }

        return dependentes;
    }
}