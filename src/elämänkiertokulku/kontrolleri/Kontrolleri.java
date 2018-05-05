
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.kartta.Kartta;
import elämänkiertokulku.kartta.Ruutu;
import elämänkiertokulku.simulaatio.Lauma;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kontrolleri {
    private Random random = new Random();
    private Kartta kartta;
    private int idLaskuri = 1;
    private List<Lauma> laumat = new ArrayList();
    private int tikki = 0;
    
    public Kontrolleri(int kartanLeveys, int kartanKorkeus) {
        this.kartta = new Kartta(kartanLeveys, kartanKorkeus);
    }
    
    public Kartta getKartta() {
        return this.kartta;
    }
    
    public void setKartta(Kartta kartta) {
        this.kartta = kartta;
    }

    public List getLaumat() {
        return laumat;
    }

    public void lisaaLauma(Lauma lauma) {
        lauma.getOmaRuutu().lisaaLauma(lauma);
        this.laumat.add(lauma);
    }

    public int getIdLaskuri() {
        return idLaskuri;
    }

    public void setIdLaskuri(int idLaskuri) {
        this.idLaskuri = idLaskuri;
    }
    
    public void tikkaa() {
        this.tikki++;
    }
    
    public int getTick() {
        return this.tikki;
    }
    
    public Random getRandom() {
        return this.random;
    }
    
    public void ajaLaumat() {
        this.laumat.forEach((lauma) -> {
            lauma.ajaLauma();
        });
    }
    
    public void ajaRuudut() {
        for (Ruutu[] ruudut : this.kartta.getRuudut()) {
            for (Ruutu ruutu : ruudut) {
                if (ruutu.getKasvaakoRuoka()) {
                    ruutu.lisaaKasviruoka(0.1);
                }
            }
        }
    }
    
    public int seuraavaId() {
        this.idLaskuri++;
        return this.idLaskuri - 1;
    }
    public int randomizer(){
        int luku = random.nextInt(100);
        return luku;
    }
}
