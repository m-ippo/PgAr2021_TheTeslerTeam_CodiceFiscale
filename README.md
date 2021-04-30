# Programma codice fiscale

**Necessita della libreria:**[Utils (versione minima 1.0)](https://github.com/ThatCmd/Utils/releases/tag/1.0)

Selezionati i file necessari per il funzionamento (lista dei comuni, lista di persone, lista dei codici fiscali) permette di caricare i file XML nelle apposite classi tramite l'uso della Reflection.
Una volta caricati i file si possono generare i codici fiscali delle persone, e solo successivamente fare una comparazione con quelli presenti nel file caricato. Infine si  può salvare il risultato dell'operazione sottoforma di file XML.

Nel controllo della validità di nomi e cognomi nei codici fiscali sono stati considerati validi nomi "particolari" contenti delle "x" (ad esempio xoxaxi o xa sono nomi validi).

L'interazione con l'utente è stata fatta tramite un banale form swing.

Per capire come funziona la lettura e scrittura della libreria è possibile consultare la repository: [EsempioXML](https://github.com/ThatCmd/EsempioXML).

Sono state implementate le seguenti funzionalità:
  - tutte quelle default/obbligatorie
  - Validità di nome e cognome

### The Tesler Team
Componenti:
 * Mattia Ippoliti
 * Nicholas Fada
 * Gabriele Vianello
