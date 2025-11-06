/*
 * - new Autobus (int: plätze) (Parameterprüfung! negativ, <100 o.Ä.)
- einsteigen (Person): boolean (war möglich oder nicht)
- aussteigen (Person): void (Exception wenn P nicht drin war)
- isDrinnen (Person): boolean
- anzahlPassagiere()
- nutzlast(): int .. Gesamtgewicht aller eingestiegenen Passagiere
 */

public class Autobus {
    // wie viele Plätze gibt es im Bus
    private int plaetze;

    // alle Personen, die gerade im Bus sind
    private Person[] personen;

    // wie viele Personen sitzen gerade drin
    private int anzahl;

    // Bus wird erstellt
    public Autobus(int plaetze) {
        // Kontrolle, damit keine falsche Zahl eingegeben wird
        if (plaetze <= 0 || plaetze > 100) {
            throw new IllegalArgumentException("Plätze müssen zwischen 1 und 100 sein!");
        }

        this.plaetze = plaetze;
        this.personen = new Person[plaetze]; // Speicherplatz für Passagiere
        this.anzahl = 0; // am Anfang ist der Bus leer
    }

    // Eine Person steigt ein
    public boolean einsteigen(Person p) {
        // wenn nichts übergeben wurde
        if (p == null) return false;

        // wenn die Person schon im Bus sitzt
        if (isDrinnen(p)) return false;

        // wenn der Bus schon voll ist
        if (anzahl >= plaetze) return false;

        // Person eintragen
        personen[anzahl] = p;
        anzahl++;
        return true;
    }

    // Eine Person steigt aus
    public void aussteigen(Person p) {
        boolean gefunden = false;

        // wir suchen die Person im Bus
        for (int i = 0; i < anzahl; i++) {
            if (personen[i] == p) {
                // letzte Person rückt nach vorne
                personen[i] = personen[anzahl - 1];
                personen[anzahl - 1] = null;
                anzahl--;
                gefunden = true;
                break;
            }
        }

        // wenn Person nicht gefunden wurde
        if (!gefunden) {
            throw new IllegalStateException("Die Person war gar nicht im Bus!");
        }
    }

    // Prüfen, ob eine Person im Bus ist
    public boolean isDrinnen(Person p) {
        for (int i = 0; i < anzahl; i++) {
            if (personen[i] == p) return true;
        }
        return false;
    }

    // Anzahl der Personen im Bus
    public int anzahlPassagiere() {
        return anzahl;
    }

    // Gesamtgewicht aller Personen im Bus
    public int nutzlast() {
        int summe = 0;
        for (int i = 0; i < anzahl; i++) {
            summe = summe + personen[i].getKg();
        }
        return summe;
    }
}
