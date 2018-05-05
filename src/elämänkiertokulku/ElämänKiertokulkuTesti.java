
package elämänkiertokulku;

import elämänkiertokulku.kontrolleri.TimerAjo;
import java.util.Timer;
import java.util.TimerTask;


public class ElämänKiertokulkuTesti {
    
    public static void main(String[] args) throws InterruptedException {
        TimerTask timerTask = new TimerAjo();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 500);
    }
}
