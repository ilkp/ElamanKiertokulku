
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kartta.Ruutu;
import elämänkiertokulku.kontrolleri.Kontrolleri;
import java.util.ArrayList;
import java.util.List;


public class Lauma extends Liikuteltava {
    private final int ALUEEN_KOKO = 4;
    private final int RUOAN_RAJAARVO = 20;
    private List<Elain> jasenet = new ArrayList();
    private List<Elain> poistettavat = new ArrayList();
    private List<Elain> uudet = new ArrayList();
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
    
    // Nimeämistä varten. Käyttö aliluokissa
    public void ajaLauma() {
        
    }
    
    public void lisaaJasen(Elain elain) {
        elain.getOmaRuutu().lisaaElain(elain);
        this.jasenet.add(elain);
    }
    
    public void siirraPoistettaviin(Elain jasen) {
        this.poistettavat.add(jasen);
    }
    
    public void tyhjennaPoistettavat() {
        this.poistettavat.forEach((jasen) -> {
            this.jasenet.remove(jasen);
        });
        this.poistettavat.clear();
    }
    
    public void lisaaUusiJasen(Elain elain1, Elain elain2) {
        int nopeusMutaatio = this.getKontrolleri().getRandom().nextInt(3) - 1;
        int jalkelaisenNopeus = (int)(elain1.getNopeus() + elain2.getNopeus()) / 2 + nopeusMutaatio;
        Elain uusi = null;
        if (elain1.getClass() == Kasvinsyoja.class) {
            uusi = new Kasvinsyoja(this.getKontrolleri(), this.getKontrolleri().seuraavaId(), jalkelaisenNopeus, elain1.getOmaRuutu(), elain1.getLauma());
        } else {
            uusi = new Lihansyoja(this.getKontrolleri(), this.getKontrolleri().seuraavaId(), jalkelaisenNopeus, elain1.getOmaRuutu(), elain1.getLauma());
        }
        elain1.getOmaRuutu().lisaaElain(uusi);
        uusi.setRuokatilanne(0);
    }
    
    public void siirraUudet() {
        this.uudet.forEach((uusi) -> {
            this.jasenet.add(uusi);
        });
        this.uudet.clear();
    }
    
    public boolean ruutuLaumanAlueella(int x, int y) {
        int laumanX = this.getOmaRuutu().getxKoord();
        int laumanY = this.getOmaRuutu().getyKoord();
        return x <= laumanX + this.ALUEEN_KOKO && x >= laumanX - this.ALUEEN_KOKO && y <= laumanY + this.ALUEEN_KOKO && y >= laumanY - this.ALUEEN_KOKO;
    }
}
