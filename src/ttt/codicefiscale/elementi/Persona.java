package ttt.codicefiscale.elementi;

import ttt.codicefiscale.utilita.ConvertiCodice;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.XMLEngine;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.xml.engine.annotations.EngineMethod;
import ttt.utils.xml.engine.annotations.Tag;
import ttt.utils.xml.engine.enums.MethodType;

/**
 * Rappresenta l'elemento "persona"
 * @author TTT
 */
@Element(Name = "persona")
public class Persona extends XMLElement {

    private String nome;
    private String cognome;
    private String sesso;
    private DataNascita data_di_nascita;
    private String comune_di_nascita;
    private char carattere_di_controllo;
    private String codice_fiscale;

    private Integer id;

    /**
     * Inizializza i valori della classe solo dopo esser stata completata da {@link XMLEngine#morph(ttt.utils.xml.document.XMLDocument)
     * }
     */
    @EngineMethod(MethodType = MethodType.CALC)
    public void init() {
        nome = getFirstElement("nome").getValue();
        cognome = getFirstElement("cognome").getValue();
        sesso = getFirstElement("sesso").getValue();
        comune_di_nascita = getFirstElement("comune_nascita").getValue();
        data_di_nascita = (DataNascita) getFirstElement("data_nascita");
    }

    public Persona() {
        super("persona");
    }

    @EngineMethod(MethodType = MethodType.SET)
    @Tag(Name = "id")
    public void setID(String value) {
        id = Integer.parseInt(value);
    }

    @EngineMethod(MethodType = MethodType.GET)
    @Tag(Name = "id")
    public int getID() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public DataNascita getDataDiNascita() {
        return data_di_nascita;
    }

    public String getLuogoDiNascita() {
        return comune_di_nascita;
    }

    public char getCarattereControllo() {
        return carattere_di_controllo;
    }

    public String getCodiceFiscale() {
        return codice_fiscale;
    }

    @Override
    public String toString() {
        return String.format("[PERSONA] Nome= %15s \tcognome= %15s\tsesso= %s\tdata di nascita= %12s\tcomune di nascita= %s", nome, cognome,
                sesso, data_di_nascita.getDataStringa(), comune_di_nascita);
    }

    public void generaCodiceFiscale() {
        this.codice_fiscale = ""; // da finire
    }

    public String getCodiceParziale(ConvertiCodice convertitore) {
        if (convertitore != null) {
            String codice_parziale = ConvertiCodice.cognomeCodice(cognome) + ConvertiCodice.nomeCodice(nome);
            if (sesso.equals("M")) {
                codice_parziale += ConvertiCodice.dataCodice(data_di_nascita, true);
            } else {
                codice_parziale += ConvertiCodice.dataCodice(data_di_nascita, false);
            }
            if (convertitore.comuneCodice(comune_di_nascita) != null) {
                codice_parziale += convertitore.comuneCodice(comune_di_nascita);
                return codice_parziale;
            }
        }
        return null;
    }

}
