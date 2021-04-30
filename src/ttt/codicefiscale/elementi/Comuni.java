/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;

/**
 * Rappresenta l'elemento "comuni"
 *
 * @author TTT
 */
@Element(Name = "comuni", CanHaveValue = false)
public class Comuni extends XMLElement {

    public Comuni() {
        super("comuni");
    }

}
