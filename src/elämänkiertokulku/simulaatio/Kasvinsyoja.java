
package elämänkiertokulku.simulaatio;

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
                case LIIKU_LAHIN_RUOKA:
                    System.out.println("testi liiku lahin ruoka");
                    loydaLahinRuoka();
                    this.liiku();
                    break;
                case LIIKU_RUOKA:
                    loydaLahinRuoka();
                    this.liiku();
                    break;
                case LIIKU_LAUMA:
                    this.setTavoiteRuutu(this.getLauma().getOmaRuutu());
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
        if (this.getOmaRuutu().getKasviruoka() != 0) {
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
        Ruutu[][] ruudut = this.getLauma().getKontrolleri().getKartta().getRuudut();
        for (int i = -alueenKoko; i < alueenKoko; i++) {
            for (int j = -alueenKoko; j < alueenKoko; j++) {
                int xKoord = this.getOmaRuutu().getxKoord() + i;
                int yKoord = this.getOmaRuutu().getyKoord() + j;
                if (ruudut[xKoord][yKoord].getKasviruoka() != 0) {
                    this.setTavoiteRuutu(ruudut[xKoord][yKoord]);
                    return;
                }
            }
        }
        this.setTavoiteRuutu(this.getLauma().getOmaRuutu());
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
    
    public void maaritaTavoiteRuutu() {
        if (this.getTavoite() == ElaimenTavoite.LIIKU_LAHIN_RUOKA) {
            
        }
    }
}
