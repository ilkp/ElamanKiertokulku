
package elämänkiertokulku.simulaatio;


public class Kasvinsyoja extends Elain {

    public Kasvinsyoja(int id, Ruutu ruutu) {
        super(id, ruutu);
    }
    
    public void maaritaTavoite() {
        if (this.ruokatilanne < 80) {
            this.tavoite = ElaimenTavoite.LOYDARUOKA;
        } else {
            this.tavoite = ElaimenTavoite.LISAANNY;
        }
    }
}
