/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import ttt.utils.console.menu.Menu;
import ttt.utils.console.menu.utils.Pair;
import ttt.utils.console.output.GeneralFormatter;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.XMLEngine;
import ttt.utils.xml.io.XMLReader;

/**
 *
 * @author gabri
 */
public final class XMLLoader {

    private XMLLoader() {
    }

    public static final Class<? extends XMLElement>[] ELEMENTI_COMUNI = new Class[]{Comune.class, Comuni.class, Codice.class, Nome.class};
    public static final Class<? extends XMLElement>[] ELEMENTI_CODICI_FICALI = new Class[]{Codice.class, Codici.class};
    public static final Class<? extends XMLElement>[] ELEMENTI_PERSONE = new Class[]{Persone.class, Persona.class, Nome.class, Cognome.class, Sesso.class, ComuneNascita.class, DataNascita.class};

    public static enum TipoXML {
        CODICI_FISCALI, COMUNI, PERSONE;
    }

    /**
     * Carica un documento XML.
     *
     * @param tipo Indica la tipologia di Engine da usare (cioè quale tipo di
     * file leggere).
     * @param da_caricare Il file XML da leggere e tradurre in classi.
     * @param risultato Il file XML di output che viene impostato nel documento
     * di ritorno.
     * @return Il documento formattato secondo la tipologia richiesta.
     */
    public static XMLDocument loadDocument(TipoXML tipo, File da_caricare, File risultato) {
        if (da_caricare != null && da_caricare.exists() && da_caricare.isFile()) {
            try {
                XMLDocument documento;
                XMLReader reader = new XMLReader(da_caricare);
                documento = reader.readDocument();

                XMLEngine engine;
                switch (tipo) {
                    case CODICI_FISCALI:
                        engine = new XMLEngine(documento, ELEMENTI_CODICI_FICALI);
                        break;
                    case COMUNI:
                        engine = new XMLEngine(documento, ELEMENTI_COMUNI);
                        break;
                    case PERSONE:
                        engine = new XMLEngine(documento, ELEMENTI_PERSONE);
                        break;
                    default:
                        engine = new XMLEngine(documento);
                        break;
                }
                XMLDocument finale = new XMLDocument(risultato);
                engine.morph(finale);
                return finale;
            } catch (IOException ex) {
                Logger.getLogger(XMLLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new XMLDocument(da_caricare);
    }

    public static ArrayList<Pair<File, TipoXML>> selezionaFiles(FolderLookup fp) {
        ArrayList<TipoXML> tipi = new ArrayList<>();
        Collections.addAll(tipi, TipoXML.values());

        ArrayList<Pair<File, TipoXML>> scelte = new ArrayList<>();

        if (fp != null) {
            do {
                Menu<File> scelta_file = new Menu<File>("Scegli file da caricare:") {
                };
                scelta_file.removeOption(1);
                scelta_file.autoPrintSpaces(false);
                fp.scan();
                scelta_file.addOption("Indietro (annulla tutto)", () -> {
                    return null;
                });
                fp.getXMLFiles().forEach((f) -> {
                    scelta_file.addOption(f.getAbsolutePath(), () -> {
                        return f;
                    });
                });
                File scelto = scelta_file.showAndWait();
                if (scelto != null) {
                    GeneralFormatter.incrementIndents();
                    Menu<TipoXML> scelta_tipologia = new Menu<TipoXML>("Scegli categoria (in base al contenuto) del file: " + scelto.getName()) {
                    };
                    scelta_tipologia.removeOption(1);
                    for (TipoXML t : tipi) {
                        scelta_tipologia.addOption(t.name(), () -> {
                            return t;
                        });
                    }
                    TipoXML scelto_2 = scelta_tipologia.showAndWait();
                    GeneralFormatter.decrementIndents();
                    tipi.remove(scelto_2);
                    scelte.add(new Pair<>(scelto, scelto_2));
                }else{
                    return null;
                }
            } while (tipi.size() > 0 && scelte.size() < TipoXML.values().length);
        }
        return null;
    }
}
