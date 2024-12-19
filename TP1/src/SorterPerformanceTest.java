public class SorterPerformanceTest {

    public static <T extends Comparable<T>> void testarVelocidade(T[] crescente, T[] decrescente, T[] aleatorios, T[] repetidos, Sorter sorter, int repeticoes) {
        System.out.println("\n\nBubble:");
        medirTempoBubble(sorter, "Crescente", crescente, repeticoes);
        medirTempoBubble(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoBubble(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoBubble(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nInsertion:");
        medirTempoInsertion(sorter, "Crescente", crescente, repeticoes);
        medirTempoInsertion(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoInsertion(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoInsertion(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nSelection:");
        medirTempoSelection(sorter, "Crescente", crescente, repeticoes);
        medirTempoSelection(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoSelection(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoSelection(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nShell:");
        medirTempoShell(sorter, "Crescente", crescente, repeticoes);
        medirTempoShell(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoShell(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoShell(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nMerge:");
        medirTempoMerge(sorter, "Crescente", crescente, repeticoes);
        medirTempoMerge(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoMerge(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoMerge(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nQuick:");
        medirTempoQuick(sorter, "Crescente", crescente, repeticoes);
        medirTempoQuick(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoQuick(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoQuick(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nHeap:");
        medirTempoHeap(sorter, "Crescente", crescente, repeticoes);
        medirTempoHeap(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoHeap(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoHeap(sorter, "Repetidos", repetidos, repeticoes);

        System.out.println("\n\nBucket:");
        medirTempoBucket(sorter, "Crescente", crescente, repeticoes);
        medirTempoBucket(sorter, "Decrescente", decrescente, repeticoes);
        medirTempoBucket(sorter, "Aleatórios", aleatorios, repeticoes);
        medirTempoBucket(sorter, "Repetidos", repetidos, repeticoes);

        if (crescente instanceof Integer[]) {
            Integer[] intCrescente = (Integer[]) crescente;
            Integer[] intDecrescente = (Integer[]) decrescente;
            Integer[] intAleatorios = (Integer[]) aleatorios;
            Integer[] intRepetidos = (Integer[]) repetidos;

            System.out.println("\n\nCounting:");
            medirTempoCounting(sorter, "Crescente", intCrescente, repeticoes);
            medirTempoCounting(sorter, "Decrescente", intDecrescente, repeticoes);
            medirTempoCounting(sorter, "Aleatórios", intAleatorios, repeticoes);
            medirTempoCounting(sorter, "Repetidos", intRepetidos, repeticoes);

            System.out.println("\n\nRadix:");
            medirTempoRadix(sorter, "Crescente", intCrescente, repeticoes);
            medirTempoRadix(sorter, "Decrescente", intDecrescente, repeticoes);
            medirTempoRadix(sorter, "Aleatórios", intAleatorios, repeticoes);
            medirTempoRadix(sorter, "Repetidos", intRepetidos, repeticoes);
        } else {
            System.out.println("\nRadix e Counting não suportam não inteiros.");
        }
    }

    private static <T extends Comparable<T>> void medirTempoHeap(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.heapSort(copia, copia.length);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para HeapSort (%s): %.2f ms%n", tipo, media);
    }


    private static <T extends Comparable<T>> void medirTempoBubble(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.bubbleSort(copia, copia.length);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para BubbleSort (%s): %.2f ms%n", tipo, media);
    }

    private static <T extends Comparable<T>> void medirTempoInsertion(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.insertionSort(copia, copia.length);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para InsertionSort (%s): %.2f ms%n", tipo, media);
    }

    private static <T extends Comparable<T>> void medirTempoSelection(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.selectionSort(copia, copia.length);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para SelectionSort (%s): %.2f ms%n", tipo, media);
    }

    private static <T extends Comparable<T>> void medirTempoShell(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.shellSort(copia, copia.length);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para ShellSort (%s): %.2f ms%n", tipo, media);
    }

    private static <T extends Comparable<T>> void medirTempoMerge(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.mergeSort(copia, 0, copia.length - 1);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para MergeSort (%s): %.2f ms%n", tipo, media);
    }

    private static <T extends Comparable<T>> void medirTempoQuick(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.quickSort(copia, 0, copia.length - 1);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para QuickSort (%s): %.2f ms%n", tipo, media);
    }

    private static <T extends Comparable<T>> void medirTempoBucket(Sorter sorter, String tipo, T[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            T[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.bucketSort(copia, 10); // Usando 10 buckets como exemplo
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para BucketSort (%s): %.2f ms%n", tipo, media);
    }

    private static void medirTempoRadix(Sorter sorter, String tipo, Integer[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            Integer[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.radixSort(copia);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para RadixSort (%s): %.2f ms%n", tipo, media);
    }

    private static void medirTempoCounting(Sorter sorter, String tipo, Integer[] vetor, int repeticoes) {
        long somaTempos = 0;
        for (int i = 0; i < repeticoes; i++) {
            Integer[] copia = vetor.clone();
            long tempoInicio = System.nanoTime();
            sorter.countingSort(copia, copia.length);
            long tempoFim = System.nanoTime();
            long duracao = tempoFim - tempoInicio;
            somaTempos += duracao;
            System.out.printf("Execução %d - %s: %.2f ms%n", i + 1, tipo, duracao / 1_000_000.0);
        }
        double media = somaTempos / (double) repeticoes / 1_000_000.0;
        System.out.printf("Tempo médio para CountingSort (%s): %.2f ms%n", tipo, media);
    }
}
