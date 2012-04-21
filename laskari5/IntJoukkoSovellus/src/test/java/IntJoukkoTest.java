
import ohtu.intjoukkosovellus.IntJoukko;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;

public class IntJoukkoTest {

    protected IntJoukko joukko;
    protected IntJoukko joukkoToinen;

    @Before
    public void init() {
        joukko = new IntJoukko(5);
        joukkoToinen = new IntJoukko(7);
    }

    @Test
    public void testaaTyhjaMahtavuus() {
        assertEquals(0, joukko.mahtavuus());
    }

    @Test
    public void testaaMahtavuusKunLisatty7() {
        for (int i = 0; i < 7; i++) {
            joukko.lisaa(i);

        }
        assertEquals(7, joukko.mahtavuus());
    }

    @Test
    public void testaaKuuluukoKunPitaisiKuulua() {
        joukko.lisaa(6);
        joukko.lisaa(5);
        assertTrue(joukko.kuuluu(5));

    }

    @Test
    public void testaaKuuluukoKunEiPitaisiKuulua() {
        joukko.lisaa(6);
        joukko.lisaa(5);
        assertFalse(joukko.kuuluu(2));

    }

    @Test
    public void testaaYhdiste() {
        joukko.lisaa(2);
        joukko.lisaa(3);
        joukkoToinen.lisaa(5);
        joukkoToinen.lisaa(7);


        IntJoukko Joukkoyhdiste = joukko.yhdiste(joukkoToinen);

        assertTrue(Joukkoyhdiste.mahtavuus() == 4);
        assertTrue(Joukkoyhdiste.kuuluu(2));
        assertTrue(Joukkoyhdiste.kuuluu(3));
        assertTrue(Joukkoyhdiste.kuuluu(5));
        assertTrue(Joukkoyhdiste.kuuluu(7));
    }

    @Test
    public void testaaToIntArray() {
        joukko.lisaa(6);
        joukko.lisaa(5);
        int[] array = joukko.toIntArray();

        assertTrue(array.length == 2 && array[0] == 6 && array[1] == 5);

    }

    @Test
    public void testaaPoistaminen() {
        joukko.lisaa(6);
        joukko.lisaa(5);
        joukko.poista(6);
        assertFalse(joukko.kuuluu(6) && joukko.mahtavuus() == 1);

    }

    @Test
    public void ToStringOikein() {
        joukko.lisaa(7);
        joukko.lisaa(8);
        joukko.lisaa(122);

        assertEquals("{7, 8, 122}", joukko.toString());
    }

    @Test
    public void TestaaLeikkaus() {
        joukko.lisaa(7);
        joukko.lisaa(9);
        joukkoToinen.lisaa(9);
        IntJoukko leikkaus = joukko.leikkaus(joukkoToinen);
        assertTrue(leikkaus.mahtavuus() == 1 && leikkaus.kuuluu(9));
    }

    @Test
    public void TestaaErotus() {
        joukko.lisaa(7);
        joukko.lisaa(6);
        joukko.lisaa(122);
        joukkoToinen.lisaa(9);
        joukkoToinen.lisaa(122);
        IntJoukko erotus = joukko.erotus(joukkoToinen);
        assertTrue(erotus.mahtavuus() == 2 && erotus.kuuluu(7) && erotus.kuuluu(6));
    }
}
