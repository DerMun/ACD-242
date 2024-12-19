import java.util.Random;

public class GenerateArrays {

    public static Integer[] gerarVetorCrescente(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = i;
        }
        return vetor;
    }

    public static Integer[] gerarVetorDecrescente(int tamanho) {
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i - 1;
        }
        return vetor;
    }

    public static Integer[] gerarVetorAleatorio(int tamanho, int min, int max) {
        Random random = new Random();
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(max - min + 1) + min;
        }
        return vetor;
    }

    public static Integer[] gerarVetorRepetido(int tamanho, int maxRepetidos) {
        Random random = new Random();
        Integer[] vetor = new Integer[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(maxRepetidos);
        }
        return vetor;
    }

}
