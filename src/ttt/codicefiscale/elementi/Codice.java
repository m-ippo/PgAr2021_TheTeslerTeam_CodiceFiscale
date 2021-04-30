package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;

/**
 * Rappresenta l'elemento "codice"
 * @author TTT
 */
@Element(Name = "codice", CanHaveTags = false)
public class Codice extends XMLElement {

    public Codice() {
        super("codice");
    }

}
