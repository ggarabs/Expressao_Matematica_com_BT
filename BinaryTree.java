import java.util.*;

public class BinaryTree {
    private Node root;

    BinaryTree(Node root) {
        this.setRoot(root);
    }

    BinaryTree() {
        this(null);
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node newRoot) {
        this.root = newRoot;
    }

    public boolean isEmpty() {
        return this.getRoot() == null;
    }

    public int getDegree() {
        return this.getDegree(this.root);
    }

    private int getDegree(Node root) {
        if (root == null)
            return -1;
        else if (root.getDegree() == 0)
            return 0;
        return Math.max(root.getDegree(), Math.max(this.getDegree(root.getLeft()), this.getDegree(root.getRight())));
    }

    public int getHeight() {
        return this.getHeight(this.root);
    }

    private int getHeight(Node root) {
        if (root == null)
            return -1;
        return 1 + Math.max(this.getHeight(root.getLeft()), this.getHeight(root.getRight()));
    }

    public void inOrderTraversal() {
        if (this.isEmpty()) {
            System.out.println("Árvore vazia!");
            return;
        }
        System.out.printf("Percurso em ordem: ");
        this.inOrderTraversal(this.root);
        System.out.println();
    }

    private void inOrderTraversal(Node root) {
        if (root == null)
            return;
        this.inOrderTraversal(root.getLeft());
        System.out.printf("%s ", root.getData());
        this.inOrderTraversal(root.getRight());
    }

