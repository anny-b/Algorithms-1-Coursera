import java.util.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args){
        RandomizedQueue<String> rq = new RandomizedQueue<String>(); 
        
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        int k = Integer.parseInt(args[0]);
        for ( String st : rq )
            System.out.println(st);
        System.out.println(rq.size());
        for (int i=0; i<k; i++){
            System.out.println(rq.dequeue());
        }

   }
}

