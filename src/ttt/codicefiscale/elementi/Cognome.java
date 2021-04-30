/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;

/**
 * Rappresenta l'elemento "cognome"
 *
 * @author TTT
 */
@Element(Name = "cognome", CanHaveTags = false)
public class Cognome extends XMLElement {

    public Cognome() {
        super("cognome");
    }

}
