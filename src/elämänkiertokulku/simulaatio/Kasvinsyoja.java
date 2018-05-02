
package elämänkiertokulku.simulaatio;


public class Kasvinsyoja extends Elain {
    
    public Kasvinsyoja(int id, Ruutu ruutu, Lauma lauma) {
        super(id, ruutu, lauma);
    }
    
    public boolean onkoOmassaRuudussaKasviruokaa() {
        if (this.ruutu.getKasviruoka() != 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public void maaritaTavoite() {
        if (1 == 0) {
            this.tavoite = ElaimenTavoite.PAKENE;
        } else if (this.ruokatilanne < 10) {
            this.tavoite = ElaimenTavoite.LIIKU_LAHIN_RUOKA;
            if (this.onkoOmassaRuudussaKasviruokaa()) {
                this.tavoite = ElaimenTavoite.SYO;
            }
        } else if (!this.onkoLaumanAlueella()) {
            this.tavoite  = ElaimenTavoite.LIIKU_LAUMA;
        } else if (this.ruokatilanne > 80) {
            this.tavoite = ElaimenTavoite.LISAANNY;
        } else if (!this.onkoOmassaRuudussaKasviruokaa()) {
            this.tavoite = ElaimenTavoite.LIIKU_RUOKA;
        } else {
            this.tavoite = ElaimenTavoite.SYO;
        }
    }
    
    public void loydaLahinRuoka() {
        Ruutu[][] ruudut = this.omaLauma.getRuudukko();
        for (int i = -4; i < 4; i++) {
            for (int j = -4; j < 4; j++) {
                int xKoord = this.ruutu.getxKoord() + i;
                int yKoord = this.ruutu.getyKoord() + j;
                if (ruudut[xKoord][yKoord].getKasviruoka() != 0) {
                    this.setTavoiteRuutu(ruudut[xKoord][yKoord]);
                    return;
                }
            }
        }
        this.setTavoiteRuutu(this.omaLauma.getRuutu());
    }
    
    public void maaritaTavoiteRuutu() {
        if (this.tavoite == ElaimenTavoite.LIIKU_LAHIN_RUOKA) {
            
        }
    }
}
