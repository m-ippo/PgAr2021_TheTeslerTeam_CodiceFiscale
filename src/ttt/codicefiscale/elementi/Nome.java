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
@Element(Name = "nome", CanHaveTags = false)
public class Nome extends XMLElement {

    public Nome() {
        super("nome");
    }

}
