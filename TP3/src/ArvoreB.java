class Registro<V>{
    private V obj;

    public Registro(V obj) {
        this.obj = obj;
    }

    public V getObjeto() {
        return obj;
    }
}


class Endereco {
    private short p;
    private byte b ; //b ∈ [0, itensPorPagina - 1]
    //Métodos para operar com um endereço
}

class Item<K extends Comparable<K>, V> {
    private Registro<V> reg;
    private String key;
    private Endereco esq, dir;

    public Item(Registro<V> reg, String key) {
        this.reg = reg;
        this.key = key;
    }

    public int compara(String chave) {
        return this.key.compareTo(chave);
    }

    public Registro<V> getRegistro() {
        return reg;
    }

    public String getKey() {
        return key;
    }
}



public class ArvoreB<K extends Comparable<K>, V> {

    private class Pagina<K extends Comparable<K>> {
        int n;//de itens
        Item<K, V>[] r;
        Pagina<K>[] p;//ponteiros para os filhos da pag atual

        @SuppressWarnings("unchecked")
        public Pagina(int mm) {
            this.n = 0;
            this.r = (Item<K, V>[]) new Item[mm];
            this.p = (Pagina<K>[]) new Pagina[mm + 1];
        }
    }

    private Pagina<K> raiz;
    private int m, mm;
    public ArvoreB(int m) {
        this.raiz = null;
        this.m = m;//ordem
        this.mm = 2 * m;//grau
    }

    public Item<K, V> pesquisa(String reg) {
        return this.pesquisa(reg, this.raiz);
    }

    private Item<K, V> pesquisa(String chave, Pagina<K> ap) {
        if (ap == null) {
            return null; // Registro não encontrado
        } else {
            int i = 0;

            //chave na página atual
            while ((i < ap.n - 1) && (ap.r[i].compara(chave) < 0)) {
                i++;
            }

            //encontrada
            if (ap.r[i].compara(chave) == 0) {
                return ap.r[i];
            }
            //subárvore à esquerda
            else if (ap.r[i].compara(chave) > 0) {
                return pesquisa(chave, ap.p[i]);
            }
            //subárvore à direita
            else {
                return pesquisa(chave, ap.p[i + 1]);
            }
        }
    }


    public void insere(Item<K, V> reg) {
        Item<K, V>[] regRetorno = (Item<K, V>[]) new Item[1];
        boolean[] cresceu = new boolean[1];
        Pagina<K> apRetorno = this.insere(reg, this.raiz, regRetorno, cresceu);

        if (cresceu[0]) {
            Pagina<K> apTemp = new Pagina<>(this.mm);
            apTemp.r[0] = regRetorno[0];
            apTemp.p[0] = this.raiz;
            apTemp.p[1] = apRetorno;
            this.raiz = apTemp;
            this.raiz.n++;
        } else {
            this.raiz = apRetorno;
        }
    }

    private Pagina<K> insere(Item<K, V> reg, Pagina<K> ap, Item<K, V>[] regRetorno, boolean[] cresceu) {
        Pagina<K> apRetorno = null;

        if (ap == null) {
            cresceu[0] = true;
            regRetorno[0] = reg;
        } else {
            int i = 0;
            while ((i < ap.n - 1) && (reg.compara(ap.r[i].getKey()) > 0)) {
                i++;
            }

            if (reg.compara(ap.r[i].getKey()) == 0) {
                System.out.println("Erro: Registro já existente");
                cresceu[0] = false;
            } else {
                if (reg.compara(ap.r[i].getKey()) > 0) i++;

                apRetorno = insere(reg, ap.p[i], regRetorno, cresceu);

                if (cresceu[0]) {
                    if (ap.n < this.mm) { // Página tem espaço
                        this.insereNaPagina(ap, regRetorno[0], apRetorno);
                        cresceu[0] = false;
                        apRetorno = ap;
                    } else { // Overflow: Página tem que ser dividida
                        Pagina<K> apTemp = new Pagina<>(this.mm);
                        apTemp.p[0] = null;

                        if (i <= this.m) {
                            this.insereNaPagina(apTemp, ap.r[this.mm - 1], ap.p[this.mm]);
                            ap.n--;
                            this.insereNaPagina(ap, regRetorno[0], apRetorno);
                        } else {
                            this.insereNaPagina(apTemp, regRetorno[0], apRetorno);
                        }

                        for (int j = this.m + 1; j < this.mm; j++) {
                            this.insereNaPagina(apTemp, ap.r[j], ap.p[j + 1]);
                            ap.p[j + 1] = null;
                        }

                        ap.n = this.m;
                        apTemp.p[0] = ap.p[this.m + 1];
                        regRetorno[0] = ap.r[this.m];
                        apRetorno = apTemp;
                    }
                }
            }
        }

        return (cresceu[0] ? apRetorno : ap);
    }

