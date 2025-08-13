package br.pucrs;

import java.util.List;
public class App {
    private long iterations = 0;

    public static void main(String[] args) {
        int[] sizes = { 32, 2048, 1048576 };
        for (int size : sizes) {
            List<Integer> arr = new java.util.ArrayList<>();
            java.util.Random rand = new java.util.Random();
            for (int i = 0; i < size; i++) {
                arr.add(rand.nextInt(1000000)); 
            }
            App sorter = new App();
            long start = System.currentTimeMillis();
            List<Integer> sorted = sorter.mergeSort(arr);
            long maxVal = sorter.maxVal2(arr.stream().mapToLong(Integer::longValue).toArray(), 0, arr.size() - 1);
            long end = System.currentTimeMillis();
            System.out.println("Tamanho: " + size);
            System.out.println("Iterações: " + sorter.iterations);
            System.out.println("Tempo (ms): " + (end - start));
            if (size <= 32) {
                System.out.println("Original: " + arr);
                System.out.println("Sorted:   " + sorted);
            }
            System.out.println("--------------------------");

            System.out.println("----------------- Sem Recursão! ------------------");
            System.out.println("Tamanho: " + size);
            System.out.println("Máximo valor: " + maxVal);
            System.out.println("Tempo (ms): " + (end - start));
            if (size <= 32) {
                System.out.println("Original: " + arr);
                System.out.println("Sorted:   " + sorted);
            }
            System.out.println("--------------------------");
        }
    }

    public List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> res = new java.util.ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            iterations++;
            if (a.get(i) <= b.get(j)) {
                res.add(a.get(i));
                i++;
            } else {
                res.add(b.get(j));
                j++;
            }
        }
        while (i < a.size()) {
            iterations++;
            res.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            iterations++;
            res.add(b.get(j));
            j++;
        }
        return res;
    }

    public List<Integer> mergeSort(List<Integer> arr) {
        if (arr.size() == 1) {
            return arr;
        }
        int half = arr.size() / 2;
        List<Integer> a = arr.subList(0, half);
        List<Integer> b = arr.subList(half, arr.size());
        a = mergeSort(a);
        b = mergeSort(b);
        arr = merge(a, b);
        return arr;
    }

   public long maxVal1(long[] A, int n) {
       long max = A[0];
       for (int i = 1; i < n; i++) {
           iterations++;
           if (A[i] > max) {
               max = A[i];
           }
       }
       return max;
   }
   public long maxVal2(long A[], int init, int end) {
    if (end - init <= 1)
        return Math.max(A[init], A[end]);
    else {
          int m = (init + end)/2;
          long v1 = maxVal2(A,init,m);
          long v2 = maxVal2(A,m+1,end);
          return Math.max(v1,v2);
         }
}  
}
