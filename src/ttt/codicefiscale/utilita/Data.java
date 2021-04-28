package ttt.codicefiscale.utilita;

public class Data {

    private int anno;
    private int mese;
    private int giorno;
    private String data_stringa;

    public Data(String data_stringa) {
        this.data_stringa = data_stringa;
        convertiData();
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
        return data_stringa;
    }

    /**
     * Converte la stringa presa dal file di input e la trsforma in tre interi che corrispondono a: anno, mese, giorno.
     */
    private void convertiData(){

        char[] anno = new char[4];
        char[] mese = new char[2];
        char[] giorno = new char[2];

        char[] a = data_stringa.toCharArray();
        for(int i = 0; i < 4; i++){
            anno[i] = a[i];
        }
        for(int i = 5; i < 7; i++){
            mese[i-5] = a[i];
        }
        for(int i = 8; i < 10; i++){
            giorno[i-8] = a[i];
        }
        this.anno = Integer.parseInt(new String(anno));
        this.mese = Integer.parseInt(new String(mese));
        this.giorno = Integer.parseInt(new String(giorno));
    }
}

