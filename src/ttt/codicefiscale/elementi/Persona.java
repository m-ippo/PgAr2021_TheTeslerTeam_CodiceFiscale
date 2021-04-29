package ttt.codicefiscale.elementi;

import ttt.codicefiscale.utilita.ConvertiCodice;
import ttt.utils.xml.document.XMLElement;
import ttt.utils.xml.engine.annotations.Element;
import ttt.utils.xml.engine.annotations.EngineMethod;
import ttt.utils.xml.engine.annotations.Tag;
import ttt.utils.xml.engine.enums.MethodType;

@Element(Name = "persona")
public class Persona extends XMLElement {

    private String nome;
    private String cognome;
    private String sesso;
    private DataNascita data_di_nascita;
    private String comune_di_nascita;
    private char tasse;
    private String codice_fiscale;
    
    private Integer id;

    /*
    public Persona(String nome, String cognome, String sesso, DataNascita data_di_nascita, String comune_di_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data_di_nascita = data_di_nascita;
        this.comune_di_nascita = comune_di_nascita;
    }*/
    /**
     *
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
    public void setID(String value){
        id = Integer.parseInt(value);
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

    public char getTasse() {
        return tasse;
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

    public String getCodiceParziale() {
        String codice_parziale = ConvertiCodice.cognomeCodice(cognome) + ConvertiCodice.nomeCodice(nome);
        if (sesso.equals("M")) {
            codice_parziale += ConvertiCodice.dataCodice(data_di_nascita, true);
        } else {
            codice_parziale += ConvertiCodice.dataCodice(data_di_nascita, false);
        }
        codice_parziale += ConvertiCodice.comuneCodice(comune_di_nascita);
        return codice_parziale;
    }

}
