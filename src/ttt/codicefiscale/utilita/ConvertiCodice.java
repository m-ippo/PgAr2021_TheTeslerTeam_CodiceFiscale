package ttt.codicefiscale.utilita;

import ttt.codicefiscale.elementi.Comune;
import ttt.codicefiscale.elementi.Persona;
import ttt.codicefiscale.elementi.DataNascita;
import ttt.codicefiscale.io.FolderLookup;
import ttt.codicefiscale.io.XMLLoader;
import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.interfaces.IXMLElement;
import ttt.utils.xml.io.XMLReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertiCodice {

    private static XMLDocument lista_comuni;
    private static boolean comuni_gia_letti = false;

    private static void leggiComuni() throws IOException {
        File f = new File("src/ttt/codicefiscale/resources/comuni.xml");
        lista_comuni = XMLLoader.loadDocument(XMLLoader.TipoXML.COMUNI, f, new File("non_usable.xml"));
        comuni_gia_letti = true;
    }

    /**
     * Metodo che ritorna una stringa corrispondente alle tre lettere del
     * cognome del codice fiscale.
     *
     * @param cognome Cognome da codificare
     * @return Stringa con codice
     */
    public static String cognomeCodice(String cognome) {
        cognome = GestisciStringhe.ordina(cognome);
        char[] ordinato = cognome.toCharArray();
        char[] ris = new char[3];

        if (cognome.length() > 3) {
            System.arraycopy(ordinato, 0, ris, 0, 3);
        } else {
            System.arraycopy(ordinato, 0, ris, 0, ordinato.length);
            for (int i = ordinato.length; i < 3; i++) {
                ris[i] = 'X';
            }
        }
        return new String(ris);
    }

    /**
     * Metodo che ritorna una stringa corrispondente alle tre lettere del nome
     * del codice fiscale. (1,3,4 consonante o 1,2,3 consonante o
     * consonanti+vocali o consonanti+vocali+X)
     *
     * @param nome Nome da codificare
     * @return Stringa con codice
     */
    public static String nomeCodice(String nome) {
        nome = GestisciStringhe.ordina(nome);
        char[] ordinato = nome.toCharArray();
        char[] ris = new char[3];

        if (nome.length() > 3) {
            if (GestisciStringhe.quanteConsonanti(nome) >= 4) {
                ris[0] = ordinato[0];
                ris[1] = ordinato[2];
                ris[2] = ordinato[3];
            } else {
                System.arraycopy(ordinato, 0, ris, 0, 3);
            }
        } else {
            for (int i = 0; i < ordinato.length; i++) {
                ris[i] = ordinato[i];
            }
            for (int i = ordinato.length; i < 3; i++) {
                ris[i] = 'X';
            }
        }
        return new String(ris);
    }

    public static String dataCodice(DataNascita d, boolean uomo) {

        String ris = "";
        int anno = d.getAnno() % 100;
        if (anno < 10) {
            ris += "0";
        }
        ris += Integer.toString(anno);

        switch (d.getMese()) {
            case 1:
                ris += "A";
                break;
            case 2:
                ris += "B";
                break;
            case 3:
                ris += "C";
                break;
            case 4:
                ris += "D";
                break;
            case 5:
                ris += "E";
                break;
            case 6:
                ris += "H";
                break;
            case 7:
                ris += "L";
                break;
            case 8:
                ris += "M";
                break;
            case 9:
                ris += "P";
                break;
            case 10:
                ris += "R";
                break;
            case 11:
                ris += "S";
                break;
            case 12:
                ris += "T";
                break;

        }

        int giorno = d.getGiorno();
        if (!uomo) { // se persona è donna aggiunge 30 al valore del giorno
            giorno += 30;
        }
        if (giorno < 10) { // se valore del giorno è minore di 10 aggiunge uno 0 nella stringa finale (per regole c.f.)
            ris += "0";
        }
        ris += Integer.toString(giorno);
        return ris;
    }

    public static String creaCodicePersona(Persona p) throws IOException {

        if(!comuni_gia_letti){
            leggiComuni();
        }

        String codice = p.getCodiceParziale();

        String orinatoPariDispari = GestisciStringhe.pariDispari(codice);
        int valore_pari_dispari = 0;

        for (int i = 0; i < 8; i++) {
            valore_pari_dispari += getValoreDispari(orinatoPariDispari.charAt(i));
        }
        for (int i = 8; i < 15; i++) {
            valore_pari_dispari += getValorePari(orinatoPariDispari.charAt(i));
        }

        valore_pari_dispari = valore_pari_dispari % 26;

        codice += getCarattereControllo(valore_pari_dispari);

        return codice;
    }

    public static int getValoreDispari(char c) {
        int valore = 0;

        switch (c) {
            case '0':
                valore = 1;
                break;
            case '1':
                valore = 0;
                break;
            case '2':
                valore = 5;
                break;
            case '3':
                valore = 7;
                break;
            case '4':
                valore = 9;
                break;
            case '5':
                valore = 13;
                break;
            case '6':
                valore = 15;
                break;
            case '7':
                valore = 17;
                break;
            case '8':
                valore = 19;
                break;
            case '9':
                valore = 21;
                break;
            case 'A':
                valore = 1;
                break;
            case 'B':
                valore = 0;
                break;
            case 'C':
                valore = 5;
                break;
            case 'D':
                valore = 7;
                break;
            case 'E':
                valore = 9;
                break;
            case 'F':
                valore = 13;
                break;
            case 'G':
                valore = 15;
                break;
            case 'H':
                valore = 17;
                break;
            case 'I':
                valore = 19;
                break;
            case 'J':
                valore = 21;
                break;
            case 'K':
                valore = 2;
                break;
            case 'L':
                valore = 4;
                break;
            case 'M':
                valore = 18;
                break;
            case 'N':
                valore = 20;
                break;
            case 'O':
                valore = 11;
                break;
            case 'P':
                valore = 3;
                break;
            case 'Q':
                valore = 6;
                break;
            case 'R':
                valore = 8;
                break;
            case 'S':
                valore = 12;
                break;
            case 'T':
                valore = 14;
                break;
            case 'U':
                valore = 16;
                break;
            case 'V':
                valore = 10;
                break;
            case 'W':
                valore = 22;
                break;
            case 'X':
                valore = 25;
                break;
            case 'Y':
                valore = 24;
                break;
            case 'Z':
                valore = 23;
                break;
        }
        return valore;
    }

    public static int getValorePari(char c) {
        int valore;
        valore = Character.isDigit(c)
                ? (int) c - (int) '0' : Character.isLetter(c) && Character.getType(c) == Character.UPPERCASE_LETTER
                ? (int) c - (int) 'A' : 0;
        /*
        switch (c){
            case '0': valore = 0; break;
            case '1': valore = 1; break;
            case '2': valore = 2; break;
            case '3': valore = 3; break;
            case '4': valore = 4; break;
            case '5': valore = 5; break;
            case '6': valore = 6; break;
            case '7': valore = 7; break;
            case '8': valore = 8; break;
            case '9': valore = 9; break;
            case 'A': valore = 0; break;
            case 'B': valore = 1; break;
            case 'C': valore = 2; break;
            case 'D': valore = 3; break;
            case 'E': valore = 4; break;
            case 'F': valore = 5; break;
            case 'G': valore = 6; break;
            case 'H': valore = 7; break;
            case 'I': valore = 8; break;
            case 'J': valore = 9; break;
            case 'K': valore = 10; break;
            case 'L': valore = 11; break;
            case 'M': valore = 12; break;
            case 'N': valore = 13; break;
            case 'O': valore = 14; break;
            case 'P': valore = 15; break;
            case 'Q': valore = 16; break;
            case 'R': valore = 17; break;
            case 'S': valore = 18; break;
            case 'T': valore = 19; break;
            case 'U': valore = 20; break;
            case 'V': valore = 21; break;
            case 'W': valore = 22; break;
            case 'X': valore = 23; break;
            case 'Y': valore = 24; break;
            case 'Z': valore = 25; break;
        }*/
        return valore;
    }

    public static String getCarattereControllo(int valore) {
        String ris = "";

        /*switch (valore){
            case 0: ris = "A"; break;
            case 1: ris = "B"; break;
            case 2: ris = "C"; break;
            case 3: ris = "D"; break;
            case 4: ris = "E"; break;
            case 5: ris = "F"; break;
            case 6: ris = "G"; break;
            case 7: ris = "H"; break;
            case 8: ris = "I"; break;
            case 9: ris = "J"; break;
            case 10: ris = "K"; break;
            case 11: ris = "L"; break;
            case 12: ris = "M"; break;
            case 13: ris = "N"; break;
            case 14: ris = "O"; break;
            case 15: ris = "P"; break;
            case 16: ris = "Q"; break;
            case 17: ris = "R"; break;
            case 18: ris = "S"; break;
            case 19: ris = "T"; break;
            case 20: ris = "U"; break;
            case 21: ris = "V"; break;
            case 22: ris = "W"; break;
            case 23: ris = "X"; break;
            case 24: ris = "Y"; break;
            case 25: ris = "Z"; break;
        }*/
        ris += "" + (char) (((int) 'A') + valore);
        return ris;
    }

    public static String comuneCodice(String s) {

        if(cercaCodiceComune(lista_comuni.getElements(), s) != null){
            return cercaCodiceComune(lista_comuni.getElements(), s);
        }
        return null;
    }

    public static String cercaCodiceComune(List<IXMLElement> l, String nome_comune){

        for(int i = 0; i < l.get(0).getElements().size(); i++){
            if(l.get(0).getElements().get(i).getElements().get(0).getValue().equals(nome_comune)){
                return l.get(0).getElements().get(i).getElements().get(1).getValue();
            }
        }

        return null;

    }
}
