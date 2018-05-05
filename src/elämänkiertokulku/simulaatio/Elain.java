
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kartta.Ruutu;
import elämänkiertokulku.kontrolleri.Kontrolleri;


public class Elain extends Liikuteltava {
    private double kuolintodennakoisyys;
    private final Lauma omaLauma;
    private double ruokatilanne;
    private ElaimenTavoite tavoite;
    
    public Elain(Kontrolleri kontrolleri, int id, int nopeus, Ruutu omaRuutu, Lauma lauma) {
        super (kontrolleri, id, nopeus, omaRuutu);
        this.omaLauma = lauma;
        this.kuolintodennakoisyys = 0.01;
    }
    
    //Alla getterit ja setterit
    public double getRuokatilanne() {
        return ruokatilanne;
    }

    //Käytä vain testaukseen. Simulaatiossa käytä 'vahennaRuoka(int muutos)' tai 'lisaaRuoka(int muutos)'
    public void setRuokatilanne(double ruokatilanne) {
        this.ruokatilanne = ruokatilanne;
    }
    
    public ElaimenTavoite getTavoite() {
        return this.tavoite;
    }
    
    public void setTavoite(ElaimenTavoite tavoite) {
        this.tavoite = tavoite;
    }

    public double getKuolintodennakoisyys() {
        return kuolintodennakoisyys;
    }

    public void setKuolintodennakoisyys(double kuolintodennakoisyys) {
        this.kuolintodennakoisyys = kuolintodennakoisyys;
    }
    
    public Lauma getLauma() {
        return this.omaLauma;
    }
    
    
    //Alla omat metodit
    
    // Metodin nimeämistä varten, käyttö aliluokassa
    public void maaritaTavoite(){}
    
    // Metodin nimeämistä varten, käyttö aliluokassa
    public void ajaElain() {
        
    }
    
    public boolean kuoleekoElain() {
        double rnd = Math.random();
        if (rnd < this.kuolintodennakoisyys * (1 + (100 - this.ruokatilanne) / 100)) {
            return false; // KUULUU OLLA TRUE, FALSE VAIN TESTAUKSEEN
        }
        this.kuolintodennakoisyys += 0.005;
        return false;
    }
    
    public void tapaElain() {
        this.getOmaRuutu().lisaaLiharuoka(100);
        this.omaLauma.siirraPoistettaviin(this);
        this.getOmaRuutu().getElaimet().remove(this);
        System.out.println(this.getId()+" kuoli");
    }
    
    public void lisaaRuoka(double muutos) {
        if (this.ruokatilanne + muutos < 100) {
            this.ruokatilanne += muutos;
        } else {
            this.ruokatilanne = 100;
        }
    }
    
    public void vahennaRuoka(double muutos) {
        if (this.ruokatilanne - muutos > 0) {
            this.ruokatilanne += muutos;
        } else {
            this.ruokatilanne = 0;
        }
    }
    
    public void lisaanny(Elain elain) {
        if (elain.getLauma() == this.getLauma() && elain.getOmaRuutu()== this.getOmaRuutu()&& elain.getTavoite() == ElaimenTavoite.LISAANNY) {
            int nopeusMutaatio = this.getKontrolleri().getRandom().nextInt(3) - 1;
            int jalkelaisenNopeus = (int)(this.getNopeus() + elain.getNopeus()) / 2 + nopeusMutaatio;
            this.getLauma().lisaaJasen(new Kasvinsyoja(this.getKontrolleri(), this.getKontrolleri().seuraavaId(), jalkelaisenNopeus, this.getOmaRuutu(), this.getLauma()));
            this.ruokatilanne -= 50;
            elain.setRuokatilanne(elain.getRuokatilanne() - 50);
        }
    }
    
    // tarkistaa onko eläin oman lauman alueella
    public boolean onkoLaumanAlueella() {
        int alueenKoko = this.omaLauma.getAlueenKoko();
        int xKoord = this.getOmaRuutu().getxKoord();
        int yKoord = this.getOmaRuutu().getyKoord();
        int laumanXKoord = this.omaLauma.getOmaRuutu().getxKoord();
        int laumanYKoord = this.omaLauma.getOmaRuutu().getyKoord();
        return xKoord > laumanXKoord - alueenKoko && xKoord < laumanXKoord + alueenKoko && yKoord > laumanYKoord - alueenKoko && yKoord < laumanYKoord + alueenKoko;
    }
    
}
