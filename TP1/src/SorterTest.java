public class SorterTest {

    public static <T extends Comparable<T>> void testar(T[] vetor, T[] vetorCopia, String tipoVetor) {

        System.out.println("\n\nvetor original-" + tipoVetor);
        Sorter.printArray(vetor);

        System.out.println("\nBubble Sort:");
        Sorter.bubbleSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;
        Sorter.printArray(vetor);//verifica se resetou

        System.out.println("\nInsertion Sort:");
        Sorter.insertionSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;
        Sorter.printArray(vetor);

        System.out.println("\nSelection Sort:");
        Sorter.selectionSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nShell Sort:");
        Sorter.shellSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nMerge Sort:");
        Sorter.mergeSort(vetor, 0, vetor.length - 1);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nQuick Sort:");
        Sorter.quickSort(vetor, 0, vetor.length - 1);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nHeap Sort:");
        Sorter.heapSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nBubble Sort:");
        Sorter.bubbleSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nInsertion Sort:");
        Sorter.insertionSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nSelection Sort:");
        Sorter.selectionSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nShell Sort:");
        Sorter.shellSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nMerge Sort:");
        Sorter.mergeSort(vetor, 0, vetor.length - 1);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nQuick Sort:");
        Sorter.quickSort(vetor.clone(), 0, vetor.length - 1);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nHeap Sort:");
        Sorter.heapSort(vetor.clone(), vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia;

        System.out.println("\nBucket Sort:");
        Sorter.bucketSort(vetor, vetor.length);
        Sorter.printArray(vetor);
        vetor = vetorCopia.clone();

        if (vetor instanceof Integer[]) { // Radix e Counting Sort só funcionam para Integer
            Integer[] intArray = (Integer[]) vetor;
            Integer[] intArrayCopia = (Integer[]) vetorCopia;

            System.out.println("\nRadix Sort:");
            Sorter.radixSort(intArray); // 10 é o número de dígitos (supondo base decimal)
            Sorter.printArray(intArray);
            vetor = (T[]) intArrayCopia.clone();

            System.out.println("\nCounting Sort:");
            Sorter.countingSort(intArray, intArray.length); // Tamanho do array usado como referência
            Sorter.printArray(intArray);
            vetor = (T[]) intArrayCopia.clone();
        } else {
            System.out.println("\nRadix e Counting não são suportam não inteiros.");
        }
    }

}

