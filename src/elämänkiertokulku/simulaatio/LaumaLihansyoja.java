
package elämänkiertokulku.simulaatio;

import elämänkiertokulku.kontrolleri.Kontrolleri;
import java.util.List;


public class LaumaLihansyoja extends Lauma {

    public LaumaLihansyoja(Kontrolleri kontrolleri, int id, int NOPEUS, Ruutu omaRuutu) {
        super(kontrolleri, id, NOPEUS, omaRuutu);
    }

    public Ruutu onkoRuokaa() {
        for (int i = -this.getAlueenKoko(); i < this.getAlueenKoko() + 1; i++) {
            for (int j = -this.getAlueenKoko(); j < this.getAlueenKoko() + 1; j++) {
                int xKoord = this.getOmaRuutu().getxKoord() + i;
                int yKoord = this.getOmaRuutu().getyKoord() + j;
                List<Elain> elaimet = this.getKontrolleri().getKartta().getRuudut()[xKoord][yKoord].getElaimet();
                for (Elain elain : elaimet) {
                    if(elain.getClass().equals(Kasvinsyoja.class)){
                        return this.getKontrolleri().getKartta().getRuudut()[xKoord][yKoord];
                    }
                }
             
            }
        }
        return null;
    }
        @Override
    public void maaritaLaumanTavoite() {
        while (onkoRuokaa() != null ){
            /*---- Lauman jäsenet alkavat jahtaamaan kasvinsyöjää.---*/
            /* getJäsenet -> setJäsenKoord(Lisättävä metodi) = onkoRuokaa()*/
            
                /*if(getJäsenKoord == onkoRuokaa()){
                    kasvinsyojä kuolee -> (koordinaatit poistuu)
                    Lihansyojä ruoka lisääntyy
                else if (getJäsenKoord != onkoRuokaa()){
                    liikuta Lihansyöjä Lauma -> getJäsenKoord suuntaan
            */
        }
        if (onkoRuokaa() == null){
            /*Lauma Liikkuu Randomilla.*/
            
        }
    }
    

}
