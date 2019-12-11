import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int DIRT = 2;
  public static final int WATER = 3;
  public static final int RUSTEDMETAL = 4;
  public static final int SAND = 5;
  public int rustCounter = 0;
  public int rustDestroyer = 0;

  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[6]; 
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[DIRT] = "Dirt";
    names[WATER] = "Water";
    names[RUSTEDMETAL] = "Rusted Metal";
    names[SAND] = "Sand";
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    
    grid = new int[numRows][numCols];
     
  }
  
  private void locationClicked(int row, int col, int tool)
  {
  
    grid[row][col] = tool;
    
    
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
    Color black = new Color(0,0,0);
    Color gray = new Color(100,100,100);
    Color rust = new Color(183, 65, 14);
    Color brown = new Color(101,67,33);
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j <grid[0].length; j++){
         if(grid[i][j] == 1){
            display.setColor(i,j,gray);
         }else if(grid[i][j] == 2){
         display.setColor(i,j,brown);
         }else if(grid[i][j] == 3){
         display.setColor(i,j,Color.blue);
         }else if(grid[i][j]==4){
         display.setColor(i,j, rust);
         }else if(grid[i][j]==5){
         
         display.setColor(i,j,Color.yellow);
         }else{
         display.setColor(i,j,black); 
         }
         
      }    
    }
    
    
  }
  public void step()
  {

    double dRandomOne = Math.random()*grid.length-1;
    double dRandomTwo = Math.random()*grid[0].length;
    
    int randomOne = (int)dRandomOne;
    int randomTwo = (int)dRandomTwo;
    if(grid[randomOne][randomTwo] == 5){
      double waterRandom = Math.random();
      if(waterRandom < 0.33){
         if(grid[randomOne+1][randomTwo]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo]=5;
      }else if(grid[randomOne+1][randomTwo]==3){
         grid[randomOne][randomTwo] = 3;
         grid[randomOne+1][randomTwo] = 5;
      }
         
      }else if(waterRandom < 0.66 && randomTwo+1 < grid[0].length){
         if(grid[randomOne+1][randomTwo+1]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo+1]=5;
      }else if(grid[randomOne+1][randomTwo+1]==3){
         grid[randomOne][randomTwo] = 3;
         grid[randomOne+1][randomTwo+1] = 5;
      }

      }else if((randomTwo-1 >= 0)){
         if(grid[randomOne+1][randomTwo-1]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo-1]=5;
      }else if(grid[randomOne+1][randomTwo-1]==3){
         grid[randomOne][randomTwo] = 3;
         grid[randomOne+1][randomTwo-1] = 5;
      }

      }

    }
    if(grid[randomOne][randomTwo] == 1 && 
        ((randomOne+1 < grid.length && grid[randomOne +1][randomTwo] == 3) || 
        (randomOne+1 < grid.length && randomTwo+1 < grid[0].length && grid[randomOne+1][randomTwo+1] == 3) ||
        (randomOne+1 < grid.length && randomTwo-1 > 0 && grid[randomOne+1][randomTwo-1] == 3) ||
        (randomTwo+1 < grid[0].length && grid[randomOne][randomTwo+1] == 3) || 
        (randomTwo-1 > 0 && grid[randomOne][randomTwo-1] == 3) ||  
        (randomOne-1 > 0 && grid[randomOne -1][randomTwo] == 3) ||
        (randomOne-1 > 0 && randomTwo+1 < grid[0].length && grid[randomOne-1][randomTwo+1] == 3) || 
        (randomOne-1 > 0 && randomTwo-1 > 0 && grid[randomOne-1][randomTwo-1] == 3))){
      rustCounter+=1;
      if(rustCounter > 4000){
         grid[randomOne][randomTwo] = 4;
         rustCounter = 0;
      }
    }
    if(grid[randomOne][randomTwo] == 4 && 
        ((randomOne+1 < grid.length && grid[randomOne +1][randomTwo] == 3) || 
        (randomOne+1 < grid.length && randomTwo+1 < grid[0].length && grid[randomOne+1][randomTwo+1] == 3) ||
        (randomOne+1 < grid.length && randomTwo-1 > 0 && grid[randomOne+1][randomTwo-1] == 3) ||
        (randomTwo+1 < grid[0].length && grid[randomOne][randomTwo+1] == 3) || 
        (randomTwo-1 > 0 && grid[randomOne][randomTwo-1] == 3) ||  
        (randomOne-1 > 0 && grid[randomOne -1][randomTwo] == 3) ||
        (randomOne-1 > 0 && randomTwo+1 < grid[0].length && grid[randomOne-1][randomTwo+1] == 3) || 
        (randomOne-1 > 0 && randomTwo-1 > 0 && grid[randomOne-1][randomTwo-1] == 3))){
      rustDestroyer +=1;
      if(rustDestroyer > 4000){
         grid[randomOne][randomTwo]=0;
         rustDestroyer = 0;
      }
   // System.out.println(random);
    }//System.out.println(grid[random][random]);
    if(grid[randomOne][randomTwo] == 2)
    {
      if(grid[randomOne+1][randomTwo]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo]=2;
      }else if(grid[randomOne+1][randomTwo]==3){
         grid[randomOne][randomTwo] = 3;
         grid[randomOne+1][randomTwo] = 2;
      }
      
    }else if(grid[randomOne][randomTwo] == 3){
      double waterRandom = Math.random();
      if(waterRandom < 0.33 && grid[randomOne+1][randomTwo] == 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo]=3;
         
      }else if(waterRandom < 0.66 && randomTwo+1 < grid[0].length && grid[randomOne][randomTwo+1] == 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne][randomTwo+1]=3;
      }else if((randomTwo-1 >= 0) && (grid[randomOne][randomTwo-1] == 0)){
         grid[randomOne][randomTwo]=0;
         grid[randomOne][randomTwo-1]=3;
      }
    
    }
    

    
  }
  
  public void run()
  {
    while (true)
    {
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
