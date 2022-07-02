
package tetris;

import java.awt.Color;


public class tetrisBrick {
    private int[][] shape;
    private Color color;
    private int x,y;
    private int[][][] shapes ;
    private int corentRotation;
     
    
    public tetrisBrick(Color color, int[][] shape)  
    {
        this.color=color;
        this.shape=shape;
        initShapes();
      
          
    }
    
    private void initShapes()
//store the all different rotation shapes of a single brick;
    {
        shapes=new int[4][][];
        for(int i=0 ; i< 4 ; i++)
        {
            int r=shape[0].length;
            int c=shape.length;
            
            shapes[i]=new int[r][c];
            
            for(int y=0; y<r ; y++)
            {
                for(int x=0 ; x<c ; x++)
                {
                    shapes[i][y][x]=shape[c-x-1][y];
                }
            }
            shape=shapes[i];
        }
    }
    
    public void startPositioon(int gridWidth)
    {
        corentRotation=0;
        shape=shapes[corentRotation];
        
        y=-getHight();
        x=((gridWidth-getWidth())/2);// for brick fall in the middle of the squere area
    }
    
    public int getHight(){
        return shape.length;
    }
    
      public int getWidth(){
        return shape[0].length;
    }
      
      public int[][] getShape(){
        return shape;
      }
    
      public Color getColor(){
      return color;
      }
      
      public int getX(){
      return x ;
      }
      
      public int getY(){
      return y ;
      }
      
      public void movedownY(){
      y++;}
    
      
      public void moveRightX(){
      x++;}
      
      public void moveLeftX(){
      x--;}
      
      public void rotate()
      {
          corentRotation++;
          if(corentRotation>3)
              corentRotation=0;
          shape=shapes[corentRotation];
          
      }
}
