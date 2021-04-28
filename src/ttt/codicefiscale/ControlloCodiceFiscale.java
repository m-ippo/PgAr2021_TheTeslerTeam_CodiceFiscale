package ttt.codicefiscale;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ttt.codicefiscale.utilita.ConvertiCodice;
import ttt.codicefiscale.utilita.GestisciStringhe;
import ttt.utils.xml.document.XMLElement;

/**
 * classe che controlla la validita del codice
 */
public class ControlloCodiceFiscale {
    /**
     * metodo che ritorna true o false in base alla validita del codice
     * @param elemento
     * @return
     */
    public static boolean Controllo (XMLElement elemento){
        Pattern pattern = Pattern.compile("([A-Z]{3})([A-Z]{3})([0-9]{2})([A-Z]{1})([0-9]{2})([A-Z]{1})([0-9]{3})([A-Z]{1})");
        Matcher matcher = pattern.matcher(elemento.getValue());
        boolean valido = matcher.find();
        if(!valido){
            return false;
        }
        if (!ControllaNumero(matcher.group(5)) || !ControllaMese((matcher.group(4)))) {
            return false;
        }
        if(!ControllaUltimoCarattere(elemento.getValue())){
            return false;
        }
        return true;
    }

    private static boolean ControllaNumero(String s){
    int valore = Integer.parseInt(s);
        return (valore > 0 && valore < 32) || (valore >= 41 && valore <= 71);
    }

    private static boolean ControllaMese (String s){
    return s.charAt(0)=='A'||s.charAt(0)=='B'||s.charAt(0)=='C'||s.charAt(0)=='D'||s.charAt(0)=='E'||
            s.charAt(0)=='H'||s.charAt(0)=='L'||s.charAt(0)=='M'||s.charAt(0)=='P'||s.charAt(0)=='R'||
            s.charAt(0)=='S'||s.charAt(0)=='T';
    }

    private static boolean ControllaUltimoCarattere(String s){

        char[] codice_meno_ultimo = new char[15];
        for(int i = 0; i < 15; i++){
            codice_meno_ultimo[i] = s.charAt(i);
        }
        String senza_ultimo = new String(codice_meno_ultimo);

        senza_ultimo = GestisciStringhe.pariDispari(senza_ultimo);


        int valore_pari_dispari = 0;

        for(int i = 0; i < 8; i++){
            valore_pari_dispari += ConvertiCodice.getValoreDispari(senza_ultimo.charAt(i));
        }

        for(int i = 8; i < 15; i++){
            valore_pari_dispari += ConvertiCodice.getValorePari(senza_ultimo.charAt(i));
        }

        valore_pari_dispari = valore_pari_dispari % 26;
        String carattere_giusto = ConvertiCodice.getCarattereControllo(valore_pari_dispari);
        char[] ultimo = new char[1];
        ultimo[0] = s.charAt(15);
        String st = new String(ultimo);
        return carattere_giusto.equals(st);
    }




}
