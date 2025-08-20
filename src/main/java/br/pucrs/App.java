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

    /*
     * 4. A Multiplicação Inteira de n-bits recebe dois números inteiros x e y de
     * n-bits e retorna o resutado de x * y.
     * 
     * Assim, novamente:
     * 
     * implemente o algortimo abaixo;
     * teste-o para os 3 casos de valores inteiros: com 4 bits, com 16 bits e com 64
     * bits. Nestes testes, contabilize o número de iterações que o algoritmo
     * executa, e o tempo gasto.
     * 
     * O algoritmo está dado abaixo:
     * 
     * ```java
     * MULTIPLY(x, y, n)
     * IF (n = 1)
     * RETURN x * y.
     * ELSE
     * m ← ⎡ n / 2 ⎤.
     * a ← ⎣ x / 2^m ⎦; b ← x mod 2^m.
     * c ← ⎣ y / 2^m ⎦; d ← y mod 2^m.
     * e ← MULTIPLY(a, c, m).
     * f ← MULTIPLY(b, d, m).
     * g ← MULTIPLY(b, c, m).
     * h ← MULTIPLY(a, d, m).
     * RETURN 2^(2m)*e + 2^m*(g + h) + f.
     * ```
     * 
     * Ajuste a assinatura da sua implementação para receber tipo inteiros long (em
     * java).
     */

    public long multiply(long x, long y, int n) {
        iterations++;
        if (n == 1) {
            return x * y;
        } else {
            int m = (int) Math.ceil(n / 2.0);

            long potenciade2 = (long) Math.pow(2, m);

            long a = x / potenciade2;
            long b = x % potenciade2;

            long c = y / potenciade2;
            long d = y % potenciade2;

            long e = multiply(a, c, m);
            long f = multiply(b, d, m);
            long g = multiply(b, c, m);
            long h = multiply(a, d, m);

            return (long) (Math.pow(2, 2 * m) * e + Math.pow(2, m) * (g + h) + f);
        }
    }

    public long multiplyBinary(String x, String y, int n) {
        iterations++;
        if (n == 1) {
            return (x.charAt(0) - '0') * (y.charAt(0) - '0');
        } else {
            int m = (int) Math.ceil(n / 2.0);

            String a = x.substring(0, x.length() - m);
            String b = x.substring(x.length() - m);

            String c = y.substring(0, y.length() - m);
            String d = y.substring(y.length() - m);

            long e = multiplyBinary(a, c, m);
            long f = multiplyBinary(b, d, m);
            long g = multiplyBinary(b, c, m);
            long h = multiplyBinary(a, d, m);

            return (long) (Math.pow(2, 2 * m) * e + Math.pow(2, m) * (g + h) + f);
        }
    }
}
