package ttt.codicefiscale.elementi;

import ttt.utils.engines.enums.MethodType;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.engines.interfaces.EngineMethod;

/**
 * Rappresenta l'elemento "data_nascita"
 *
 * @author TTT
 */
@Element(Name = "data_nascita")
public class DataNascita extends XMLElement {

    private int anno;
    private int mese;
    private int giorno;

    public DataNascita() {
        super("data_nascita");
    }

    public int getAnno() {
        return anno;
    }

    public int getMese() {
        return mese;
    }

    public int getGiorno() {
        return giorno;
    }

    public String getDataStringa() {
        return getValue();
    }

    /**
     * Converte la stringa presa dal file di input e la trsforma in tre interi
     * che corrispondono a: anno, mese, giorno.
     */
    @EngineMethod(MethodType = MethodType.CALC)
    public void convertiData() {

        char[] _anno = new char[4];
        char[] _mese = new char[2];
        char[] _giorno = new char[2];

        char[] a = getDataStringa().toCharArray();
        System.arraycopy(a, 0, _anno, 0, 4);
        for (int i = 5; i < 7; i++) {
            _mese[i - 5] = a[i];
        }
        for (int i = 8; i < 10; i++) {
            _giorno[i - 8] = a[i];
        }
        this.anno = Integer.parseInt(new String(_anno));
        this.mese = Integer.parseInt(new String(_mese));
        this.giorno = Integer.parseInt(new String(_giorno));
    }
}
