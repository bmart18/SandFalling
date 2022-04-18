import java.awt.*;
import java.util.*;

public class SandLab
{
  public static void main(String[] args)
  {
    SandLab lab = new SandLab(120, 120);
    lab.run();
  }
  
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int DIRT = 2;
  public static final int WATER = 3;
  public static final int RUSTEDMETAL = 4;
  public static final int SAND = 5;
  public static final int LAVA = 6;
  public static final int STONE = 7;
  public int rustCounter = 0;
  public int rustDestroyer = 0;
  public int lavacounter = 0;
  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    names = new String[8]; 
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[DIRT] = "Dirt";
    names[WATER] = "Water";
    names[RUSTEDMETAL] = "Rusted Metal";
    names[SAND] = "Sand";
    names[LAVA] = "Lava";
    names[STONE] = "Stone";
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    
    grid = new int[numRows][numCols];
     
  }
  
  private void locationClicked(int row, int col, int tool)
  {
  
    grid[row][col] = tool;
    
    
  }

  
  public boolean checkNeighbors(int gridx, int gridy, int block)
  {
	  if(((gridx+1 < grid.length) && grid[gridx +1][gridy] == block) || 
		        (gridx+1 < grid.length && gridy+1 < grid[0].length && grid[gridx+1][gridy+1] == block) ||
		        (gridx+1 < grid.length && gridy-1 > 0 && grid[gridx+1][gridy-1] == block) ||
		        (gridy+1 < grid[0].length && grid[gridx][gridy+1] == block) || 
		        (gridy-1 > 0 && grid[gridx][gridy-1] == block) ||  
		        (gridx-1 > 0 && grid[gridx -1][gridy] == block) ||
		        (gridx-1 > 0 && gridy+1 < grid[0].length && grid[gridx-1][gridy+1] == block) || 
		        (gridx-1 > 0 && gridy-1 > 0 && grid[gridx-1][gridy-1] == block)){
		      return true;
	  }
	return false;
  }
  
  //copies each element of grid into the display
  public void updateDisplay()
  {
    Color black = new Color(0,0,0);
    Color gray = new Color(100,100,100);
    Color rust = new Color(183, 65, 14);
    Color lava = new Color(200,75,0);
    Color stone = new Color(61, 59, 56);
    Color brown = new Color(101,67,33);
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j <grid[0].length; j++){
         if(grid[i][j] == 1){
            display.setColor(i,j,gray);
         }else if(grid[i][j] == DIRT){
         display.setColor(i,j,brown);
         }else if(grid[i][j] == WATER){
         display.setColor(i,j,Color.blue);
         }else if(grid[i][j]==RUSTEDMETAL){
         display.setColor(i,j, rust);
         }else if(grid[i][j]==SAND){
         display.setColor(i,j,Color.yellow);
         }else if(grid[i][j]==LAVA){
         display.setColor(i,j,lava);
         }else if(grid[i][j]==STONE){
         display.setColor(i,j,stone);
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
    if(grid[randomOne][randomTwo] == SAND){
      double waterRandom = Math.random();
      if(waterRandom < 0.33){
         if(grid[randomOne+1][randomTwo]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo]=SAND;
      }else if(grid[randomOne+1][randomTwo]==WATER){
         grid[randomOne][randomTwo] = WATER;
         grid[randomOne+1][randomTwo] = SAND;
      }
         
      }else if(waterRandom < 0.66 && randomTwo+1 < grid[0].length){
         if(grid[randomOne+1][randomTwo+1]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo+1]=SAND;
      }else if(grid[randomOne+1][randomTwo+1]==WATER){
         grid[randomOne][randomTwo] = WATER;
         grid[randomOne+1][randomTwo+1] = SAND;
      }

      }else if((randomTwo-1 >= 0)){
         if(grid[randomOne+1][randomTwo-1]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo-1]=SAND;
      }else if(grid[randomOne+1][randomTwo-1]==WATER){
         grid[randomOne][randomTwo] = WATER;
         grid[randomOne+1][randomTwo-1] = SAND;
      }

      }

    }
    if((grid[randomOne][randomTwo] == 1) && (checkNeighbors(randomOne,randomTwo, WATER))){
      rustCounter+=1;
      if(rustCounter > 4000){
         grid[randomOne][randomTwo] = RUSTEDMETAL;
         rustCounter = 0;
      }
    }
    if(grid[randomOne][randomTwo] == RUSTEDMETAL && (checkNeighbors(randomOne,randomTwo, WATER)))
        {
      rustDestroyer +=1;
      if(rustDestroyer > 4000){
         grid[randomOne][randomTwo]=0;
         rustDestroyer = 0;
      }
   // System.out.println(random);
    }//System.out.println(grid[random][random]);
    if(grid[randomOne][randomTwo] == DIRT)
    {
      if(grid[randomOne+1][randomTwo]== 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo]=DIRT;
      }else if(grid[randomOne+1][randomTwo]==WATER){
         grid[randomOne][randomTwo] = WATER;
         grid[randomOne+1][randomTwo] = DIRT;
      }
    
    }if(grid[randomOne][randomTwo] == LAVA){
    	if (checkNeighbors(randomOne,randomTwo, WATER)){
    		grid[randomOne][randomTwo] = STONE;
    	}
    	lavacounter++;
        double lavaRandom = Math.random();
        if (lavacounter > 500){
        	lavacounter = 0;
            if(lavaRandom < 0.80 && grid[randomOne+1][randomTwo] == 0){
                grid[randomOne][randomTwo]=0;
                grid[randomOne+1][randomTwo]=LAVA;
                
             }else if(lavaRandom < 0.90 && randomTwo+1 < grid[0].length && grid[randomOne][randomTwo+1] == 0){
                grid[randomOne][randomTwo]=0;
                grid[randomOne][randomTwo+1]=LAVA;
             }else if((randomTwo-1 >= 0) && (grid[randomOne][randomTwo-1] == 0)){
                grid[randomOne][randomTwo]=0;
                grid[randomOne][randomTwo-1]=LAVA;
             }
        }
      }
    else if(grid[randomOne][randomTwo] == WATER){
      double waterRandom = Math.random();
      if(waterRandom < 0.33 && grid[randomOne+1][randomTwo] == 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne+1][randomTwo]=WATER;
         
      }else if(waterRandom < 0.66 && randomTwo+1 < grid[0].length && grid[randomOne][randomTwo+1] == 0){
         grid[randomOne][randomTwo]=0;
         grid[randomOne][randomTwo+1]=WATER;
      }else if((randomTwo-1 >= 0) && (grid[randomOne][randomTwo-1] == 0)){
         grid[randomOne][randomTwo]=0;
         grid[randomOne][randomTwo-1]=WATER;
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
