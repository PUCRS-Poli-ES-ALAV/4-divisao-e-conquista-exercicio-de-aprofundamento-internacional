package br.pucrs;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Gerar lista de inteiros aleatórios pequenos
        int size = 10;
        List<Integer> arr = new java.util.ArrayList<>();
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < size; i++) {
            arr.add(rand.nextInt(100)); // Números entre 0 e 99
        }

        System.out.println("Original: " + arr);
        List<Integer> sorted = new App().mergeSort(arr);
        System.out.println("Sorted: " + sorted);
    }

    public List<Integer> merge(List<Integer> a, List<Integer> b){
        List<Integer> res = new java.util.ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) <= b.get(j)) {
                res.add(a.get(i));
                i++;
            } else {
                res.add(b.get(j));
                j++;
            }
        }
        while (i < a.size()) {
            res.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            res.add(b.get(j));
            j++;
        }
        return res;
    }
    public List<Integer> mergeSort(List<Integer> arr){
        if(arr.size()==1){
            return arr;
        }
        int half = arr.size()/2;
        List<Integer> a = arr.subList(0, half);
        List<Integer> b = arr.subList(half, arr.size());

        a = mergeSort(a);
        b = mergeSort(b);
        arr = merge(a, b);
        return arr;
    }
}
