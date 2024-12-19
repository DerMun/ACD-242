import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Engine> engines = Engine.readEnginesFromFile("src/enginet.txt");
        ArvoreB tree = new ArvoreB(2);

        for (Engine engine : engines) {
            Registro<Engine> registro = new Registro<>(engine);
            Item<String, Engine> item = new Item<>(registro, engine.getName());
            tree.insere(item);

            tree.printTree();
            System.out.println("\n");
        }

        System.out.println("deletar 1ZZ\n");
        tree.retira("1ZZ");
        tree.printTree();

        System.out.println("\npesquisar 2JZ");
        Item<String, Engine> item = tree.pesquisa("2JZ");

        if (item != null) {
            Engine resultado = (Engine) item.getRegistro().getObjeto();
            System.out.println(resultado.printInfo());
        } else {
            System.out.println("Engine não encontrado");
        }

    }
}