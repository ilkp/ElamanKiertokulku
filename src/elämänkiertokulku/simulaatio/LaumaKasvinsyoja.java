
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kartta.Ruutu;
import elämänkiertokulku.kontrolleri.Kontrolleri;


public class LaumaKasvinsyoja extends Lauma {

    public LaumaKasvinsyoja(Kontrolleri kontrolleri, int id, int NOPEUS, Ruutu omaRuutu) {
        super(kontrolleri, id, NOPEUS, omaRuutu);
    }
    
    @Override
    public void ajaLauma() {
        this.getJasenet().forEach((jasen) -> {
            jasen.ajaElain();
        });
        
        maaritaLaumanTavoite();
        switch (this.getTavoite()) {
            case PAKENE:
                // IMPLEMENT
                break;
            case ODOTA:
                // IMPLEMENT
                break;
            case UUSI_RUOKAPAIKKA:
                this.setTavoiteRuutu(seuraavanRuokapaikanSijainti());
                this.liiku();
                break;
            case LIIKU_RUOKA:
                this.liiku();
                break;
        }
        tyhjennaPoistettavat();
    }
    
    @Override
    public void maaritaLaumanTavoite() {
        if (1 == 0) {
            this.setTavoite(LaumanTavoite.PAKENE);
            // IMPLEMENT
        } else if (this.getTavoiteRuutu() != null) {
            this.setTavoite(LaumanTavoite.LIIKU_RUOKA);
        } else if (ruoanMaaraAlueella(this.getOmaRuutu().getxKoord(), this.getOmaRuutu().getyKoord()) > this.getRuoanRajaarvo()) {
            this.setTavoite(LaumanTavoite.ODOTA);
        } else {
            this.setTavoite(LaumanTavoite.UUSI_RUOKAPAIKKA);
        }
    }
    
    // Metodia käytetään, kun lauman alueelta loppuu ruoka, ja lauma halutaan siirtää uuteen ruutuun.
    @Override
    public Ruutu seuraavanRuokapaikanSijainti() {
        double parasArvo = 0;
        int ruoanMaara = 0;
        double ruudunArvo = 0;
        Ruutu[][] ruudut = this.getKontrolleri().getKartta().getRuudut();
        Ruutu palautettava = ruudut[0][0];
        
        for (int i = this.getAlueenKoko(); i < ruudut.length - this.getAlueenKoko(); i++) {
            for (int j = this.getAlueenKoko(); j < ruudut.length - this.getAlueenKoko(); j++) {
                if (i != this.getOmaRuutu().getxKoord() && j != this.getOmaRuutu().getyKoord()) {
                    ruoanMaara = ruoanMaaraAlueella(i , j);
                    ruudunArvo = ruoanMaara / this.etaisyysRuudusta(ruudut[i][j]);
                    if (ruudunArvo > parasArvo) {
                        palautettava = ruudut[i][j];
                        parasArvo = ruudunArvo;
                    }
                }
            }
        }
        return palautettava;
    }
    
    @Override
    public int ruoanMaaraAlueella(int x, int y) {
        int maara = 0;
        for (int i = -this.getAlueenKoko(); i < this.getAlueenKoko() + 1; i++) {
            for (int j = -this.getAlueenKoko(); j < this.getAlueenKoko() + 1; j++) {
                int xKoord = x + i;
                int yKoord = y + j;
                maara += this.getKontrolleri().getKartta().getRuudut()[xKoord][yKoord].getKasviruoka();
            }
        }
        return maara;
    }
}
