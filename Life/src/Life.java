import java.awt.Color;
import java.util.Objects;

/*
This code is written by Sanad Alwerfali (Student #: 201857760)
Nature Inspired Computing
2022-09-15
*/
public class Life {	
	
	// variables to be used later in the code 
	private int num;
	private int size;
	private int width = 15;
	private Picture pic;
	static Color w = new Color(255,255,255);
    static Color b = new Color(0,0,0);
	static int current [][];
    
	
	// class constructor 
	public Life(int num, int size) {
		this.num = num;
		this.size = size;
		current = new int [size][size];
		pic = new Picture(size * width, size * width);
	};
	
	
	// Initializing the initial configurations for the Penta-decathlon Oscillator and Simkin glider gun
	static int[][] PDO = {
			{ 0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0,0,0,0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            { 0,0,0,0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0},
            { 0,0,0,0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            { 0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}    
    };
	static int[][] SGG = { 
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
	}};
	
	// fill a current with a random color (Taken from the PictureDemo.java class)
	private void drawCell(int i, int j, Color color)
	{
        for (int offsetX = 0; offsetX < width; offsetX++)
        {
            for (int offsetY = 0; offsetY < width; offsetY++)
            {
                // set() colors an individual pixel
                pic.set((i*width)+offsetX,
                        (j*width)+offsetY, color);
            }
        }
    }
	
	// display (or update) the picture on screen (Taken from the PictureDemo.java class)
    public void show()
    {
        pic.show();
    }
	
    
    
    // Initialize the grid based on the user's input
	void Grid (String c) {
		if (Objects.equals(c, "P")) {
	        for (int y = 0; y < PDO.length; y++) {
	            for (int x = 0; x < PDO[0].length; x++) {
	                current[y][x] = PDO[y][x];
	            }
	        }
	        
		}
			
		if (Objects.equals(c, "S")) {
				
				for (int y = 0; y < SGG.length; y++) {

		            for (int x = 0; x < SGG[0].length; x++) {
		                current[y][x] = SGG[y][x];
		            }
		        }
		}
		
		if (Objects.equals(c, "R")) {
			for (int i = 0; i < current.length; i++) {
				for (int k = 0; k < current.length; k++) {
					int x = (int) (Math.random()*100);
					if (x % 2 == 0)
					{current[i][k] = 0;}
					else
					{current[i][k] = 1;}
				}       
		    }
		}
	}
    
	// function to draw the cells with colors
	void draw (int curr[][]) {
		for (int i = 0; i < curr.length; i++) {
			for (int k = 0; k < curr.length; k++) {
				if (curr[i][k] == 1){
					drawCell(k,i,b);
				}
				else {
					drawCell(k,i,w);
				}
			}
		}
		show();
	}
	
	// function to update the current array buy checking the GOL rules and neighbors 
	void update(int size)
    {
        // a temporary place holder array
		int[][] nxtGen = new int[size][size];
  
        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                
                int aliveCell = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                    	aliveCell += current[(i+y+size)%size][(j+x+size)%size];
                    }
                }
                
                // subtract the current for its neighbor
                aliveCell -= current[y][x];
 
                // ========= Rules of GOL ==========
                
                // if alone, dies
                if ((current[y][x] == 1) && (aliveCell < 2)) {
                    nxtGen[y][x] = 0;
                }
                // if it has more than three neighbors, dies
                else if ((current[y][x] == 1) && (aliveCell > 3)) {
                    nxtGen[y][x] = 0;
                }
                // if has three neighbors, creates 
                else if ((current[y][x] == 0) && (aliveCell == 3)) {
                    nxtGen[y][x] = 1;
                }
                // stay as is
                else
                    nxtGen[y][x] = current[y][x];
                
                // ========= Rules of GOL ==========
          }
        }
        current = nxtGen; 
       }
    
    
	public static void main(String[] args) {
		// taking input from the user
		int num = Integer.parseInt(args[0]);
		int size = Integer.parseInt(args[1]);
		String c = args[2];
		
		// Initializing an object of type Life
		Life life = new Life(num, size);
		
		// starting the grid 
		life.Grid(c);
		
		try {
				for (int e = 0; e < num; e++) {
					life.update(size);
					life.draw(current);
					Thread.sleep(80);
				}
			
		} 
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}
	
}

