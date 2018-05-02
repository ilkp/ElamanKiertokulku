
package elämänkiertokulku.simulaatio;


public class Kasvinsyoja extends Elain {
    
    public Kasvinsyoja(int id, Ruutu ruutu, Lauma lauma) {
        super(id, ruutu, lauma);
    }
    
    public boolean onkoOmassaRuudussaKasviruokaa() {
        if (this.getRuutu().getKasviruoka() != 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public void maaritaTavoite() {
        if (1 == 0) {
            this.setTavoite(ElaimenTavoite.PAKENE);
            
        } else if (this.getRuokatilanne() < 10) {
            this.setTavoite(ElaimenTavoite.LIIKU_LAHIN_RUOKA);
            if (this.onkoOmassaRuudussaKasviruokaa()) {
                this.setTavoite(ElaimenTavoite.SYO);
            }
            
        } else if (!this.onkoLaumanAlueella()) {
            this.setTavoite(ElaimenTavoite.LIIKU_LAUMA);
            
        } else if (this.getRuokatilanne() > 80) {
            this.setTavoite(ElaimenTavoite.LISAANNY);
            
        } else if (!this.onkoOmassaRuudussaKasviruokaa()) {
            this.setTavoite(ElaimenTavoite.LIIKU_RUOKA);
            
        } else {
            this.setTavoite(ElaimenTavoite.SYO);
        }
    }
    
    // Eläimen tavoiteruuduksi asetetaan ensimmäinen löydetty ruutu, jossa on ruokaa tai oman lauman ruutu, jos ruokaa ei löydy.
    public void loydaLahinRuoka() {
        int alueenKoko = this.getLauma().getAlueenKoko();
        Ruutu[][] ruudut = this.getLauma().getRuudukko();
        for (int i = -alueenKoko; i < alueenKoko; i++) {
            for (int j = -alueenKoko; j < alueenKoko; j++) {
                int xKoord = this.getRuutu().getxKoord() + i;
                int yKoord = this.getRuutu().getyKoord() + j;
                if (ruudut[xKoord][yKoord].getKasviruoka() != 0) {
                    this.setTavoiteRuutu(ruudut[xKoord][yKoord]);
                    return;
                }
            }
        }
        this.setTavoiteRuutu(this.getLauma().getRuutu());
    }
    
    public void maaritaTavoiteRuutu() {
        if (this.getTavoite() == ElaimenTavoite.LIIKU_LAHIN_RUOKA) {
            
        }
    }
}