    public void preOrderTraversal() {
        if (this.root == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        System.out.printf("Percurso em pré-ordem: ");
        this.preOrderTraversal(this.root);
        System.out.println();
    }

    private void preOrderTraversal(Node root) {
        if (root == null)
            return;
        System.out.printf("%s ", root.getData());
        this.preOrderTraversal(root.getLeft());
        this.preOrderTraversal(root.getRight());
    }

    public void postOrderTraversal() {
        if (this.root == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        System.out.printf("Percurso em pós-ordem: ");
        this.postOrderTraversal(this.root);
        System.out.println();
    }

    private void postOrderTraversal(Node root) {
        if (root == null)
            return;
        this.postOrderTraversal(root.getLeft());
        this.postOrderTraversal(root.getRight());
        System.out.printf("%s ", root.getData());
    }

    public void levelOrderTraversal() {
        if (this.root == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        System.out.printf("Percurso em-nível: ");
        this.levelOrderTraversal(this.root);
        System.out.println();
    }

    private void levelOrderTraversal(Node root) {
        Queue<Node> auxQueue = new LinkedList<Node>();

        auxQueue.add(root);

        while (auxQueue.peek() != null) {
            Node front = auxQueue.peek();
            if (front.getLeft() != null)
                auxQueue.offer(front.getLeft());
            if (front.getRight() != null)
                auxQueue.offer(front.getRight());
            System.out.printf("%s ", front.getData());
            auxQueue.poll();
        }
    }

    public void printNodeInformation() {
        if (!this.isEmpty()) {
            System.out.println("\nINFORMAÇÕES DOS NÓS: ");
            this.printNodeInformation(this.root);
        }

        System.out.println("\nINFORMAÇÕES DA ÁRVORE BINÁRIA: ");
        System.out.printf("A BT está vazia?: %b\n", this.isEmpty());
        System.out.printf("Grau da BT: %d\n", this.getDegree());
        System.out.printf("Altura da BT: %d\n", this.getHeight());

        if (!this.isEmpty()) {
            this.inOrderTraversal();
            this.preOrderTraversal();
            this.postOrderTraversal();
            this.levelOrderTraversal();
        }

        System.out.println();
    }

    private void printNodeInformation(Node root) { // Imprimir informações na travessia em ordem
        if (root == null)
            return;
        root.printInformations();
        this.printNodeInformation(root.getLeft());
        this.printNodeInformation(root.getRight());
    }

    public float calculate() {
        if (this.root == null) {
            System.out.println("Árvore vazia! Não há o que calcular.\n");
            return Float.NaN;
        }
        return root.visit();    // Para calcular o resultado da expressão, visito a raiz
    }

    public void buildTree(ArrayList<String> tokens) {
        Stack<Node> parents = new Stack<Node>(), children = new Stack<Node>();

        if (tokens.isEmpty()) {
            System.out.println("Árvore binária vazia!");
            return;
        }

        // HashMap utilizado para associar os operadores a suas prioridades

        Map<String, Integer> priority = new HashMap<String, Integer>();
        priority.put("(", 0);
        priority.put(")", 0);
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("!", 1);   // Auxiliar para diferenciar operador unário '-'
        priority.put("@", 1);   // Auxiliar para diferenciar operador unário '+'
        priority.put("*", 2);
        priority.put("/", 2);

        Node parent = null, right = null, left = null;

        for (int i = 0; i < tokens.size(); i++) {   // percorre os tokens armazenados
            Node aux = new OperatorNode(tokens.get(i));
            if (tokens.get(i).equals("(")) {       // se o token for '(', empilhe-o em parents
                parents.add(aux);
            } else if (tokens.get(i).equals(")")) { // se for ')', desempilhe até encontrar o '('
                while (!parents.peek().getData().equals("(")) {

                    parent = parents.pop();     // recebe o operador atual
                    right = children.pop();     // recebe o operando da direita

                    // se ele satisfazer essas condições, é operador binário, senão é unário
                    if(children.size() > 0 && !parent.getData().equals("!") && !parent.getData().equals("@")) left = children.pop();
                    else if(parent.getData().equals("!")){
                        // sendo unário, mudamos o simbolo antes de armazenar na árvore e o filho da esquerda é nulo
                        parent.setData("-");
                        left = null;
                    }else if(parent.getData().equals("@")){
                        parent.setData("+");
                        left = null;
                    }

                    parent.setLeft(left);
                    parent.setRight(right);
                    if(left != null) left.setParent(parent);    // só conecto o filho da esquerda ao pai se não for unário
                    right.setParent(parent);

                    children.add(parent);   // após fazer as conexões nos nós, eu armazeno o pai na pilha dos filhos

                    if (parents.isEmpty()) {    // se a pilha esvaziou, então define-se o ultimo pai como raiz
                        children.pop();
                        this.setRoot(parent);
                    }
                }
                parents.pop();  // apaga o '(' da pilha
            } else if (Utils.isNumeric(tokens.get(i))) {
                aux = new OperandNode(tokens.get(i));
                children.add(aux);  // se é um operando, empilho-o em children
            } else {    // se for um operador, 
                switch (tokens.get(i)) {
                    case "+":
                        // operador unário
                        if((i == 0) || (i+1 != tokens.size() && tokens.get(i-1).equals("(") && tokens.get(i+1).equals("("))) aux = new Sum("@");
                        // operador binário
                        else aux = new Sum(tokens.get(i));
                        break;
                    case "-":
                        // operador unário
                        if((i == 0) || (i+1 != tokens.size() && tokens.get(i-1).equals("(") && tokens.get(i+1).equals("("))) aux = new Difference("!");
                        // operador binário                        
                        else aux = new Difference(tokens.get(i));
                        break;
                    case "*":
                        aux = new Product(tokens.get(i));
                        break;
                    case "/":
                        aux = new Quocient(tokens.get(i));
                        break;
                    default:
                        System.out.println("Operador inválido!");
                        break;
                }

                // enquanto o nó atual tiver prioridade menor que os que estão empilhados em parents, removo os topos e monto suas ligações
                while (!parents.isEmpty() && priority.get(tokens.get(i)) <= priority.get(parents.peek().getData())) {
                    parent = parents.pop();
                    right = children.pop();
                    // se não for um operador unário, armazeno o topo do children no nó da esquerda
                    if(children.size() > 0 && !parent.getData().equals("!") && !parent.getData().equals("@")) left = children.pop();
                    else if(parent.getData().equals("!")){
                        // sendo unário, mudo o conteúdo antes de armazenar na árvore e o filho da esquerda é nulo
                        parent.setData("-");
                        left = null;
                    }else if(parent.getData().equals("@")){
                        parent.setData("+");
                        left = null;
                    }

                    parent.setLeft(left);
                    parent.setRight(right);
                    if(left != null) left.setParent(parent);
                    right.setParent(parent);

                    children.add(parent);   // adiciono o ultimo pai à pilha dos filhos
                }
                parents.add(aux);
            }
        }

        if(this.getRoot() == null && parents.isEmpty()){    // se a análise terminou e a árvore ainda não tem raiz
            if(children.isEmpty()) this.setRoot(parent); // armazeno o último pai
            else if(parents.isEmpty()) this.setRoot(children.pop());    // ou o último filho
        }

        // o mesmo que o trecho acima, só que com os nós que restaram na pilha após a análise
        while (!parents.isEmpty()) {
            parent = parents.pop();
            right = children.pop();
            if(children.size() > 0 && !parent.getData().equals("!") && !parent.getData().equals("@")) left = children.pop();
            else if(parent.getData().equals("!")){
                parent.setData("-");
                left = null;
            }else if(parent.getData().equals("@")){
                parent.setData("+");
                left = null;
            }
            parent.setLeft(left);
            parent.setRight(right);
            if(left != null) left.setParent(parent);
            right.setParent(parent);
            children.add(parent);

            if (parents.isEmpty()) {
                children.pop();
                this.setRoot(parent);
            }
        }

    }

    private void clear(Node root){      // limpa arestas e vértices percoreendo a árvore em pós-ordem
        if (root == null) return;
        this.clear(root.getLeft());
        this.clear(root.getRight());
        root.setLeft(null);
        root.setRight(null);
        root.setParent(null);
        root = null;
    }

    public void clear(){
        this.clear(this.getRoot());
        this.setRoot(null);
    }

}