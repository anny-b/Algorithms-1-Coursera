import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {  
    
    private boolean[][] pcgrid;
    private int i;
    private int j;
    private int gridN;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;
   
    public Percolation(int gridN) {     // create N-by-N grid, with all sites blocked
       if (gridN <= 0) throw new IllegalArgumentException("Grid dimension cannot be zero or negative");

       pcgrid = new boolean[gridN][gridN];
       for (i = 0; i < gridN; i++)
       {
           for (j = 0; j < gridN; j++)
           {
               pcgrid[i][j] = false;
           }
       }
       gridN = pcgrid.length;
       WeightedQuickUnionUF uf = new WeightedQuickUnionUF(gridN*gridN);
       this.uf = uf;

       virtualTop = 0; // virt top can be any cell from the top row of cells 
       for (int i = 1; i <= gridN; i++) { 
           uf.union(xyto1D(1, i, gridN), virtualTop); 
       } 

       virtualBottom = gridN*gridN - 1; // virt top can be any cell from the top row of cells 
       for (int i = 1; i <= gridN; i++) { 
           uf.union(xyto1D(gridN, i, gridN), virtualBottom); 
       } 
    }
    
    private static void validate_indices(int i, int j, int gridN) {
       if (i < 0 || i > gridN) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j < 0 || j > gridN) throw new IndexOutOfBoundsException("row index j out of bounds");
       return;
    }
   
    public void open(int i, int j) {          // open site (row i, column j) if it is not open already
       int ai = i-1;
       int aj = j-1;
       gridN = pcgrid.length;

       pcgrid[ai][aj] = true; 
       if (ai-1 >= 0 && isOpen(i - 1, j))  //left 
       { 
           uf.union(xyto1D(i, j, gridN),xyto1D(i-1, j, gridN)); 
       } 
       if (ai+1 < gridN && isOpen(i + 1, j))         //right 
       { 
           uf.union(xyto1D(i, j, gridN),xyto1D(i+1, j, gridN)); 
       } 
       if (aj-1 >= 0 && isOpen(i, j-1))     //up 
       { 
           uf.union(xyto1D(i, j, gridN),xyto1D(i, j-1, gridN)); 
       } 
       if (aj+1 < gridN && isOpen(i, j+1))     //down 
       { 
           uf.union(xyto1D(i, j, gridN),xyto1D(i, j+1, gridN)); 
       } 
    }

    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
       gridN = pcgrid.length;
       validate_indices(i-1, j-1, gridN);
       return pcgrid[i-1][j-1];
    }
   
    public boolean isFull(int i, int j) {     // is site (row i, column j) full?
       gridN = pcgrid.length;
       return (isOpen(i,j) && uf.connected(virtualTop, xyto1D(i, j, gridN)));
    }
    
    public boolean percolates() {            // does the system percolate?
       gridN = pcgrid.length;
       if (gridN == 1)
       { 
           if (isOpen(1, 1))
           {
                return true;
           }
           return false;
       }
       if (gridN == 2)
       {
            if (uf.connected(0, 3)) return true;
            if (uf.connected(1, 2)) return true;
            if (uf.connected(0, 2)) return true;
            if (uf.connected(1, 3)) return true;
            return false;
       }
       return uf.connected(virtualTop, virtualBottom); 
    } 
    
    private static int xyto1D(int i, int j, int gridN) {
       int xy = gridN*(i-1) + j-1;
       return xy;
    }
       
    public static void main(String[] args) {  // test client (optional)
       int N1 = 10;
       Percolation pc = new Percolation(N1);
       pc.open(1, 1);
       pc.open(1, 2);
       pc.open(2, 2);
       pc.open(3, 3);
       pc.isFull(3, 3);
    }    
  
}
