import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorter {

    public static <T extends Comparable<T>> void bubbleSort(T[] A, int n){
        boolean troca = true;

        for(int i = 0; i < (n-1) && troca; i++){
            troca = false;

            for(int j = n-1; j > i; j--){
                if(A[j].compareTo(A[j - 1]) < 0){
                    troca = true;

                    //swap
                    T temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
                }
            }
        }

    }


    public static <T extends Comparable<T>> void insertionSort(T[] A, int n){
        int i, j;
        T key;//elemento direita (selecionado, key), elemento esquerda (comparado) e chave (guardada pois será sobreescrita)

        for (i = 1; i < n; i++) {//começa com o segundo elemento e itera sempre comparando com o da esquerda
            key = A[i];
            j = i - 1;
            while (j >= 0 && A[j].compareTo(key) > 0) {//elemento comparado (esquerda) é iterado e vão para a direita até que seja encontrado onde a chave é maior
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;//elemento selecionado fica no mesmo index ou vai para o ultimo comparado
        }
    }


    public static <T extends Comparable<T>> void selectionSort(T[] A, int n){
        int i, min, j;

        for(i = 0; i < (n-1); i++){
            min = i;
            for(j = i+1; j < n; j++){
                if( A[j].compareTo(A[min]) < 0 ){
                    min = j;
                }
            }
            //swap
            if(min != i){
                T temp = A[i];
                A[i] = A[min];
                A[min] = temp;
            }
        }

    }

    public static <T extends Comparable<T>> void shellSort(T[] A, int n){
        int h = 1, j;
        T x;

        do {
            h = h * 3 + 1;
        } while (h < n);//valores para h = 1, 4, 13, 40, 121, 364, … (até >= n)

        do {
            h = h / 3;
            for(int i = h; i < n; i++){
                x = A[i];
                j = i;

                while(j >= h && A[j - h].compareTo(x) > 0) {
                    A[j] = A[j - h];
                    j = j - h;
                }
                A[j] = x;
            }
        }while (h != 1);
    }


    public static <T extends Comparable<T>> void bucketSort(T[] A, int n) {
        // criar n buckets/listas
        List<T>[] buckets = new List[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // encontrar o valor máximo e mínimo para normalizar os índices
        T min = getMinValue(A);
        T max = getMaxValue(A);

        // distribuir os elementos
        for (T value : A) {
            int bucketIndex = (int) ((value.compareTo(min)) * (n - 1) / (max.compareTo(min) + 1.0));
            buckets[bucketIndex].add(value);
        }

        // ordenar cada bucket com insertionSort
        int index = 0;
        for (List<T> bucket : buckets) {
            T[] bucketArray = (T[]) bucket.toArray(new Comparable[0]);
            insertionSort(bucketArray, bucketArray.length);
            for (T value : bucketArray) {
                A[index++] = value;
            }
        }
    }


    public static void radixSort(Integer[] A) {
        int max = getMaxValue(A);
        int exp = 1; // é a potência de 10 correspondente ao dígito atual

        while (max / exp > 0) {
            countingSort(A, exp);
            exp *= 10; // próximo dígito
        }
    }

    private static <T extends Comparable<T>> T getMaxValue(T[] A) {
        T max = A[0];
        for (T value : A) {
            if (value.compareTo(max) > 0) {
                max = value;
            }
        }
        return max;
    }

    private static <T extends Comparable<T>> T getMinValue(T[] A) {
        T min = A[0];
        for (T value : A) {
            if (value.compareTo(min) < 0) {
                min = value;
            }
        }
        return min;
    }


    public static void countingSort(Integer[] A, int exp) {
        int N = A.length;

        // Encontra o maior valor do array para normalizar os índices
        int max = getMaxValue(A);

        int[] B = new int[N];
        int[] C = new int[max + 1];

        // sejam B[1 : n] e C[0 : k] novos vetores
        for (int i = 0; i <= max; i++) {
            C[i] = 0;
        }

        for (int j = 0; j < N; j++) {
            int index = (A[j] / exp) % 10; // pegar o dígito correspondente
            C[index] = C[index] + 1;
        }
        // C[i] contém agora o número de elementos iguais a i.

        for (int i = 1; i <= max; i++) {
            C[i] = C[i] + C[i - 1];
        }
        // C[i] contém agora o número de elementos menores ou iguais a i.

        // copia A para B, começando do final de A
        for (int j = (N - 1); j >= 0; j--) {
            int index = (A[j] / exp) % 10; // pegar o dígito correspondente
            B[C[index] - 1] = A[j];
            C[index] = C[index] - 1; // para lidar com valores duplicados
        }

        for (int i = 0; i < N; i++) {
            A[i] = B[i];
        }
    }



    public static <T extends Comparable<T>> void mergeSort(T[] A, int p, int r){
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);      //primeira metade
            mergeSort(A, q + 1, r);  //segunda metade
            merge(A, p, q, r);//mescla
        }
    }

    private static <T extends Comparable<T>> void merge(T[] A, int p, int q, int r) {
        int nL = q - p + 1;
        int nR = r - q;

        T[] L = (T[]) new Comparable[nL];
        T[] R = (T[]) new Comparable[nR];

        for (int i = 0; i < nL; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < nR; j++) {
            R[j] = A[q + 1 + j];
        }

        int i = 0, j = 0;
        int k = p;

        while (i < nL && j < nR) {
            if (L[i].compareTo(R[j]) <= 0) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < nL) {
            A[k] = L[i];
            i++;
            k++;
        }

        while (j < nR) {
            A[k] = R[j];
            j++;
            k++;
        }
    }


    public static <T extends Comparable<T>> void quickSort(T[] A, int p, int r){
        if(p < r){
            int q = partion(A, p, r);
            quickSort(A, p, q - 1); // ordena o lado baixo recursivamente
            quickSort(A, q + 1, r); // ordena o lado alto recursivamente
        }
    }

    private static <T extends Comparable<T>> int partion(T[] A, int p, int r){
        T x = A[r];				// o pivô
        int i = p - 1;				// índice mais alto para o lado baixo
        for(int j = p; j <= (r-1); j++){		// processa cada elemento, menos o pivô
            if(A[j].compareTo(x) <= 0) {                // este elemento pertence ao lado baixo?
                i = i + 1;                // índice de um novo local no lado baixo
                // coloca este elemento lá SWAP
                T aux = A[i];
                A[i] = A[j];
                A[j] = aux;
            }
        }
        //swap
        T aux = A[i+1];
        A[i + 1] = A[r];	// pivô entra logo à direita do lado baixo
        A[r] = aux;
        return i + 1;		// novo índice do pivô
    }



    public static <T extends Comparable<T>> void heapSort(T[] A, int n) {
        build(A, n);

        for (int i = n - 1; i > 0; i--) {
            // Swap
            T aux = A[0];
            A[0] = A[i];
            A[i] = aux;

            n = n - 1;

            maximizes(A, 0, n);
        }
    }

    private static int ESQUERDA(int i) {
        return 2 * i + 1;
    }

    private static int DIREITA(int i) {
        return 2 * i + 2;
    }

    private static <T extends Comparable<T>> void maximizes(T[] A, int i, int tamanho) {
        int maior = i;
        int l = ESQUERDA(i);
        int r = DIREITA(i);

        //comparar com o filho à esquerda
        if (l < tamanho && A[l].compareTo(A[maior]) > 0) {
            maior = l;
        }

        //comparar com o filho à direita
        if (r < tamanho && A[r].compareTo(A[maior]) > 0) {
            maior = r;
        }

        //trocar se o maior não for o pai
        if (maior != i) {
            T aux = A[i];
            A[i] = A[maior];
            A[maior] = aux;

            //ajustar o heap a partir do nó alterado
            maximizes(A, maior, tamanho);
        }
    }

    private static <T extends Comparable<T>> void build(T[] A, int tamanho) {
        for (int i = (tamanho / 2) - 1; i >= 0; i--) { //começa pelos nós internos
            maximizes(A, i, tamanho);
        }
    }



    public static <T> void printArray(T[] A) {
        for (T element : A) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

}