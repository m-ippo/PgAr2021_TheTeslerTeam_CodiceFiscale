/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale;

import ttt.utils.console.input.ConsoleInput;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.interfaces.IXMLElement;
import ttt.utils.xml.io.XMLReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class CodiceFiscaleMain {

    public static void main(String[] args) throws IOException {

        XMLElement s = new XMLElement("codfis");
        s.setValue("FDANHL01Z01Z918V");
        System.out.println(Controllo.controllo(s));

        XMLReader c = new XMLReader(new File("C:\\Users\\user\\Documents\\GitHub\\PGAR_2021_TheTeslerTeam_CodiceFiscale\\src\\ttt\\codicefiscale\\resources\\inputPersone.xml"));
        XMLDocument doc = c.readDocument();
        List<IXMLElement> lista = doc.getElements();
        doc.getElements().get(0);
        //stampa(lista);

    }

    public static void stampa(List<IXMLElement> s){
        s.forEach(elemento -> {
            System.out.println(elemento.getName());
            if(elemento.hasSubElements()){
                stampa(elemento.getElements());
            }
        });
    }
}
