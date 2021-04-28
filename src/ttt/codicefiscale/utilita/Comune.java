package ttt.codicefiscale.utilita;

public class Comune {
    private String nome;
    private String codice_comune;

    public Comune(String nome, String codice_comune) {
        this.nome = nome;
        this.codice_comune = codice_comune;
    }

    public String getNome() {
        return nome;
    }

    public String getCodiceComune() {
        return codice_comune;
    }

    @Override
    public String toString() {
        return String.format("[COMUNE]\tNome= %25s\tCodice= %s",nome, codice_comune);
    }
}
