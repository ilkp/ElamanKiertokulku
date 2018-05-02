
package elämänkiertokulku.simulaatio;

import java.util.ArrayList;
import java.util.List;


public class Lauma {
    private final int ALUEEN_KOKO = 4;
    private final int RUOAN_RAJAARVO = 20;
    private final int NOPEUS = 10;
    private final Ruutu[][] RUUDUKKO;
    private int id;
    private List<Elain> jasenet = new ArrayList();
    private List<Elain> poistettavat = new ArrayList();
    private Ruutu ruutu;
    private Ruutu tavoiteRuutu;
    private LaumanTavoite tavoite;
    private int liikkeenVaihe; // liikkeen vaihetta kasvatetaan yhdellä, kunnes sen arvo on >= lauman nopeus, jolloin lauma saa liikkua
    
    public Lauma(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        this.id = id;
        this.ruutu = ruutu;
        this.RUUDUKKO = ruudukko;
        this.liikkeenVaihe = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ruutu getRuutu() {
        return ruutu;
    }

    public void setRuutu(Ruutu ruutu) {
        this.ruutu = ruutu;
    }

    public List<Elain> getJasenet() {
        return jasenet;
    }

    public void setJasenet(List<Elain> jasenet) {
        this.jasenet = jasenet;
    }

    public Ruutu[][] getRuudukko() {
        return RUUDUKKO;
    }
    
    public void setTavoite(LaumanTavoite tavoite) {
        this.tavoite = tavoite;
    }
    
    public LaumanTavoite getTavoite() {
        return this.tavoite;
    }
    
    public void setTavoiteRuutu(Ruutu ruutu) {
        this.tavoiteRuutu = ruutu;
    }
    
    public Ruutu getTavoiteRuutu() {
        return this.tavoiteRuutu;
    }
    
    public void setLiikkeenVaihe(int vaihe) {
        this.liikkeenVaihe = vaihe;
    }
    
    public int getLiikkeenVaihe() {
        return this.liikkeenVaihe;
    }
    
    public int getNopeus() {
        return this.NOPEUS;
    }
    
    public int getAlueenKoko() {
        return this.ALUEEN_KOKO;
    }
    
    public int getRuoanRajaarvo() {
        return this.RUOAN_RAJAARVO;
    }
    
    
    
    // Nimeämistä varten. Käyttö aliluokissa
    public void maaritaLaumanTavoite() {
        
    }
    
    // Nimeämistä varten. Käyttö aliluokissa
    public int ruoanMaaraAlueella(int x, int y) {
        return 0;
    }
    
    // Nimeämistä varten. Käyttö aliluokissa
    public Ruutu seuraavanRuokapaikanSijainti() {
        return this.ruutu;
    }
    
    public void ajaLauma() {
        System.out.println("testi");
    }
    
    public void lisaaJasen(Elain elain) {
        elain.getRuutu().lisaaElain(elain);
        this.jasenet.add(elain);
    }
    
    // yMuutos vaikuttaa xMuutokseen, koska vino liikkuminen on mahdollista
    public int etaisyysRuudusta(Ruutu ruutu) {
        int yMuutos = ruutu.getyKoord() - this.ruutu.getyKoord();
        int xMuutos = ruutu.getxKoord() - this.ruutu.getxKoord() - yMuutos;
        return yMuutos + xMuutos;
    }
    
    public void siirraPoistettaviin(Elain jasen) {
        this.jasenet.remove(jasen);
        this.poistettavat.add(jasen);
    }
    
    public void tyhjennaPoistettavat() {
        this.poistettavat.forEach((jasen) -> {
            this.poistettavat.remove(jasen);
        });
    }
    
    @Override
    public String toString() {
        return this.id+" "+this.jasenet;
    }
}
