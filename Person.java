
/**
 * Beschreiben Sie hier die Klasse Person.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Person
{
    private String name; // Name der Person
    private int kg;      // Gewicht

    public Person(String name, int kg) {
        this.name = name;
        this.kg = kg;
    }

    public String getName() {
        return name;
    }

    public int getKg() {
        return kg;
    }
}


