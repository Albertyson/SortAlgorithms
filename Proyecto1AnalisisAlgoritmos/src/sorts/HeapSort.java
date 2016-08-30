/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

/**
 *
 * @author Albertyson
 */
import java.util.*;
 
public class HeapSort {
 
   private static int[] a;
   private static int n;
   private static int left;
   private static int right;
   private static int largest;
 
 
   public static void buildheap(int []a) {
      n = a.length-1;
      for(int i=n/2; i>=0; i--){
         maxheap(a,i);
      }
   }
 
   public static void maxheap(int[] a, int i) { 
      left = 2*i;
      right = 2*i+1;
 
      if(left <= n && a[left] > a[i]){
         largest=left;
      } else {
         largest=i;
      }
 
      if(right <= n && a[right] > a[largest]) {
         largest=right;
      }
      if(largest!=i) {
         exchange(i, largest);
         maxheap(a, largest);
      }
   }
 
   public static void exchange(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t; 
   }
 
   public static int[] sort(int[] myarray) {
      a = myarray;
      buildheap(a);
      for(int i=n; i>0; i--) {
         exchange(0, i);
         n=n-1;
         maxheap(a, 0);
      }
      return a;
   }
}