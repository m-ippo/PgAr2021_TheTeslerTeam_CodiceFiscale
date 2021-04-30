package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.xml.engine.annotations.EngineMethod;
import ttt.utils.xml.engine.enums.MethodType;

/**
 * Rappresenta l'elemento "comune"
 *
 * @author TTT
 */
@Element(Name = "comune", CanHaveTags = false)
public class Comune extends XMLElement {

    private String nome;
    private String codice_comune;

    public Comune() {
        super("comune");
    }

    @EngineMethod(MethodType = MethodType.CALC)
    public void init() {
        nome = getFirstElement("nome").getValue();
        codice_comune = getFirstElement("codice").getValue();
    }

    public String getNome() {
        return nome;
    }

    public String getCodiceComune() {
        return codice_comune;
    }

    @Override
    public String toString() {
        return String.format("[COMUNE]\tNome= %25s\tCodice= %s", nome, codice_comune);
    }
}
