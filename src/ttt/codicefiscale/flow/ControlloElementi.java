/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ttt.codicefiscale.elementi.Codice;
import ttt.codicefiscale.elementi.Persona;
import ttt.codicefiscale.utilita.ControlloCodiceFiscale;
import ttt.codicefiscale.utilita.ConvertiCodice;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.engine.interfaces.IXMLElement;

/**
 *
 * @author TTT
 */
public class ControlloElementi {

    public ControlloElementi() {
    }

    HashMap<String, Persona> codici_persone = new HashMap<>();

    public void riempiTabella(XMLDocument persone) {
        persone.getFirstElement("persone").getElements().forEach((persona) -> {
            Persona p = (Persona) persona;
            String codiceFiscale = ConvertiCodice.creaCodicePersona(p);
            codici_persone.put(codiceFiscale, p);
        });
    }

    ArrayList<Codice> invalidi = new ArrayList<>();
    ArrayList<Codice> spaiati = new ArrayList<>();

    public void controllaCodici(XMLDocument document) {
        invalidi.clear();
        spaiati.clear();
        List<IXMLElement> elements = document.getFirstElement("codici").getElements();
        elements.stream().map(codice -> (Codice) codice).forEachOrdered(c -> {
            if (!ControlloCodiceFiscale.Controllo(c)) {
                invalidi.add(c);
            }else{
                if(!codici_persone.containsKey(c.getValue())){
                    spaiati.add(c);
                }
            }
        });
    }
}
