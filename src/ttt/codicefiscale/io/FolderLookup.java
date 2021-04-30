/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttt.codicefiscale.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author gabri
 */
public class FolderLookup {

    private File baseDir;
    private final ArrayList<File> xmls = new ArrayList<>();

    public FolderLookup(File directory) {
        baseDir = directory;
    }

    public void showSelectFolder() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Seleziona cartella di ricerca");
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int option = chooser.showOpenDialog(new JPanel());
            if (option == JFileChooser.APPROVE_OPTION) {
                changeDirectory(chooser.getSelectedFile());
            }
        });
    }

    public void changeDirectory(File newDirectory) {
        baseDir = newDirectory;
        scan();
    }

    public void scan() {
        if (baseDir != null && baseDir.exists() && baseDir.isDirectory()) {
            File[] listFiles = baseDir.listFiles((file, name) -> {
                return name.toLowerCase().endsWith(".xml");
            });
            xmls.clear();
            Collections.addAll(xmls, listFiles);
        }
    }

    public List<File> getXMLFiles() {
        return Collections.unmodifiableList(xmls);
    }

}
