
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kartta.Ruutu;
import elämänkiertokulku.kontrolleri.Kontrolleri;
import java.util.ArrayList;
import java.util.List;


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
                // seuraavaRuutu metodi löytyy Liikuteltava luokasta
                this.setTavoiteRuutu(seuraavaRuutu(this.maaritaPakenemisSuunta()));
                break;
            case ODOTA:
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
        if (alueenLihansyojat().size() > 0) {
            this.setTavoite(LaumanTavoite.PAKENE);
            
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
                if (this.getKontrolleri().getKartta().getRuudut()[xKoord][yKoord].getKasviruoka() > 0) {
                    maara += this.getKontrolleri().getKartta().getRuudut()[xKoord][yKoord].getKasviruoka();
                }
            }
        }
        return maara;
    }
    
    public List<Elain> alueenLihansyojat() {
        int omaX = this.getOmaRuutu().getxKoord();
        int omaY = this.getOmaRuutu().getyKoord();
        int kartanX = this.getKontrolleri().getKartta().getRuudut().length;
        int kartanY = this.getKontrolleri().getKartta().getRuudut()[0].length;
        List<Elain> lihansyojat = new ArrayList();
        for (int i = -this.getAlueenKoko(); i < 5; i++) {
            for (int j = -this.getAlueenKoko(); j < 5; j++) {
                if (omaX + i >= 0 && omaX + i < kartanX && omaY >= 0 && omaY < kartanY) {
                    List<Elain> ruudunElaimet = this.getKontrolleri().getKartta().getRuudut()[omaX + i][omaY + j].getElaimet();
                    for (Elain elain : ruudunElaimet) {
                        if (elain.getClass().equals(Lihansyoja.class)) {
                            lihansyojat.add(elain);
                        }
                    }
                }
            }
        }
        return lihansyojat;
    }
    
    public int[] maaritaPakenemisSuunta() {
        List<Elain> lihansyojat = alueenLihansyojat();
        Elain lahin = lihansyojat.get(0);
        int etaisyys = this.etaisyysRuudusta(lahin.getOmaRuutu());
        for (Elain lihansyoja : lihansyojat) {
            if (this.etaisyysRuudusta(lihansyoja.getOmaRuutu()) < etaisyys) {
                lahin = lihansyoja;
                etaisyys = this.etaisyysRuudusta(lihansyoja.getOmaRuutu());
            }
        }
        int pakenemisXSuunta = lahin.getOmaRuutu().getxKoord() - this.getOmaRuutu().getxKoord();
        if (pakenemisXSuunta != 0) {
            pakenemisXSuunta /= Math.abs(pakenemisXSuunta);
        }
        int pakenemisYSuunta = lahin.getOmaRuutu().getyKoord() - this.getOmaRuutu().getyKoord();
        if (pakenemisYSuunta != 0) {
            pakenemisYSuunta /= Math.abs(pakenemisYSuunta);
        }
        return new int[] {pakenemisXSuunta, pakenemisYSuunta};
    }
}

