/**
 * Classe para representar uma aresta do grafo, composta
 * por uma disciplina e seu pré-requisito.
 * Na aresta, 'V' é o pré-requisito e 'W' a disciplina
 * dependenten de 'V'.
 *
 * @author Luigi D. C. Soares
 * @version 1.0
 */
public class Aresta {
    private final String v;
    private final String w;

    public Aresta(String v, String w) {
        this.v = v;
        this.w = w;
    }

    public String getV() {
        return this.v;
    }

    public String getW() {
        return this.w;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Aresta)) {
            return false;
        }

        Aresta aresta = (Aresta) object;

        return this.v.equals(aresta.getV()) && this.w.equals(aresta.getW()); 
    }

    @Override
    public String toString() {
        return "(" + this.v + ", " + this.w + ")";
    }
}