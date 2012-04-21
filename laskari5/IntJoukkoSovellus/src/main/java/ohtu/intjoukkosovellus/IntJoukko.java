package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luvut = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        luvut = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (alkioidenLkm == luvut.length) {
            kasvataKokoa();
        }
        luvut[alkioidenLkm] = luku;
        alkioidenLkm++;
        return true;

    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        int poistettavanIndeksi = -1;

        poistettavanIndeksi = etsiIndeksiJossaPoistettava(luku);
        siirraTaulukkoaIndeksiinAsti(poistettavanIndeksi);
        alkioidenLkm--;
        return true;




    }

    public void siirraTaulukkoaIndeksiinAsti(int indeksi) {
        for (int j = indeksi; j < alkioidenLkm - 1; j++) {
            luvut[j] = luvut[j + 1];
        }
    }

    public int etsiIndeksiJossaPoistettava(int luku) {
        for (int i = 0; i < luvut.length; i++) {
            if (luku == luvut[i]) {
                return i;
            }
        }
        return -1;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }
        String tuotos = "";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += luvut[i];
            tuotos += ", ";
        }
        tuotos += luvut[alkioidenLkm - 1];
        return "{" + tuotos + "}";

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }

    public IntJoukko yhdiste(IntJoukko toinen) {
        IntJoukko yhdiste = new IntJoukko(toinen.mahtavuus() + alkioidenLkm);
        int[] toinenArray = toinen.toIntArray();

        for (int i = 0; i < alkioidenLkm; i++) {
            yhdiste.lisaa(luvut[i]);
        }
        for (int i = 0; i < toinenArray.length; i++) {
            yhdiste.lisaa(toinenArray[i]);

        }
        return yhdiste;
    }

    public IntJoukko leikkaus(IntJoukko toinen) {
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < luvut.length; i++) {
            if (toinen.kuuluu(luvut[i])) {
                leikkaus.lisaa(luvut[i]);
            }
        }
        return leikkaus;
    }

    public IntJoukko erotus(IntJoukko toinen) {
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < alkioidenLkm; i++) {
            if (!toinen.kuuluu(luvut[i])) {
                erotus.lisaa(luvut[i]);
            }

        }
        return erotus;
    }

    private void kasvataKokoa() {
        int[] apu = new int[luvut.length + kasvatuskoko];
        System.arraycopy(luvut, 0, apu, 0, luvut.length);
        luvut = apu;
    }
}