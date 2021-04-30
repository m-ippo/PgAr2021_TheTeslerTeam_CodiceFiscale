package ttt.codicefiscale.utilita;

public class GestisciStringhe {
    /**
     * Metodo che ordina una stringa mettendo prima le consonanti e poi le vocali.
     *
     * @param daOrdinare stringa da ordinare
     */
    public static String ordina(String daOrdinare) {
        daOrdinare = daOrdinare.toUpperCase();
        char[] c = daOrdinare.toCharArray();

        int num_consonanti = quanteConsonanti(daOrdinare);

        char[] vocali = new char[daOrdinare.length() - num_consonanti];
        char[] consonanti = new char[num_consonanti];

        int index_vocali = 0;
        int index_consonanti = 0;

        for (char lettera : c) {
            if (isVocale(lettera)) {
                vocali[index_vocali] = lettera;
                index_vocali++;
            } else {
                consonanti[index_consonanti] = lettera;
                index_consonanti++;
            }
        }

        String vocali_s = new String(vocali);
        String consonanti_s = new String(consonanti);

        return consonanti_s + vocali_s;
    }

    public static boolean isVocale(char lettera) {
        return lettera == 'A' || lettera == 'E' || lettera == 'I' || lettera == 'O' || lettera == 'U';
    }

    public static boolean isX(char lettera){
        return lettera == 'X';
    }

    public static boolean isConsonante(char lettera){
        return !isVocale(lettera);
    }

    public static boolean isConsonanteNonX(char lettera){
        return isConsonante(lettera) && !isX(lettera);
    }

    public static int quanteConsonanti(String s) {
        int ris = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!isVocale(s.charAt(i))) {
                ris++;
            }
        }
        return ris;
    }

    /**
     * Metodo che ritorna una stringa riordinata in modo che prima ci siano tutti i caratteri in posizione dispari e poi pari
     *
     * @param s Stringa da riordinare
     * @return Stringa ordinata
     */
    public static String pariDispari(String s) {
        char[] completa = s.toCharArray();
        char[] pari = new char[7];
        char[] dispari = new char[8];

        int index_pari = 0;
        int index_dispari = 0;

        for (int i = 0; i < completa.length; i++) {
            if (i % 2 == 1) {
                pari[index_pari] = completa[i];
                index_pari++;
            } else {
                dispari[index_dispari] = completa[i];
                index_dispari++;
            }
        }
        return new String(dispari) + new String(pari);

    }
}