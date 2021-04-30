package ttt.codicefiscale.utilita;

import ttt.utils.xml.document.XMLElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ttt.codicefiscale.utilita.GestisciStringhe.*;

/**
 * Classe che controlla la validita di un codice fiscale.
 */
public class ControlloCodiceFiscale {

    private static ConvertiCodice convertitore;

    /**
     * Imposta il convertitore da utilizzare.
     * @param c
     */
    public static void setConvertitore(ConvertiCodice c){
        convertitore = c;
    }

    public static ConvertiCodice getConvertitore(){
        return convertitore;
    }

    /**
     * Metodo che ritorna true o false in base alla validita del codice
     *
     * @param elemento Il codice fiscale da controllare.
     * @return T/F se il codice fiscale risulta valido o no.
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

    /**
     * Metodo per controllare la validita del giorno della data di nascita del codice fiscale.
     * @param giorno Giorno del codice fiscale da controllare.
     * @param mese Mese del codice fiscale da controllare.
     * @return T/F
     */
    private static boolean ControllaNumero(String giorno, String mese) {
        int valore = Integer.parseInt(giorno);
        return (valore > 0 && valore < (mese.equals("B") ? 29 : 32)) || (valore > 40 && valore < (mese.equals("B") ? 69 : 72));
    }

    /**
     * Metodo per controllare la validita del mese della data di nascita del codice fiscale.
     * @param mese Mese del codice fiscale da controllare.
     * @return T/F
     */
    private static boolean ControllaMese(String mese) {
        return mese.charAt(0) == 'A' || mese.charAt(0) == 'B' || mese.charAt(0) == 'C' || mese.charAt(0) == 'D' || mese.charAt(0) == 'E'
                || mese.charAt(0) == 'H' || mese.charAt(0) == 'L' || mese.charAt(0) == 'M' || mese.charAt(0) == 'P' || mese.charAt(0) == 'R'
                || mese.charAt(0) == 'S' || mese.charAt(0) == 'T';
    }

    /**
     * Metodo per controllare la validita del carattere di controllo del codice fiscale.
     * @param s Ultimo carattere.
     * @return T/F
     */
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

    /**
     * Metodo per controllare la validita delle lettere del nome/cognome del codice fiscale.
     * @param s Nome o cognome.
     * @return T/F
     */
    public static boolean ControllaLettere(String s){

        char prima = s.charAt(0);
        char seconda = s.charAt(1);
        char terza = s.charAt(2);

        if(isVocale(prima) && (isConsonanteNonX(seconda) || isConsonanteNonX(terza))) return false;
        if(isVocale(prima) && isX(seconda) && isConsonanteNonX(terza)) return false;
        if(isVocale(prima) && isConsonanteNonX(seconda)) return false;
        if(isConsonanteNonX(prima) && isVocale(seconda) && isConsonanteNonX(terza)) return false;
        return !(isX(prima) && isVocale(seconda) && isConsonanteNonX(terza));
    }

    /**
     * Metodo per controllare la validita del codice del comune del codice fiscale.
     * @param codice_comune Codice del comune da controllare.
     * @param convertitore Convertitore da utilizzare.
     * @return T/F
     */
    private static boolean ControllaComune(String codice_comune, ConvertiCodice convertitore){
        return convertitore.codiceComuneIsPresent(codice_comune);
    }

}
