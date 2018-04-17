import java.util.Iterator;
import java.util.*;
import java.lang.*;

public class Deque<Item>  implements Iterable<Item> {
   //public Deque(Item item);
   private Node first = null;
   private Node last = null;
   public class Node{
       Item item;
       Node next;
   }

   public Iterator<Item> iterator() { return new ListIterator(); }

   private class ListIterator implements Iterator<Item>
   {

       private Node current = first;
       public boolean hasNext() {  return current != null;  }        
       public void remove()     {  throw UnsupportedOperationException(); }
       public Item next()        {            
          if (!hasNext()){ throw NoSuchElementException();}
          Item item = current.item;            
          current   = current.next;             
          return item;        
       }       
   }

   //private class ListIterator implements Iterator <Item>{
   //    private Node scanner = head;
   //}

   public boolean isEmpty() { return first == null; }                 // is the deque empty?

   public int size() {  // return the number of items on the deque
        Node current;
        current = first;
        int n = 0; 
        while ( current != null ){
             n += 1; 
             current = current.next;
        }
        return n;
   }
   public void addFirst(Item item) {          // add the item to the front
        if (item == null){
           throw new IllegalArgumentException();
       }

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        Node current = first;
        while (current.next != null) {
            current = current.next;
            last = current;
        } 
   }

   public void addLast(Item item) {            // add the item to the end
        //Node oldlast = new Node();
        if (item == null){
           throw new java.lang.IllegalArgumentException();
        }

        if (isEmpty()){ 
            Node first = new Node();
            first.item = item;
            first.next = null;
            Node last = first;
        }
        else{
            Node oldlast = last;
            System.out.println(oldlast.item);
            last = new Node();
            oldlast.next = last;
            last.item = item;
            last.next = null;
        }
   }

   public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()){
            throw java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if ( isEmpty() ) last = null;
        return item;
   }

   public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()){
            throw java.util.NoSuchElementException();
        }
        Node current = first;
        if ( size() == 1 ) {
            Item item = first.item;
            first = null;
            return item;
        }
        
        while ( current.next != last ) {
            current = current.next;
        }

        last = current;
        last.next = null;
        System.out.println(last.item);
        if ( isEmpty() ) last = null;
        Item item = last.item;
        return item;
   }
/*
   public Iterator<Item> iterator();         // return an iterator over items in order from front to end
*/
   public static void main(String[] args) {   // unit testing
       Deque<Integer> dec = new Deque<Integer>();
       System.out.println("Initial size of list="+dec.size());
       dec.addFirst(1);
       dec.addFirst(2);
       dec.addFirst(3);
       System.out.println("Last="+dec.last.item);
       System.out.println("Initial size of list="+dec.size());
       dec.addLast(4);
       System.out.println("Size of list="+dec.size());
       System.out.println("Elements of list");
       for (Integer i : dec)
           System.out.println(i);
       dec.removeFirst();
       System.out.println("Elements of list after removing first element");
       for (Integer i : dec)
           System.out.println(i);
       System.out.println("Size of list="+dec.size());
       dec.removeLast();
       System.out.println("Elements of list after removing last");
       for (Integer i : dec)
           System.out.println(i);
       System.out.println("Size of list="+dec.size());
       dec.removeLast();
       System.out.println("Size of list="+dec.size());
       dec.removeLast();
   } 
}

