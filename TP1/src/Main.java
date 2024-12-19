public class Main {

    public static void main(String[] args) {

        GenerateArrays generate = new GenerateArrays();



        Integer[] crescente = generate.gerarVetorCrescente(10);
        Integer[] decrescente = generate.gerarVetorDecrescente(10);
        Integer[] aleatorio = generate.gerarVetorAleatorio(10, 0, 20);
        Integer[] repetido = generate.gerarVetorRepetido(10, 3);

        SorterTest test = new SorterTest();
        test.testar(crescente, crescente.clone(), "crescente");
        test.testar(decrescente, decrescente.clone(), "decrescente");
        test.testar(aleatorio, aleatorio.clone(), "aleatorio");
        test.testar(repetido, repetido.clone(), "repetido");



        /*int tamanho = 10000;

        Integer[] crescente = generate.gerarVetorCrescente(tamanho);
        Integer[] decrescente = generate.gerarVetorDecrescente(tamanho);
        Integer[] aleatorio = generate.gerarVetorAleatorio(tamanho, 0, 15000);
        Integer[] repetido = generate.gerarVetorRepetido(tamanho, 100);

        Sorter sorter = new Sorter();

        SorterPerformanceTest desempenho = new SorterPerformanceTest();
        desempenho.testarVelocidade(crescente, decrescente, aleatorio, repetido, sorter, 10);*/
    }

}