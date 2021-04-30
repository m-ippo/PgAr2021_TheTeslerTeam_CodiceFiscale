/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.flow;

import java.io.File;
import java.util.ArrayList;
import ttt.codicefiscale.io.FolderLookup;
import ttt.codicefiscale.io.XMLLoader;
import ttt.utils.console.menu.Menu;
import ttt.utils.console.menu.utils.Pair;

/**
 * Inutilizzata: è stata rimpiazzata dalla parte grafica. Incompleta e non verrà
 * completata.
 *
 * @author TTT
 */
public class GestioneMenu {

    Menu<Void> main_menu;

    public GestioneMenu() {
        main_menu = new Menu("Scegli operazione:", true) {
        };
        init();
    }

    FolderLookup fp = new FolderLookup(new File("" + File.separatorChar));
    ArrayList<Pair<File, XMLLoader.TipoXML>> fileBase;

    private void init() {
        main_menu.reset();
        main_menu.addOption("Cambia cartella di ricerca", () -> {
            //SwingUtilities.invokeLater(() -> {
            fp.showSelectFolder();
            //});
            return null;
        });
        main_menu.addOption("Seleziona i file di base", () -> {
            fileBase = XMLLoader.selezionaFiles(fp);
            if (fileBase != null) {
                main_menu.addLazyExecutable(() -> {
                    main_menu.removeOption(2);
                    main_menu.removeOption(2);
                    return null;
                });
                proceed();
            }
            return null;
        });
        main_menu.paintMenu();
    }

    private void proceed() {
        //Generare gli XMLDocument dei files aperti
        main_menu.addOption("Genera codici fiscali", () -> {
            //Generare i codici fiscali del XMLDocumnt aperto
            //Procedere con i calcoli e i controlli per vedere quali codici fiscali sono presenti.
            //Aggiungere l'opzione di salvataggio del file appena generato
            return null;
        });
    }
}
