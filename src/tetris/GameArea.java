
package tetris;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GameArea extends JPanel
{
    private int gridRows;//num of lines
    private int gridColums;//num of colums
    private int  gridCellSize;
    private tetrisBrick brick ;
    private Color[][] background;
    
    public GameArea(JPanel placeholder, int colums)//design mode - placeholder
    {
        
    placeholder.setVisible(false);
    this.setBounds(placeholder.getBounds()); 
    this.setBackground(placeholder.getBackground());
    this.setBorder(placeholder.getBorder());
   
    
    gridColums= colums;
    gridCellSize=this.getBounds().width/gridColums;
    gridRows = this.getBounds().height/gridCellSize;
    background= new Color[gridRows][gridColums];
    background[5][0]=Color.magenta;
    
    
    }
    
        
    public void spawnBrick()
    {
    brick = new tetrisBrick(Color.red,new int[][]{ {1,1}, {1,0}, {1,1} });
    brick.startPositioon(gridColums);
    }
    
    public boolean moveBrickdown()
    {
        if(checkBottom()==false)
        {
          movingBricktoBackground();
          return false;
        }
        
        brick.movedownY();
 //paint the brick on a new location - use the paintComponent methode on a new location       
        repaint();
        
        return true;  
    }
    
    public void dropBrickDown()
    {
        while(checkBottom())
//make sure the brick getting the bottem and stop
        {
            brick.movedownY();
        }
// when brick stop falling we paint the brick in the new location-floor   
        repaint();
    }
    
    public void moveRight()
    {
        if(!checkRight()){return;}
        brick.moveRightX();
 // to prevent lattency of the action we do rpaint on each action  
        repaint();
    } 
    
    public void moveLeft()
    {
       if(!checkLeft()){return;}
       brick.moveLeftX();
 // repaint = to prevent lattency of the action we do rpaint on each action        
       repaint();
    }
    
    public void rotateBrick()            
    {
      brick.rotate();//each time rotate 90 degree
      repaint();
    }
    
    public boolean checkBottom()
 //stop the brick when hiting the floor.
    {
        if (brick.getY()+brick.getHight() == gridRows) 
      {
            return false;
      }
        int[][] shape=brick.getShape();
        int w=brick.getWidth();
        int h=brick.getHight();
        
        for(int col=0 ; col<w ; col++)
        {
            for(int row=h-1 ; row>=0 ;row--)
            {
                if(shape[row][col]!=0)
                {
                    int x =col+brick.getX();
                    int y =row+brick.getY()+1;
                    if(y<0)//in the begining the brick is outside the game area 
                        break;
                    if(background[y][x]!=null)
                        return false;
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean checkLeft()
//prevent the brick to get out the bounds            
    {
        if(brick.getX()==0)//x mean the current location of the brick
            return false;
        return true;
    }
 //prevent the brick to get out the bounds
    public boolean checkRight()
    {
        if(brick.getX()+brick.getWidth()==gridColums)//when the brick is arrive to gridColums it's means the current location of the brick
            return false;
        return true;
    }
    
    
    private void createBrick(Graphics g){
        int h=brick.getHight();
        int w=brick.getWidth();
        Color color=brick.getColor();
        int[][] shape=brick.getShape();
        
        for(int row =0 ;row<brick.getHight();row++){
            for(int col=0 ; col<brick.getWidth();col++){
                if(brick.getShape()[row][col]==1){
                    int x=(brick.getX()+col)*gridCellSize;
                    int y=(brick.getY()+row)*gridCellSize;

                    drawGridCell(g,color,x,y);

                }

    }}}
    
      public void drawGridCell(Graphics g , Color color , int x, int y)
    {
        g.setColor(brick.getColor());
        g.fillRect(x,y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x,y, gridCellSize, gridCellSize);
    }
    
    public void drawbackground(Graphics g)
    {
        Color color;
        for( int row=0;row<gridRows;row++)
        {
            for(int col=0 ; col<gridColums ; col++)
            {
                
                color=background[row][col];
                
                if(color!=null)
                {
                    int x= col*gridCellSize;
                    int y =row*gridCellSize;
                    drawGridCell(g,color,x,y);
                }        
            }
        }
    }
    
    private void movingBricktoBackground()
 //use it to 'save' brick position and paint the brick cells
    {
        int h=brick.getHight();
        int w=brick.getWidth();
        int [][] shape=brick.getShape();
        
        Color color=brick.getColor();
        
        int xPos=brick.getX();
        int yPos=brick.getY();
        
        
        for(int r=0 ; r<h ; r++)
        {
            for(int c=0 ; c<w ; c++)
            {
                if(shape[r][c]==1)
                {
                    background[r+yPos][c+xPos]=color;
                }
            }
        }
    }
  

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        createBrick(g);
        drawbackground(g);
    }
    
}
