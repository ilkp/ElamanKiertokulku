
package elämänkiertokulku.simulaatio;

import java.util.ArrayList;
import java.util.List;


public class Lauma {
    private int id;
    private Ruutu ruutu;
    private final Ruutu[][] ruudukko;
    private List<Elain> jasenet = new ArrayList();
    private List<Elain> poistettavat = new ArrayList();
    
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
        this.jasenet.forEach((elain) -> {
            elain.ajaElain();
        });
        tyhjennaPoistettavat();
    }
    
    public void lisaaJasen(Elain elain) {
        elain.getRuutu().lisaaElain(elain);
        this.jasenet.add(elain);
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
