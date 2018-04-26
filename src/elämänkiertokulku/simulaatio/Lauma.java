
package elämänkiertokulku.simulaatio;

import java.util.ArrayList;
import java.util.List;


public class Lauma {
    private int id;
    private List<Elain> poistettavat = new ArrayList();
    protected Ruutu ruutu;
    protected Ruutu tavoiteRuutu;
    protected final Ruutu[][] ruudukko;
    protected List<Elain> jasenet = new ArrayList();
    protected LaumanTavoite tavoite;
    
    protected final int ALUEEN_KOKO = 4;
    protected final int RUOAN_RAJAARVO = 20;
    
    public Lauma(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        this.id = id;
        this.ruutu = ruutu;
        this.ruudukko = ruudukko;
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
        return ruudukko;
    }
    
    
    public void ajaLauma() {
    }
    
    public void lisaaJasen(Elain elain) {
        elain.getRuutu().lisaaElain(elain);
        this.jasenet.add(elain);
    }
    
    // yMuutos vaikuttaa xMuutokseen, koska vinoon liikkuminen on mahdollista
    public int etaisyysRuudusta(Ruutu ruutu) {
        int yMuutos = ruutu.getyKoord() - this.ruutu.getyKoord();
        int xMuutos = ruutu.getxKoord() - this.ruutu.getxKoord() - yMuutos;
        return yMuutos + xMuutos;
    }
    
    public void maaritaLaumanTavoite() {
    }
    
    public int ruoanMaaraAlueella(int x, int y) {
        return 0;
    }
    
    public Ruutu seuraavanRuokapaikanSijainti() {
        return null;
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
