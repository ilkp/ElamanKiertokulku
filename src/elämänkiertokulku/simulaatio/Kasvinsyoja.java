
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
                    boolean lisaantyja = false;
                    for (Elain elain : ruudunElaimet) {
                        if (elain != this && elain.getLauma() == this.getLauma() && elain.getRuokatilanne() > 80) {
                            this.getLauma().lisaaUusiJasen(this, elain);
                            this.setRuokatilanne(1);
                            elain.setRuokatilanne(1);
                            lisaantyja = true;
                            break;
                        }
                    }
                    if (lisaantyja == false) {
                        loydaLisaantyja();
                        this.liiku();
                        break;
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
        //this.vahennaRuoka(0.5);
    }
    
    public void syo() {
        double ruudunRuoka = this.getOmaRuutu().getKasviruoka();
        if (ruudunRuoka < 5) {
            this.lisaaRuoka(ruudunRuoka);
            this.getOmaRuutu().vahennaKasviRuoka(ruudunRuoka);
        } else {
            this.lisaaRuoka(5);
            this.getOmaRuutu().vahennaKasviRuoka(5);
        }
    }
    
    public boolean onkoOmassaRuudussaKasviruokaa() {
        return this.getOmaRuutu().getKasviruoka() > 0;
    }
    
    @Override
    public void maaritaTavoite() {
        if (1 == 0) {
            this.setTavoite(ElaimenTavoite.PAKENE);
            
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
    // Ruokaa haetaan 4x4 alueelta sisältä ulos päin.
    public Ruutu loydaLahinRuoka() {
        Ruutu[][] ruudut = this.getKontrolleri().getKartta().getRuudut();
        Ruutu oma = this.getOmaRuutu();
        Ruutu vara = null;
        int skannauksenAloitus = 0;
        for (int i = 0; i < 4; i++) {
            skannauksenAloitus--;
            for (int j = skannauksenAloitus; j < Math.abs(skannauksenAloitus) + 1; j++) {
                for (int k = skannauksenAloitus; k < Math.abs(skannauksenAloitus) + 1; k++) {
                    if (this.getLauma().ruutuLaumanAlueella(oma.getxKoord() + j, oma.getyKoord() + k) && ruudut[oma.getxKoord() + j][oma.getyKoord() + k].getKasviruoka() > 0) {
                        if (ruudut[oma.getxKoord() + j][oma.getyKoord() + k].getElaimet().size() > 0) {
                            vara = ruudut[oma.getxKoord() + j][oma.getyKoord() + k];
                        } else {
                            return ruudut[oma.getxKoord() + j][oma.getyKoord() + k];
                        }
                    }
                }
            }
        }
        if (vara != null) {
            return vara;
        }
        return this.getLauma().getOmaRuutu();
    }
    
    public void loydaLisaantyja() {
        this.setTavoiteRuutu(this.getLauma().getOmaRuutu());
        for (Elain jasen : this.getLauma().getJasenet()) {
            if (jasen.getId() != this.getId() && jasen.getRuokatilanne() > 80) {
                this.setTavoiteRuutu(jasen.getOmaRuutu());
                break;
            }
        }
    }
}
