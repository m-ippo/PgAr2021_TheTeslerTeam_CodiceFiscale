/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.flow;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ttt.codicefiscale.elementi.Codice;
import ttt.codicefiscale.elementi.Codici;
import ttt.codicefiscale.elementi.Invalidi;
import ttt.codicefiscale.elementi.Output;
import ttt.codicefiscale.elementi.Persona;
import ttt.codicefiscale.elementi.Persone;
import ttt.codicefiscale.elementi.Spaiati;
import ttt.codicefiscale.utilita.ControlloCodiceFiscale;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLTag;
import ttt.utils.xml.engine.interfaces.IXMLElement;

import static ttt.codicefiscale.utilita.ControlloCodiceFiscale.getConvertitore;

/**
 *
 * @author TTT
 */
public class ControlloElementi {

    public ControlloElementi() {
    }

    HashMap<String, Persona> codici_persone = new HashMap<>();

    public void riempiTabella(XMLDocument persone) {
        codici_persone.clear();
        persone.getFirstElement("persone").getElements().forEach((persona) -> {
            Persona p = (Persona) persona;
            String codiceFiscale = getConvertitore().creaCodicePersona(p);
            codici_persone.put(codiceFiscale, p);
        });
    }

    public int getSize() {
        return codici_persone.size();
    }

    ArrayList<Codice> invalidi = new ArrayList<>();
    ArrayList<Codice> spaiati = new ArrayList<>();
    ArrayList<Codice> validi = new ArrayList<>();

    public void controllaCodici(XMLDocument codici) {
        invalidi.clear();
        spaiati.clear();
        List<IXMLElement> elements = codici.getFirstElement("codici").getElements();
        elements.stream().map(codice -> (Codice) codice).forEachOrdered(c -> {
            if (!ControlloCodiceFiscale.Controllo(c)) {
                invalidi.add(c);
            } else {
                if (!codici_persone.containsKey(c.getValue())) {
                    spaiati.add(c);
                }else{
                    validi.add(c);
                }
            }
        });
    }

    public XMLDocument generaOutput(File file_output) {
        XMLDocument document = new XMLDocument(file_output);
        Output tab_output = new Output();

        Persone persone = new Persone();
        XMLTag tag_numero_persone = new XMLTag("numero");
        tag_numero_persone.setValue("" + codici_persone.size());
        persone.addTag(tag_numero_persone);

        codici_persone.keySet().forEach(codice -> {
            Persona p111 = codici_persone.get(codice);
            Codice codice_fiscale = new Codice();
            codice_fiscale.setValue(validi.stream().filter((cod) -> {
                return cod.getValue().equals(codice);
            }).findAny().isPresent() ? codice : "ASSENTE");
            p111.addSubElement(codice_fiscale);
            persone.addSubElement(p111);
        });

        tab_output.addSubElement(persone);

        Codici codici = new Codici();

        Invalidi invalido = new Invalidi();
        XMLTag tag_numero_invalidi = new XMLTag("numero");
        tag_numero_invalidi.setValue("" + invalidi.size());
        invalido.addTag(tag_numero_invalidi);
        invalidi.forEach(cod -> {
            invalido.addSubElement(cod);
        });
        codici.addSubElement(invalido);

        Spaiati spaiato = new Spaiati();
        XMLTag tag_numero_spaiati = new XMLTag("numero");
        tag_numero_spaiati.setValue("" + spaiati.size());
        spaiato.addTag(tag_numero_spaiati);
        spaiati.forEach(cod -> {
            spaiato.addSubElement(cod);
        });
        codici.addSubElement(spaiato);
        tab_output.addSubElement(codici);
        document.addSubElement(tab_output);
        return document;
    }
}
