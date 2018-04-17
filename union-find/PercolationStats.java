import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Arrays;

public class PercolationStats {
   private Percolation pc;
   private double[] meanArray;
   private double mean;
   private double sigma;
   
   private static int rowRandom(int i) {
          return StdRandom.uniform(i);
              }

   public PercolationStats(int N, int T){     // perform T independent experiments on an N-by-N grid
       if (N <= 0) throw new IllegalArgumentException("Grid dimension cannot be zero or negative");
       if (T <= 0) throw new IllegalArgumentException("Number of trials cannot be zero or negative");

       int i; 
       int mu;
       meanArray = new double[T];
       for (i = 0; i < T; i++) {
           meanArray[i] = 0.0;
       }
       for (i = 0; i < T; i++){
          Percolation pc = new Percolation(N);
          int k = 0;
          while( !pc.percolates() ) {
             pc.open(rowRandom(N)+1, rowRandom(N)+1);
             k++;       
          }
          meanArray[i] = (double)k/(N*N);
       }
   }

   public double mean() {                      // sample mean of percolation threshold
       int T = meanArray.length - 1;
       double mean = 0.0;
       for (int i = 0; i < T; i++){
           mean = mean + meanArray[i];
       }
       mean =  mean / (double) T;
       return mean;
   }

   public double stddev() {                    // sample standard deviation of percolation threshold
       int T = meanArray.length - 1;
       double bs = 0;
       double sigma = 0.0;
       for (int i = 0; i < T; i++){
           bs = meanArray[i] - mean; 
           sigma = sigma + Math.pow(bs,2);
       }
       sigma = sigma/ (double) (T-1);
       return sigma; 
   }

   public double confidenceLo() {              // low  endpoint of 95% confidence interval
       int T = meanArray.length - 1;
       double conLo = (mean - 1.96 * sigma) / Math.sqrt(T);
       return conLo;
       }

   public double confidenceHi() {              // high endpoint of 95% confidence interval
       int T = meanArray.length - 1;
       double conHi = (mean + 1.96 * sigma) / Math.sqrt(T);
       return conHi;
       }

   public static void main(String[] args) {    // test client (described below)
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        double meanps=ps.mean();     
        double sigmaps = ps.stddev();     
        double conLo = ps.confidenceLo(); 
        double conHi=ps.confidenceHi(); 
   }
}
