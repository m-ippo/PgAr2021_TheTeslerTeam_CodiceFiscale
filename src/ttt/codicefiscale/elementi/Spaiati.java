/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.elementi;

import ttt.utils.engines.enums.MethodType;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.engines.interfaces.EngineMethod;
import ttt.utils.xml.engine.annotations.Tag;

/**
 * Rappresenta l'elemento "spaiati"
 *
 * @author TTT
 */
@Element(Name = "spaiati")
public class Spaiati extends XMLElement {

    private Integer size = 0;

    public Spaiati() {
        super("spaiati");
    }

    @EngineMethod(MethodType = MethodType.SET)
    @Tag(Name = "numero")
    public void setSize(String value) {
        size = Integer.parseInt(value);
    }

    @EngineMethod(MethodType = MethodType.GET)
    @Tag(Name = "numero", ValueType = Integer.class)
    public Integer setSize() {
        return size;
    }
}
