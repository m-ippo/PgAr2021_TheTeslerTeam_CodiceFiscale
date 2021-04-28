/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;

/**
 *
 * @author gabri
 */
@Element(Name = "comune_nascita", CanHaveTags = false)
public class ComuneNascita extends XMLElement {

    public ComuneNascita() {
        super("comune_nascita");
    }
}
