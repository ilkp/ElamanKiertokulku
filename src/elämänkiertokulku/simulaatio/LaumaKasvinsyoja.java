
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kontrolleri.Kontrolleri;


public class LaumaKasvinsyoja extends Lauma {

    public LaumaKasvinsyoja(Kontrolleri kontrolleri, int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        super(kontrolleri, id, ruutu, ruudukko);
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
        } else if (ruoanMaaraAlueella(this.getRuutu().getxKoord(), this.getRuutu().getyKoord()) > this.getRuoanRajaarvo()) {
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
        Ruutu palautettava = this.getRuudukko()[0][0];
        
        for (int i = this.getAlueenKoko(); i < this.getRuudukko().length - this.getAlueenKoko(); i++) {
            for (int j = this.getAlueenKoko(); j < this.getRuudukko()[i].length - this.getAlueenKoko(); j++) {
                if (i != this.getRuutu().getxKoord() && j != this.getRuutu().getyKoord()) {
                    ruoanMaara = ruoanMaaraAlueella(i , j);
                    ruudunArvo = ruoanMaara / this.etaisyysRuudusta(this.getRuudukko()[i][j]);
                    if (ruudunArvo > parasArvo) {
                        palautettava = this.getRuudukko()[i][j];
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
                maara += this.getRuudukko()[xKoord][yKoord].getKasviruoka();
            }
        }
        return maara;
    }
}
