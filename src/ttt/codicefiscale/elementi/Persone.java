/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.elementi;

import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.xml.engine.annotations.EngineMethod;
import ttt.utils.xml.engine.annotations.Tag;
import ttt.utils.xml.engine.enums.MethodType;

/**
 *
 * @author gabri
 */
@Element(Name = "persone", CanHaveValue = false)
public class Persone extends XMLElement {

    private Integer size = 0;

    public Persone() {
        super("persone");
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
