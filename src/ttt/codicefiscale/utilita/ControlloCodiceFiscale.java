package ttt.codicefiscale.utilita;

import ttt.utils.xml.document.XMLDocument;
import ttt.utils.xml.document.XMLElement;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ttt.codicefiscale.utilita.GestisciStringhe.*;

/**
 * classe che controlla la validita del codice
 */
public class ControlloCodiceFiscale {

    private static ConvertiCodice convertitore;

    public static void setConvertitore(ConvertiCodice c){
        convertitore = c;
    }
    public static ConvertiCodice getConvertitore(){
        return convertitore;
    }

    /**
     * metodo che ritorna true o false in base alla validita del codice
     *
     * @param elemento
     * @return
     */
    public static boolean Controllo(XMLElement elemento){
        Pattern pattern = Pattern.compile("([A-Z]{3})([A-Z]{3})([0-9]{2})([A-Z]{1})([0-9]{2})([A-Z]{1})([0-9]{3})([A-Z]{1})");
        Matcher matcher = pattern.matcher(elemento.getValue());
        boolean valido = matcher.find();
        if (!valido) {
            return false;
        }
        if(!ControllaMese((matcher.group(4)))){
            return false;
        }
        if (!ControllaNumero(matcher.group(5), matcher.group(4))) {
            return false;
        }
        if(!ControllaLettere(matcher.group(1)) || !ControllaLettere(matcher.group(2))){
            return false;
        }
        if(!ControllaComune(matcher.group(6) + matcher.group(7), convertitore)){
            return false;
        }
        if(!ControllaLettere(matcher.group(1)) || !ControllaLettere(matcher.group(2))){
            return false;
        }
        return ControllaUltimoCarattere(elemento.getValue());
    }

    private static boolean ControllaNumero(String giorno, String mese) {
        int valore = Integer.parseInt(giorno);
        return (valore > 0 && valore < (mese.equals("B") ? 29 : 32)) || (valore > 40 && valore < (mese.equals("B") ? 69 : 72));
    }

    private static boolean ControllaMese(String s) {
        return s.charAt(0) == 'A' || s.charAt(0) == 'B' || s.charAt(0) == 'C' || s.charAt(0) == 'D' || s.charAt(0) == 'E'
                || s.charAt(0) == 'H' || s.charAt(0) == 'L' || s.charAt(0) == 'M' || s.charAt(0) == 'P' || s.charAt(0) == 'R'
                || s.charAt(0) == 'S' || s.charAt(0) == 'T';
    }

    private static boolean ControllaUltimoCarattere(String s) {

        char[] codice_meno_ultimo = new char[15];
        for (int i = 0; i < 15; i++) {
            codice_meno_ultimo[i] = s.charAt(i);
        }
        String senza_ultimo = new String(codice_meno_ultimo);

        senza_ultimo = GestisciStringhe.pariDispari(senza_ultimo);

        int valore_pari_dispari = 0;

        for (int i = 0; i < 8; i++) {
            valore_pari_dispari += ConvertiCodice.getValoreDispari(senza_ultimo.charAt(i));
        }

        for (int i = 8; i < 15; i++) {
            valore_pari_dispari += ConvertiCodice.getValorePari(senza_ultimo.charAt(i));
        }

        valore_pari_dispari = valore_pari_dispari % 26;
        String carattere_giusto = ConvertiCodice.getCarattereControllo(valore_pari_dispari);
        char[] ultimo = new char[1];
        ultimo[0] = s.charAt(15);
        String st = new String(ultimo);
        return carattere_giusto.equals(st);
    }

    public static boolean ControllaLettere(String s){

        char prima = s.charAt(0);
        char seconda = s.charAt(1);
        char terza = s.charAt(2);

        if(isVocale(prima) && (isConsonanteNonX(seconda) || isConsonanteNonX(terza))) return false;
        if(isVocale(prima) && isX(seconda) && isConsonanteNonX(terza)) return false;
        if(isVocale(prima) && isConsonanteNonX(seconda)) return false;
        if(isConsonanteNonX(prima) && isVocale(seconda) && isConsonanteNonX(terza)) return false;
        if(isX(prima) && isVocale(seconda) && isConsonanteNonX(terza)) return false;

        return true;
    }

    private static boolean ControllaComune(String codice_comune, ConvertiCodice convertitore){
        return convertitore.codiceComuneIsPresent(codice_comune);
    }

}
