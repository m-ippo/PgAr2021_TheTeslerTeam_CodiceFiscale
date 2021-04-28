/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale;

import java.io.File;
import ttt.utils.xml.engine.interfaces.IXMLElement;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import ttt.codicefiscale.elementi.Codice;
import ttt.codicefiscale.elementi.Codici;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.engine.XMLEngine;
import ttt.utils.xml.io.XMLReader;
import ttt.utils.xml.io.XMLWriter;

/**
 *
 * @author TTT
 */
public class CodiceFiscaleMain {

    public static void main(String[] args) throws IOException {
        /*
        XMLElement s = new XMLElement("codfis");
        s.setValue("FDANHJ01S23D938V");
        System.out.println(ControlloCodiceFiscale.Controllo(s));
         */

        /*JFileChooser chooser = new JFileChooser();
        String selezione = "GO";
        FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
        chooser.addChoosableFileFilter(filter);
        chooser.showDialog(null, selezione);*/
        File f = new File("C:\\Users\\gabri\\Downloads\\codiciFiscali.xml");//chooser.getSelectedFile();

        if (f != null) {
            XMLReader c = new XMLReader(f);
            XMLDocument doc = c.readDocument();
            /*List<IXMLElement> lista = doc.getElements();
            doc.getElements().get(0);
            stampa(lista);*/
            XMLEngine engine = new XMLEngine(doc, Codice.class, Codici.class);
            File output = new File("output.xml");
            XMLDocument d = new XMLDocument(output);
            engine.morph(d);
            XMLWriter lWriter= new XMLWriter(output);
            lWriter.writeDocument(d);
        }

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
