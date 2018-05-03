
package elämänkiertokulku.simulaatio;

import java.util.List;


public class Kasvinsyoja extends Elain {
    
    public Kasvinsyoja(int id, Ruutu ruutu, Lauma lauma) {
        super(id, ruutu, lauma);
    }
    
    @Override
    public void ajaElain() {
        if (!kuoleekoElain()) {
            maaritaTavoite();
            switch (this.getTavoite()) {
                case PAKENE:
                    // IMPLEMENT
                    break;
                case LISAANNY:
                    List<Elain> ruudunElaimet = this.getRuutu().getElaimet();
                    boolean ruudussaLisaantyja = false;
                    for (Elain elain : ruudunElaimet) {
                        if (elain.getId() != this.getId() && elain.getLauma() == this.getLauma() && elain.getTavoite() == ElaimenTavoite.LISAANNY) {
                            this.lisaanny(elain);
                            ruudussaLisaantyja = true;
                        }
                    }
                    if (!ruudussaLisaantyja) {
                        loydaLisaantyja();
                        this.liiku();
                    }
                    break;
                case SYO:
                    this.syo();
                    break;
                case LIIKU_LAHIN_RUOKA:
                    loydaLahinRuoka();
                    this.liiku();
                    break;
                case LIIKU_RUOKA:
                    loydaLahinRuoka();
                    this.liiku();
                    break;
                case LIIKU_LAUMA:
                    this.setTavoiteRuutu(this.getLauma().getRuutu());
                    this.liiku();
                    break;
            }
        } else {
            this.tapaElain();
        }
    }
    
    public void syo() {
        int ruudunRuoka = this.getRuutu().getKasviruoka();
        if (ruudunRuoka < 5) {
            this.lisaaRuoka(ruudunRuoka);
            this.getRuutu().vahennaKasviRuoka(ruudunRuoka);
        } else {
            this.lisaaRuoka(5);
            this.getRuutu().vahennaKasviRuoka(5);
        }
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
    
    public void loydaLisaantyja() {
        for (Elain jasen : this.getLauma().getJasenet()) {
            if (jasen.getTavoite() == ElaimenTavoite.LISAANNY && jasen.onkoLaumanAlueella()) {
                this.setTavoiteRuutu(jasen.getRuutu());
                break;
            }
        }
        this.setTavoiteRuutu(this.getLauma().getRuutu());
    }
    
    public void maaritaTavoiteRuutu() {
        if (this.getTavoite() == ElaimenTavoite.LIIKU_LAHIN_RUOKA) {
            
        }
    }
}
