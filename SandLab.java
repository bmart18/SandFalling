import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(200, 200);
    lab.run();
  }
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  // task 4 -- Add a SAND particle type
  // task 6 -- Add a WATER particle type

  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[4];    // will need to modify this for tasks 4, 6
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    
    // task 4 -- Add a SAND particle type
    // task 6 -- Add a WATER particle type
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    
    // task 1
    // Insert code to initialize the grid field to refer to a 
    // 2-dimensional array of the same dimensions.
    grid = new int[numRows][numCols];
     
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    // task 2
    // Write code to store this value representing the given tool 
    // in the corresponding position of the grid array
    grid[row][col] = tool;
    
    
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
    // task 3
    // Complete this method so that empty locations are shown in one 
    // color (probably black) and metal locations are shown in 
    // another color (probably gray).
    Color black = new Color(0,0,0);
    Color gray = new Color(100,100,100);
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j <grid[0].length; j++){
         if(grid[i][j] == 1){
            display.setColor(i,j,gray);
         }else if(grid[i][j] == 2){
         display.setColor(i,j,Color.yellow);
         }else if(grid[i][j] == 3){
         display.setColor(i,j,Color.blue);
         }else{
         display.setColor(i,j,black); 
         }
         
      }    
    }
    
    
  }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step()
  {
    // task 5
    // This method should choose a single random valid location. 
    // (Do not use a loop.) If that location contains a sand particle 
    // and the location below it is empty, the particle should move 
    // down one row. (Metal particles will never move.)
    double dRandomOne = Math.random()*grid.length-1;
    double dRandomTwo = Math.random()*grid[0].length;
    
    int randomOne = (int)dRandomOne;
    int randomTwo = (int)dRandomTwo;
   // System.out.println(random);
    //System.out.println(grid[random][random]);
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
