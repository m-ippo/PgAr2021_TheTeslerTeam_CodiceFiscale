/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.flow;

import java.io.File;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import ttt.codicefiscale.io.FolderLookup;
import ttt.codicefiscale.io.XMLLoader;
import ttt.utils.console.menu.Menu;
import ttt.utils.console.menu.utils.Pair;

/**
 *
 * @author gabri
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
            SwingUtilities.invokeLater(() -> {
                fp.showSelectFolder();
            });
            return null;
        });
        main_menu.addOption("Seleziona file base", () -> {
            fileBase = XMLLoader.selezionaFiles(fp);
            return null;
        });
        main_menu.paintMenu();
    }
}
