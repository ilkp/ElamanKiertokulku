
package elämänkiertokulku.kontrolleri;

import elämänkiertokulku.simulaatio.Kartta;
import elämänkiertokulku.simulaatio.Lauma;
import java.util.ArrayList;
import java.util.List;

public class Kontrolleri {
    private Kartta kartta;
    private int idLaskuri = 1;
    private List<Lauma> laumat = new ArrayList();
    
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
        lauma.getRuutu().lisaaLauma(lauma);
        this.laumat.add(lauma);
    }

    public int getIdLaskuri() {
        return idLaskuri;
    }

    public void setIdLaskuri(int idLaskuri) {
        this.idLaskuri = idLaskuri;
    }
    
    public void ajaLaumat() {
        this.laumat.forEach((lauma) -> {
            lauma.ajaLauma();
        });
    }
    
    public int seuraavaId() {
        this.idLaskuri++;
        return this.idLaskuri - 1;
    }
}
