/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

import java.util.ArrayList;

/**
 *
 * @author Albertyson
 */
public class main {

    public static void main(String[] args) {
        System.out.println("Análisis para 10 datos");
        compareSorts(10);
        System.out.println("Análisis para 100 datos");
        compareSorts(100);
        System.out.println("Análisis para 1,000 datos");
        compareSorts(1000);
        System.out.println("Análisis para 10,000 datos");
        compareSorts(10000);
        System.out.println("Análisis para 100,000 datos");
        compareSorts(100000);
        System.out.println("Análisis para 1,000,000 datos");
        compareSorts(1000000);
    }
    
    public static void compareSorts(int arrayLength){
        ArrayList<Long> bubleList = new ArrayList();
        ArrayList<Long> insertList = new ArrayList();
        ArrayList<Long> selectionList = new ArrayList();
        ArrayList<Long> mergeList = new ArrayList();
        ArrayList<Long> heapList = new ArrayList();
        ArrayList<Long> quickList = new ArrayList();
        ArrayList<Long> radixList = new ArrayList();
        for (int i = 0; i < 10; i++) {            
            int[] arr = getRandomArray(arrayLength);
            long timeBeforeBubble = System.nanoTime();
            sort(0, arr);
            long timeAfterBubble = System.nanoTime();
            long timeBubble = timeAfterBubble - timeBeforeBubble;
            arr = getRandomArray(arrayLength);
            long timeBeforeInsert = System.nanoTime();
            sort(1, arr);
            long timeAfterInsert = System.nanoTime();
            long timeInsert = timeAfterInsert - timeBeforeInsert;
            arr = getRandomArray(arrayLength);
            long timeBeforeSelection = System.nanoTime();
            sort(2, arr);
            long timeAfterSelection = System.nanoTime();
            long timeSelection = timeAfterSelection - timeBeforeSelection;
            arr = getRandomArray(arrayLength);
            long timeBeforeMerge = System.nanoTime();
            sort(3, arr);
            long timeAfterMerge = System.nanoTime();
            long timeMerge = timeAfterMerge - timeBeforeMerge;
            arr = getRandomArray(arrayLength);
            long timeBeforeHeap = System.nanoTime();
            sort(4, arr);
            long timeAfterHeap = System.nanoTime();
            long timeHeap = timeAfterHeap - timeBeforeHeap;
            arr = getRandomArray(arrayLength);
            long timeBeforeQuick = System.nanoTime();
            sort(5, arr);
            long timeAfterQuick = System.nanoTime();
            long timeQuick = timeAfterQuick - timeBeforeQuick;
            arr = getRandomArray(arrayLength);
            long timeBeforeRadix = System.nanoTime();
            sort(6, arr);
            long timeAfterRadix = System.nanoTime();
            long timeRadix = timeAfterRadix - timeBeforeRadix;
            bubleList.add(timeBubble);
            insertList.add(timeInsert);
            selectionList.add(timeSelection);
            mergeList.add(timeMerge);
            heapList.add(timeHeap);
            quickList.add(timeQuick);
            radixList.add(timeRadix);
        }
        long acumBubble = 0;
        long acumInsert = 0;
        long acumSelection = 0;
        long acumMerge = 0;
        long acumHeap = 0;
        long acumQuick = 0;
        long acumRadix = 0;
        for (int i = 0; i < 10; i++) {
            acumBubble += bubleList.get(i);
            acumInsert += insertList.get(i);
            acumSelection += selectionList.get(i);
            acumMerge += mergeList.get(i);
            acumHeap += heapList.get(i);
            acumQuick += quickList.get(i);
            acumRadix += radixList.get(i);
        }
        double avgBubble = acumBubble/10;
        double avgInsert = acumInsert/10;
        double avgSelection = acumSelection/10;
        double avgMerge = acumMerge/10;
        double avgHeap = acumHeap/10;
        double avgQuick = acumQuick/10;
        double avgRadix = acumRadix/10;
        printTime(avgBubble,"Bubble Sort");
        printTime(avgInsert,"Insert Sort");
        printTime(avgSelection,"Selection Sort");
        printTime(avgMerge,"Merge Sort");
        printTime(avgHeap,"Heap Sort");
        printTime(avgQuick,"Quick Sort");
        printTime(avgRadix,"Radix Sort");
    }
    public static int[] getRandomArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int rand = (int) Math.round(Math.random() * 50);
            arr[i] = rand;
        }
        return arr;
    }

    public static void sort(int opt, int[] arr) {
//        printArray(arr);
        switch (opt) {
            case 0://bubble                
                arr = bubbleSort(arr);
                break;
            case 1://insert
                arr = insertSort(arr);
                break;
            case 2://selection
                arr = selectionSort(arr);
                break;
            case 3://merge
                arr = mergeSort(arr);
                break;
            case 4://heap
                arr = HeapSort.sort(arr);
                break;
            case 5://quick
                arr = quickSort(arr, 0, arr.length - 1);
                break;
            case 6://radix
                arr = Radix.radixsort(arr,arr.length);
                break;
        }
//        printArray(arr);
    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int index = arr[i];
            while (j > 0 && arr[j - 1] > index) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = index;
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int selection = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[selection]) {
                    selection = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[selection];
            arr[selection] = temp;
        }
        return arr;
    }

    public static int[] mergeSort(int[] A) {
        if (A.length == 1) {
            return A;
        }
        int q = A.length / 2;
        int[] leftArray = new int[q];
        System.arraycopy(A, 0, leftArray, 0, q);
        int[] rightArray = new int[q];
        System.arraycopy(A, q, rightArray, 0, q);
        mergeSort(leftArray);
        mergeSort(rightArray);
        return merge(A, leftArray, rightArray);
    }

    public static int[] merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i, li, ri;
        i = li = ri = 0;
        while (i < totElem) {
            if ((li < l.length) && (ri < r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                } else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            } else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        return a;
    }

    public static int[] quickSort(int A[], int izq, int der) {

        int pivote = A[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        int aux;

        while (i < j) {            // mientras no se crucen las búsquedas
            while (A[i] <= pivote && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (A[j] > pivote) {
                j--;         // busca elemento menor que pivote
            }
            if (i < j) {                      // si no se han cruzado                      
                aux = A[i];                  // los intercambia
                A[i] = A[j];
                A[j] = aux;
            }
        }
        A[izq] = A[j]; // se coloca el pivote en su lugar de forma que tendremos
        A[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            A = quickSort(A, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            A = quickSort(A, j + 1, der); // ordenamos subarray derecho
        }
        return A;
    }

    public static int[] radix(int[] arr) {
        int i;
        int m = arr[0];
        int exp = 1;
        int  n = arr.length;
        int[] b = new int[10];
//        for (i = 1; i < n; i++) {
//            if (arr[i] > m) {
//                m = arr[i];
//            }
//        }
//        while (m / exp > 0) {
//            int[] elements10 = new int[10];
//
//            for (i = 0; i < n; i++) {
//                elements10[(arr[i] / exp) % 10]++;
//            }
//            for (i = 1; i < 10; i++) {
//                elements10[i] += elements10[i - 1];
//            }
//            for (i = n - 1; i >= 0; i--) {
//                b[--elements10[(arr[i] / exp) % 10]] = arr[i];
//            }
//            for (i = 0; i < n; i++) {
//                arr[i] = b[i];
//            }
//            exp *= 10;
//        }
        return arr;
    }

    public static void printArray(int[] arr) {
        String s = "[ ";
        for (int i = 0; i < arr.length; i++) {
            s += i != arr.length - 1 ? arr[i] + ", " : arr[i];
        }
        s += " ]";
        System.out.println(s);
    }
    
    public static void printTime(double nanoTime,String sortName){
        double retVal = nanoTime;
        if(nanoTime < 1000){
            System.out.println("Tiempo en nanoSegundos de " + sortName + ":\t"+retVal);
        }else if(nanoTime >= 1000 && nanoTime < 1000000){
            //microseconds
            retVal = nanoTime / 1000;
            System.out.println("Tiempo en microsegundos de " + sortName + ":\t"+retVal);
        }else if(nanoTime >= 1000000 && nanoTime < 1000000000){
            //microseconds
            retVal = nanoTime / 1000000;
            System.out.println("Tiempo en milisegundos de " + sortName + ":\t"+retVal);
        }else if(nanoTime >= 1000000000){
            //seconds
            retVal = nanoTime / 1000000000;
            System.out.println("Tiempo en segundos de " + sortName + ":\t"+retVal);
        }        
    }
}
