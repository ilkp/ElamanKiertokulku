
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kontrolleri.Kontrolleri;
import java.util.ArrayList;
import java.util.List;


public class Lauma extends Liikuteltava {
    private final int ALUEEN_KOKO = 4;
    private final int RUOAN_RAJAARVO = 20;
    private List<Elain> jasenet = new ArrayList();
    private List<Elain> poistettavat = new ArrayList();
    private LaumanTavoite tavoite;

    public Lauma(Kontrolleri kontrolleri, int id, int NOPEUS, Ruutu omaRuutu) {
        super(kontrolleri, id, NOPEUS, omaRuutu);
    }

    public List<Elain> getJasenet() {
        return jasenet;
    }

    public void setJasenet(List<Elain> jasenet) {
        this.jasenet = jasenet;
    }
    
    public void setTavoite(LaumanTavoite tavoite) {
        this.tavoite = tavoite;
    }
    
    public LaumanTavoite getTavoite() {
        return this.tavoite;
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
        return null;
    }
    
    public void ajaLauma() {
        
    }
    
    public void lisaaJasen(Elain elain) {
        elain.getOmaRuutu().lisaaElain(elain);
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
}