    private void insereNaPagina(Pagina<K> ap, Item<K, V> reg, Pagina<K> apDir) {
        int k = ap.n - 1;
        while ((k >= 0) && (reg.compara(ap.r[k].getKey()) < 0)) {
            ap.r[k + 1] = ap.r[k];
            ap.p[k + 2] = ap.p[k + 1];
            k--;
        }
        ap.r[k + 1] = reg;
        ap.p[k + 2] = apDir;
        ap.n++;
    }


    public void retira(String chave) {
        boolean diminuiu[] = new boolean[1];
        this.raiz = this.retira(chave, this.raiz, diminuiu);

        if (diminuiu[0] && (this.raiz.n == 0)) { // Árvore diminui na altura
            this.raiz = this.raiz.p[0];
        }
    }

    private Pagina retira(String chave, Pagina ap, boolean[] diminuiu) {
        if (ap == null) {
            System.out.println("Erro: Registro não encontrado");
            diminuiu[0] = false;
        } else {
            int ind = 0;
            // Busca pelo índice do item com a chave correspondente
            while ((ind < ap.n - 1) && (chave.compareTo(ap.r[ind].getKey()) > 0)) ind++;

            if (chave.compareTo(ap.r[ind].getKey()) == 0) { // achou
                if (ap.p[ind] == null) { // Página folha
                    ap.n--;
                    diminuiu[0] = ap.n < this.m;
                    for (int j = ind; j < ap.n; j++) {
                        ap.r[j] = ap.r[j + 1];
                        ap.p[j] = ap.p[j + 1];
                    }
                    ap.p[ap.n] = ap.p[ap.n + 1];
                    ap.p[ap.n + 1] = null; // transfere a posse da memória
                } else { // Página não é folha: trocar com antecessor
                    diminuiu[0] = antecessor(ap, ind, ap.p[ind]);
                    if (diminuiu[0]) diminuiu[0] = reconstitui(ap.p[ind], ap, ind);
                }
            } else { // não achou
                if (chave.compareTo(ap.r[ind].getKey()) > 0) ind++;
                ap.p[ind] = retira(chave, ap.p[ind], diminuiu);
                if (diminuiu[0]) diminuiu[0] = reconstitui(ap.p[ind], ap, ind);
            }
        }
        return ap;
    }



    private boolean antecessor(Pagina ap, int ind, Pagina apPai) {
        boolean diminuiu = true;
        if (apPai.p[apPai.n] != null) {
            diminuiu = antecessor(ap, ind, apPai.p[apPai.n]);
            if (diminuiu) diminuiu = reconstitui(apPai.p[apPai.n], apPai, apPai.n);
        } else {
            ap.r[ind] = apPai.r[--apPai.n];
            diminuiu = apPai.n < this.m;
        }
        return diminuiu;
    }


