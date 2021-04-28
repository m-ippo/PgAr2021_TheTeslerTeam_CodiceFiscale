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
import ttt.codicefiscale.elementi.Codice;
import ttt.codicefiscale.elementi.Codici;
import ttt.codicefiscale.elementi.Cognome;
import ttt.codicefiscale.elementi.Comune;
import ttt.codicefiscale.elementi.ComuneNascita;
import ttt.codicefiscale.elementi.Comuni;
import ttt.codicefiscale.elementi.DataNascita;
import ttt.codicefiscale.elementi.Nome;
import ttt.codicefiscale.elementi.Persona;
import ttt.codicefiscale.elementi.Persone;
import ttt.codicefiscale.elementi.Sesso;
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
        File f1 = new File("C:\\Users\\gabri\\Downloads\\inputPersone.xml");//chooser.getSelectedFile();
        File f2 = new File("C:\\Users\\gabri\\Downloads\\comuni.xml");//chooser.getSelectedFile();

        if (f != null) {
            XMLReader c = new XMLReader(f);
            XMLReader c1 = new XMLReader(f1);
            XMLReader c2 = new XMLReader(f2);
            XMLDocument doc = c.readDocument();
            XMLDocument doc1 = c1.readDocument();
            XMLDocument doc2 = c2.readDocument();
            /*List<IXMLElement> lista = doc.getElements();
            doc.getElements().get(0);
            stampa(lista);*/
            XMLEngine engine = new XMLEngine(doc, Codice.class, Codici.class);
            XMLEngine engine1 = new XMLEngine(doc1, Persone.class, Persona.class, DataNascita.class, Cognome.class, Nome.class, Sesso.class, ComuneNascita.class);
            XMLEngine engine2 = new XMLEngine(doc2, Comune.class, Comuni.class, Nome.class, Codice.class);
            File output = new File("output_codiciFiscali.xml");
            File output1 = new File("output_inputPersone.xml");
            File output2 = new File("output_comuni.xml");
            XMLDocument d = new XMLDocument(output);
            XMLDocument d1 = new XMLDocument(output);
            XMLDocument d2 = new XMLDocument(output);
            engine.morph(d);
            engine1.morph(d1);
            engine2.morph(d2);
            XMLWriter lWriter = new XMLWriter(output);
            XMLWriter lWriter1 = new XMLWriter(output1);
            XMLWriter lWriter2 = new XMLWriter(output2);
            lWriter.writeDocument(d);
            lWriter1.writeDocument(d1);
            lWriter2.writeDocument(d2);
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
