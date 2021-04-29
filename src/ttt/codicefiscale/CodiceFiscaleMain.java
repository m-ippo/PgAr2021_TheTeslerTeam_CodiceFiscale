/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale;

import ttt.utils.xml.engine.interfaces.IXMLElement;
import java.io.IOException;
import java.util.List;
import ttt.codicefiscale.flow.GestioneMenu;

/**
 *
 * @author TTT
 */
public class CodiceFiscaleMain {

    public static void main(String[] args) throws IOException {
        GestioneMenu gm = new GestioneMenu();
    }

    public static void stampa(List<IXMLElement> s) {
        s.forEach(elemento -> {
            System.out.println(elemento.getValue());
            if (elemento.hasSubElements()) {
                stampa(elemento.getElements());
            }
        });
    }
}
