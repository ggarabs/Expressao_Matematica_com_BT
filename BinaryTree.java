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
        return root.visit();
    }

    public void buildTree(ArrayList<String> tokens) {
        Stack<Node> parents = new Stack<Node>(), children = new Stack<Node>();

        if (tokens.isEmpty()) {
            System.out.println("Árvore binária vazia!");
            return;
        }

        Map<String, Integer> priority = new HashMap<String, Integer>();
        priority.put("(", 0);
        priority.put(")", 0);
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);

        Node parent = null, right = null, left = null;

        for (String token : tokens) {
            Node aux = new OperatorNode(token);
            if (token.equals("(")) {
                parents.add(aux);
            } else if (token.equals(")")) {
                while (!parents.peek().getData().equals("(")) {
                    right = children.pop();
                    left = children.pop();
                    parent = parents.pop();
                    parent.setLeft(left);
                    parent.setRight(right);
                    left.setParent(parent);
                    right.setParent(parent);
                    children.add(parent);

                    if (parents.isEmpty()) {
                        children.pop();
                        this.setRoot(parent);
                        System.out.println("entrei");
                    }
                }
                parents.pop();
            } else if (Utils.isNumeric(token)) {
                aux = new OperandNode(token);
                children.add(aux);
            } else {
                while (!parents.isEmpty() && priority.get(token) <= priority.get(parents.peek().getData())) {
                    right = children.pop();
                    left = children.pop();
                    parent = parents.pop();
                    parent.setLeft(left);
                    parent.setRight(right);
                    left.setParent(parent);
                    right.setParent(parent);
                    children.add(parent);
                }

                parents.add(aux);
            }
        }

        if(this.getRoot() == null && parents.isEmpty()){
            if(children.isEmpty()) this.setRoot(parent); 
            else if(parents.isEmpty()) this.setRoot(children.pop());
        }

        while (!parents.isEmpty()) {
            parent = parents.pop();
            right = children.pop();
            left = children.pop();

            parent.setLeft(left);
            parent.setRight(right);
            left.setParent(parent);
            right.setParent(parent);
            children.add(parent);

            if (parents.isEmpty()) {
                children.pop();
                this.setRoot(parent);
            }
        }

    }

    private void clear(Node root){      // limpa conexões e nodes
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