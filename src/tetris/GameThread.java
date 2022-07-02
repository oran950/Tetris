// this class responsible on the time between the moves
package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GameThread extends Thread { 
    private GameArea bab;
    
    public GameThread(GameArea bab)
    {
        
    this.bab=bab;
    
    }

      
    @Override
    public void run()
    {
        while(true)
        {// infinty loop for the brick keep falling
            
            bab.spawnBrick();
            while (bab.moveBrickdown()){

            try {

                

                Thread.sleep(300);// the speed of faliing brick

            } catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
    }
    
}
