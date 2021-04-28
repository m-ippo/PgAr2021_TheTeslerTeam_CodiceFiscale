package ttt.codicefiscale;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import ttt.utils.xml.document.XMLElement;

/**
 * classe che controlla la validita del codice
 */
public class Controllo {
    /**
     * metodo che ritorna true o false in base alla validita del codice
     * @param elemento
     * @return
     */
    public static boolean controllo (XMLElement elemento){
        Pattern pattern = Pattern.compile("([A-Z]{3})([A-Z]{3})([0-9]{2})([A-Z]{1})([0-9]{2})([A-Z]{1})([0-9]{3})([A-Z]{1})");
        Matcher matcher = pattern.matcher(elemento.getValue());
        boolean valido = matcher.find();
        if(!valido){
            return false;
        }else {
            if (!ControllaNumero(matcher.group(5)) || !ControllaMese((matcher.group(4)))) {
                return false;
            }
        }
        return true;
    }

        private static boolean ControllaNumero(String s){
        Integer valore = Integer.parseInt(s);
        if ((valore>0 && valore<32) || (valore>=41 && valore<=71)){
            return true;
            }
        return false;
        }

        private static boolean ControllaMese (String s){
        return s.charAt(0)=='A'||s.charAt(0)=='B'||s.charAt(0)=='C'||s.charAt(0)=='D'||s.charAt(0)=='E'||
                s.charAt(0)=='H'||s.charAt(0)=='L'||s.charAt(0)=='M'||s.charAt(0)=='P'||s.charAt(0)=='R'||
                s.charAt(0)=='S'||s.charAt(0)=='T';
        }




}
