
package elämänkiertokulku.simulaatio;

import java.util.List;


public class LaumaLihansyoja extends Lauma {
    

    public LaumaLihansyoja(int id, Ruutu ruutu, Ruutu[][] ruudukko) {
        super(id, ruutu, ruudukko);
    }
    

    public Ruutu onkoRuokaa() {
        for (int i = -this.getAlueenKoko(); i < this.getAlueenKoko() + 1; i++) {
            for (int j = -this.getAlueenKoko(); j < this.getAlueenKoko() + 1; j++) {
                int xKoord = this.getRuutu().getxKoord() + i;
                int yKoord = this.getRuutu().getyKoord() + j;
                List<Elain> elaimet = this.getRuudukko()[xKoord][yKoord].getElaimet();
                for (Elain elain : elaimet) {
                    if(elain.getClass().equals(Kasvinsyoja.class)){
                        return this.getRuudukko()[xKoord][yKoord];
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
