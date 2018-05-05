
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kartta.Ruutu;
import elämänkiertokulku.kontrolleri.Kontrolleri;
import java.util.List;


public class Kasvinsyoja extends Elain {
    
    public Kasvinsyoja(Kontrolleri kontrolleri, int id, int nopeus, Ruutu omaRuutu, Lauma lauma) {
        super(kontrolleri, id, nopeus, omaRuutu, lauma);
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
                    List<Elain> ruudunElaimet = this.getOmaRuutu().getElaimet();
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
                case LIIKU:
                    this.liiku();
                    break;
            }
        } else {
            this.tapaElain();
        }
    }
    
    public void syo() {
        int ruudunRuoka = this.getOmaRuutu().getKasviruoka();
        if (ruudunRuoka < 5) {
            this.lisaaRuoka(ruudunRuoka);
            this.getOmaRuutu().vahennaKasviRuoka(ruudunRuoka);
        } else {
            this.lisaaRuoka(5);
            this.getOmaRuutu().vahennaKasviRuoka(5);
        }
    }
    
    public boolean onkoOmassaRuudussaKasviruokaa() {
        return this.getOmaRuutu().getKasviruoka() != 0;
    }
    
    @Override
    public void maaritaTavoite() {
        if (1 == 0) {
            this.setTavoite(ElaimenTavoite.PAKENE);
            
        } else if (this.getRuokatilanne() < 10) {
            if (this.onkoOmassaRuudussaKasviruokaa()) {
                this.setTavoite(ElaimenTavoite.SYO);
                this.setTavoiteRuutu(null);
            } else {
                this.setTavoite(ElaimenTavoite.LIIKU);
                this.setTavoiteRuutu(loydaLahinRuoka());
            }
            
        } else if (!this.onkoLaumanAlueella()) {
            this.setTavoite(ElaimenTavoite.LIIKU);
            this.setTavoiteRuutu(this.getLauma().getOmaRuutu());
            
        } else if (this.getRuokatilanne() > 80) {
            this.setTavoite(ElaimenTavoite.LISAANNY);
            
        } else if (!this.onkoOmassaRuudussaKasviruokaa()) {
            this.setTavoite(ElaimenTavoite.LIIKU);
            this.setTavoiteRuutu(loydaLahinRuoka());
            
        } else {
            this.setTavoite(ElaimenTavoite.SYO);
            this.setTavoiteRuutu(null);
        }
    }
    
    // Eläimen tavoiteruuduksi asetetaan ensimmäinen löydetty ruutu, jossa on ruokaa tai oman lauman ruutu, jos ruokaa ei löydy.
    public Ruutu loydaLahinRuoka() {
        Ruutu[][] ruudut = this.getKontrolleri().getKartta().getRuudut();
        Ruutu oma = this.getOmaRuutu();
        int skannauksenAloitus = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = skannauksenAloitus; j < Math.abs(skannauksenAloitus) + 1; j++) {
                for (int k = skannauksenAloitus; k < Math.abs(skannauksenAloitus) + 1; k++) {
                    if (ruudut[oma.getxKoord() + j][oma.getyKoord() + k].getKasviruoka() > 0) {
                        return ruudut[oma.getxKoord() + j][oma.getyKoord() + k];
                    }
                }
            }
            skannauksenAloitus--;
        }
        return this.getLauma().getOmaRuutu();
    }
    
    public void loydaLisaantyja() {
        for (Elain jasen : this.getLauma().getJasenet()) {
            if (jasen.getTavoite() == ElaimenTavoite.LISAANNY && jasen.onkoLaumanAlueella()) {
                this.setTavoiteRuutu(jasen.getOmaRuutu());
                break;
            }
        }
        this.setTavoiteRuutu(this.getLauma().getOmaRuutu());
    }
}