    private boolean reconstitui(Pagina apPag, Pagina apPai, int posPai) {
        boolean diminuiu = true;

        if (posPai < apPai.n) { // aux = Página à direita de apPag
            Pagina aux = apPai.p[posPai + 1];
            int dispAux = (aux.n - this.m + 1) / 2;
            apPag.r[apPag.n++] = apPai.r[posPai];
            apPag.p[apPag.n] = aux.p[0];
            aux.p[0] = null; // transfere a posse da memória

            if (dispAux > 0) { // Existe folga: transfere de aux para apPag
                for (int j = 0; j < dispAux - 1; j++) {
                    this.insereNaPagina(apPag, aux.r[j], aux.p[j + 1]);
                    aux.p[j + 1] = null; // transfere a posse da memória
                }

                apPai.r[posPai] = aux.r[dispAux - 1];
                aux.n = aux.n - dispAux;
                for (int j = 0; j < aux.n; j++) aux.r[j] = aux.r[j + dispAux];
                for (int j = 0; j <= aux.n; j++) aux.p[j] = aux.p[j + dispAux];
                aux.p[aux.n + dispAux] = null; // transfere a posse da memória
                diminuiu = false;
            } else { // Fusão: intercala aux em apPag e libera aux
                for (int j = 0; j < this.m; j++) {
                    this.insereNaPagina(apPag, aux.r[j], aux.p[j + 1]);
                    aux.p[j + 1] = null; // transfere a posse da memória
                }
                aux = apPai.p[posPai + 1] = null; // libera aux
                for (int j = posPai; j < apPai.n - 1; j++) {
                    apPai.r[j] = apPai.r[j + 1];
                    apPai.p[j + 1] = apPai.p[j + 2];
                }
                apPai.p[apPai.n--] = null; // transfere a posse da memória
                diminuiu = apPai.n < this.m;
            }

        } else { // aux = Página à esquerda de apPag
            Pagina aux = apPai.p[posPai - 1];
            int dispAux = (aux.n - this.m + 1) / 2;
            for (int j = apPag.n - 1; j >= 0; j--) apPag.r[j + 1] = apPag.r[j];
            apPag.r[0] = apPai.r[posPai - 1];
            for (int j = apPag.n; j >= 0; j--) apPag.p[j + 1] = apPag.p[j];
            apPag.n++;

            if (dispAux > 0) { // Existe folga: transfere de aux para apPag
                for (int j = 0; j < dispAux - 1; j++) {
                    this.insereNaPagina(apPag, aux.r[aux.n - j - 1], aux.p[aux.n - j]);
                    aux.p[aux.n - j] = null; // transfere a posse da memória
                }
                apPag.p[0] = aux.p[aux.n - dispAux + 1];
                aux.p[aux.n - dispAux + 1] = null; // transfere a posse da memória
                apPai.r[posPai - 1] = aux.r[aux.n - dispAux];
                aux.n = aux.n - dispAux;
                diminuiu = false;
            } else { // Fusão: intercala apPag em aux e libera apPag
                for (int j = 0; j < this.m; j++) {
                    this.insereNaPagina(aux, apPag.r[j], apPag.p[j + 1]);
                    apPag.p[j + 1] = null; // transfere a posse da memória
                }
                apPag = null; // libera apPag
                apPai.p[apPai.n--] = null; // transfere a posse da memória
                diminuiu = apPai.n < this.m;
            }
        }
        return diminuiu;
    }


    public void printTree() {
        if (raiz != null) {
            printPagina(raiz, 0);
        } else {
            System.out.println("A árvore está vazia.");
        }
    }

    private void printPagina(Pagina<K> pagina, int nivel) {
        if (pagina == null) return;

        //indenta o nível atual de forma mais clara
        String indentacao = "   ".repeat(nivel);
        System.out.print(indentacao);

        System.out.print("├── ");

        for (int i = 0; i < pagina.n; i++) {
            System.out.print(pagina.r[i].getKey() + "       ");
        }
        System.out.println();

        //imprime as páginas filhas
        for (int i = 0; i <= pagina.n; i++) {
            if (pagina.p[i] != null) {
                printPagina(pagina.p[i], nivel + 1);
            }
        }
    }


}