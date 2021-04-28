/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale;

import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.interfaces.IXMLElement;
import ttt.utils.xml.io.XMLReader;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author TTT
 */
public class CodiceFiscaleMain {

    public static void main(String[] args) throws IOException {

        XMLElement s = new XMLElement("codfis");
        s.setValue("FDANHJ01S23D938V");
        System.out.println(ControlloCodiceFiscale.Controllo(s));


/*
        JFileChooser chooser = new JFileChooser();
        String selezione = "GO";
        FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
        chooser.addChoosableFileFilter(filter);
        chooser.showDialog(null, selezione);
        File f = chooser.getSelectedFile();

        XMLReader c = new XMLReader(f);
        XMLDocument doc = c.readDocument();
        List<IXMLElement> lista = doc.getElements();
        doc.getElements().get(0);
        stampa(lista);
*/

    }

    public static void stampa(List<IXMLElement> s){
        s.forEach(elemento -> {
            System.out.println(elemento.getValue());
            if(elemento.hasSubElements()){
                stampa(elemento.getElements());
            }
        });
    }
}
