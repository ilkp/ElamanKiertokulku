
package elämänkiertokulku.simulaatio;


public class Elain {
    private int id;
    private double kuolintodennakoisyys;
    private final Lauma omaLauma;
    private Ruutu ruutu;
    private Ruutu tavoiteRuutu;
    private int ruokatilanne;
    private ElaimenTavoite tavoite;
    private int liikkeenVaihe;
    private int nopeus = 10;
    
    public Elain(int id, Ruutu ruutu, Lauma lauma) {
        this.id = id;
        this.ruutu = ruutu;
        this.omaLauma = lauma;
        this.kuolintodennakoisyys = 0.01;
        this.liikkeenVaihe = 0;
    }
    
    //Alla getterit ja setterit
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

    public int getRuokatilanne() {
        return ruokatilanne;
    }

    //Käytä vain testaukseen. Simulaatiossa käytä 'vahennaRuoka(int muutos)' tai 'lisaaRuoka(int muutos)'
    public void setRuokatilanne(int ruokatilanne) {
        this.ruokatilanne = ruokatilanne;
    }
    
    public ElaimenTavoite getTavoite() {
        return this.tavoite;
    }
    
    public void setTavoite(ElaimenTavoite tavoite) {
        this.tavoite = tavoite;
    }

    public Ruutu getTavoiteRuutu() {
        return tavoiteRuutu;
    }

    public void setTavoiteRuutu(Ruutu tavoiteRuutu) {
        this.tavoiteRuutu = tavoiteRuutu;
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
    
    public int getLiikkeenVaihe() {
        return this.liikkeenVaihe;
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
            return true;
        }
        this.kuolintodennakoisyys += 0.005;
        return false;
    }
    
    public void tapaElain() {
        this.ruutu.lisaaLiharuoka(100);
        this.omaLauma.siirraPoistettaviin(this);
        this.ruutu.getElaimet().remove(this);
    }
    
    public void lisaaRuoka(int muutos) {
        if (this.ruokatilanne + muutos < 100) {
            this.ruokatilanne += muutos;
        } else {
            this.ruokatilanne = 100;
        }
    }
    
    public void vahennaRuoka(int muutos) {
        if (this.ruokatilanne - muutos > 0) {
            this.ruokatilanne += muutos;
        } else {
            this.ruokatilanne = 0;
        }
    }
    
    public void liiku() {
        if (this.liikkeenVaihe >= this.nopeus) {
            int liikkeenXSuunta = this.getTavoiteRuutu().getxKoord() - this.getRuutu().getxKoord();
            if (liikkeenXSuunta != 0) {
                liikkeenXSuunta /= Math.abs(liikkeenXSuunta);
            }

            int liikkeenYSuunta = this.getTavoiteRuutu().getyKoord() - this.getRuutu().getyKoord();
            if (liikkeenYSuunta != 0) {
                liikkeenYSuunta /= Math.abs(liikkeenYSuunta);
            }

            int uusiX = this.getRuutu().getxKoord() + liikkeenXSuunta;
            int uusiY = this.getRuutu().getyKoord() + liikkeenYSuunta;

            Ruutu seuraavaRuutu = this.getLauma().getRuudukko()[uusiX][uusiY];
            this.setRuutu(seuraavaRuutu);

            if (this.getRuutu() == this.getTavoiteRuutu()) {
                this.setTavoiteRuutu(null);
            }
            this.liikkeenVaihe = 0;
        } else {
            this.liikkeenVaihe++;
        }

        if (this.getRuutu().equals(this.getTavoiteRuutu())) {
            this.setTavoiteRuutu(null);
        }
    }
    
    public void lisaanny(Elain elain) {
        if (elain.getLauma() == this.getLauma() && elain.getRuutu() == this.getRuutu() && elain.getTavoite() == ElaimenTavoite.LISAANNY) {
            this.getLauma().lisaaJasen(new Kasvinsyoja(this.getLauma().getKontrolleri().seuraavaId(), this.getRuutu(), this.getLauma()));
            this.ruokatilanne -= 50;
            elain.setRuokatilanne(elain.getRuokatilanne() - 50);
        }
    }
    
    // tarkistaa onko eläin oman lauman alueella
    public boolean onkoLaumanAlueella() {
        int alueenKoko = this.omaLauma.getAlueenKoko();
        int xKoord = this.ruutu.getxKoord();
        int yKoord = this.ruutu.getyKoord();
        int laumanXKoord = this.omaLauma.getRuutu().getxKoord();
        int laumanYKoord = this.omaLauma.getRuutu().getyKoord();
        return xKoord > laumanXKoord - alueenKoko && xKoord < laumanXKoord + alueenKoko && yKoord > laumanYKoord - alueenKoko && yKoord < laumanYKoord + alueenKoko;
    }
    
    
    @Override
    public String toString() {
        return "id "+this.id+":ruoka "+this.ruokatilanne;
    }
}
