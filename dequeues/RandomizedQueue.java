import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Node first = null;
   private Node last = null;
   public class Node{
          Item item;
          Node next;
       } 
   public boolean isEmpty(){                 // is the randomized queue empty?
       return first==null;
   }
   public int size(){                        // return the number of items on the randomized queue
       Node current = first;
       if (isEmpty()){return 0;}
       int n = 0;
       while (current != null){
           current=current.next;
           n++;
       }
       return n; 
   }
   
   public void enqueue(Item item){           // add the item
       if (item == null){
           throw new java.lang.IllegalArgumentException();
       }
       Node newfirst = new Node();
       newfirst.item = item;
       newfirst.next = first;
       first = newfirst; 
   }

   public Item dequeue(){                    // remove and return a random item
       if (isEmpty()) {
           throw new NoSuchElementException();
       }
       else 
       {
           int s = size();
           if (s==1){
               Item item = first.item;
               first = null;
               return item;
           }
           else{
               int  n = StdRandom.uniform(s);
               System.out.println("Random integer="+n);
       
       
               Node current = first;
               Node previous = current;
               int i = 0;
               if (n==0){ 
                   Item item = first.item;
                   first = first.next;
                   return item;
               }
       
               while (i < n){
                   previous = current; 
                   current = current.next;
                   i++;
               }

               Item item = previous.next.item;
               previous.next = current.next;
               return item;
          }
      }
   }

   public Item sample(){                     // return a random item (but do not remove it)
       Random rand = new Random();
       int s = size();
       int  n = StdRandom.uniform(s);
       System.out.println("Random integer="+n);
       Node current = first;
       int i = 0;
       while (i < n-1){ current = current.next;i++;}
       return current.item;
   }

   public Iterator<Item> iterator(){return new RQIterator();         // return an independent iterator over items in random order
   }
   private class RQIterator implements Iterator<Item>
   {

       private Node current = first;
       public boolean hasNext() {  return current != null;  }
       public void remove()     {  throw new java.lang.UnsupportedOperationException();  }
       public Item next()        {
          if (current == null){ throw new NoSuchElementException();}
          else{
               Item item = current.item;
               current = current.next;
               return item;
          }
       }
   }

   public static void main(String[] args){   // unit testing (optional)
       RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
       rq.enqueue(1);
       rq.enqueue(2);
       rq.enqueue(3);
       rq.enqueue(4);
       rq.enqueue(5);
       for (Integer i : rq)
          System.out.println(i);
       int sz = rq.size();
       System.out.println("Size of Randomized queue="+sz);
       System.out.println("Removed random sample="+rq.dequeue());
       System.out.println("Size of Randomized queue="+rq.size());
       for (Integer i : rq)
          System.out.println(i);
       System.out.println("Removed random sample="+rq.dequeue());
       System.out.println("Size of Randomized queue="+rq.size());
       for (Integer i : rq)
          System.out.println(i);
       System.out.println("Removed random sample="+rq.dequeue());
       System.out.println("Size of Randomized queue="+rq.size());
       for (Integer i : rq)
          System.out.println(i);
       System.out.println("Removed random sample="+rq.dequeue());
       System.out.println("Size of Randomized queue="+rq.size());
   }
}
