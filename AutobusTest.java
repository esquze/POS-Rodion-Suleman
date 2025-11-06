import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 
 */
public class AutobusTest {

    // Test: Falsche Werte beim Erstellen vom Bus
    @Test
    public void testKonstruktorFehler() {
        // 0 Plätze -> Fehler
        assertThrows(IllegalArgumentException.class, () -> new Autobus(0));

        // 200 Plätze -> auch Fehler (zu groß)
        assertThrows(IllegalArgumentException.class, () -> new Autobus(200));
    }

    // Test: normales Ein- und Aussteigen
    @Test
    public void testEinUndAussteigen() {
        Autobus bus = new Autobus(2);
        Person p1 = new Person("Ali", 70);
        Person p2 = new Person("Sara", 60);

        // Ali steigt ein
        assertTrue(bus.einsteigen(p1));

        // Sara steigt ein
        assertTrue(bus.einsteigen(p2));

        // Ali will nochmal einsteigen -> darf nicht, ist schon drin
        assertFalse(bus.einsteigen(p1));

        // Ali sitzt im Bus
        assertTrue(bus.isDrinnen(p1));

        // Ali steigt aus
        bus.aussteigen(p1);

        // Ali ist jetzt nicht mehr im Bus
        assertFalse(bus.isDrinnen(p1));

        // Es bleibt nur noch Sara im Bus
        assertEquals(1, bus.anzahlPassagiere());
    }

    // Test: Bus ist voll
    @Test
    public void testBusVoll() {
        Autobus bus = new Autobus(1);
        Person p1 = new Person("Anna", 55);
        Person p2 = new Person("Tom", 80);

        // Anna darf rein
        assertTrue(bus.einsteigen(p1));

        // Tom darf nicht mehr rein -> Bus ist voll
        assertFalse(bus.einsteigen(p2));
    }

    // Test: Aussteigen von jemandem, der gar nicht im Bus war
    @Test
    public void testFalschesAussteigen() {
        Autobus bus = new Autobus(2);
        Person p1 = new Person("Ali", 70);

        // Ali war nie im Bus -> es soll eine Exception kommen
        assertThrows(IllegalStateException.class, () -> bus.aussteigen(p1));
    }

    // Test: Nutzlast (Gesamtgewicht)
    @Test
    public void testNutzlast() {
        Autobus bus = new Autobus(3);
        Person p1 = new Person("Ali", 70);
        Person p2 = new Person("Sara", 60);

        bus.einsteigen(p1);
        bus.einsteigen(p2);

        // 70 + 60 = 130 -> das muss rauskommen
        assertEquals(130, bus.nutzlast());
    }
}
