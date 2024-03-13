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

    public int getDegree(){
        return this.getDegree(this.root);
    }

    private int getDegree(Node root) {
        if (root == null)
            return -1;
        else if (root.getDegree() == 0)
            return 0;
        return Math.max(root.getDegree(), Math.max(this.getDegree(root.getLeft()), this.getDegree(root.getRight())));
    }

    public int getHeight(){
        return this.getHeight(this.root);
    }

    private int getHeight(Node root) {
        if (root == null)
            return -1;
        return 1 + Math.max(this.getHeight(root.getLeft()), this.getHeight(root.getRight()));
    }

    public void inOrderTraversal(){
        if(this.root == null){
            System.out.println("Árvore vazia!");
            return;
        }
        this.inOrderTraversal(this.root);
    }

    private void inOrderTraversal(Node root) {
        if (root == null) return;
        this.inOrderTraversal(root.getLeft());
        System.out.printf("%s ", root.getData());
        this.inOrderTraversal(root.getRight());
    }

    public void preOrderTraversal(){
        if(this.root == null){
            System.out.println("Árvore vazia!");
            return;
        }
        this.preOrderTraversal(this.root);
    }

    private void preOrderTraversal(Node root) {
        if (root == null)
            return;
        System.out.printf("%s ", root.getData());
        this.preOrderTraversal(root.getLeft());
        this.preOrderTraversal(root.getRight());
    }

    public void postOrderTraversal(){
        if(this.root == null){
            System.out.println("Árvore vazia!");
            return;
        }
        this.postOrderTraversal(this.root);
    }

    private void postOrderTraversal(Node root) {
        if (root == null)
            return;
        this.postOrderTraversal(root.getLeft());
        this.postOrderTraversal(root.getRight());
        System.out.printf("%s ", root.getData());
    }

    public void levelOrderTraversal(){
        if(this.root == null){
            System.out.println("Árvore vazia!");
            return;
        }
        this.levelOrderTraversal(this.root);
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

    public float calculate() {
        if(this.root == null){
            System.out.println("Árvore vazia! Não há o que calcular.");
            return Float.NaN;
        }
        return root.visit();
    }

    public void buildTree(ArrayList<String> tokens) {
        Stack<Node> parents = new Stack<Node>(), children = new Stack<Node>();

        if(tokens.isEmpty()){
            System.out.println("Árvore binária vazia!");
            return;
        }

        Map<String, Integer> priority = new HashMap<String, Integer>();     // Isso deve mudar também
        priority.put("(", 0);
        priority.put(")", 0);
        priority.put("+", 1);
        priority.put("-", 1);
        priority.put("*", 2);
        priority.put("/", 2);

        for (String token : tokens) {
            Node aux = new OperatorNode(token);
            if (token.equals("(")) {
                parents.add(aux);
            } else if (token.equals(")")) {
                while (!parents.peek().getData().equals("(")) {
                    Node parent = parents.pop(), right = children.pop(), left = children.pop();
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
                parents.pop();
            } else if (Utils.isNumeric(token)) {
                aux = new OperandNode(token);
                children.add(aux);
            } else {
                while (!parents.isEmpty() && priority.get(token) <= priority.get(parents.peek().getData())) {
                    Node parent = parents.pop(), right = children.pop(), left = children.pop();
                    parent.setLeft(left);
                    parent.setRight(right);
                    left.setParent(parent);
                    right.setParent(parent);
                    children.add(parent);
                }

                parents.add(aux);
            }
        }

        while (!parents.isEmpty()) {
            Node parent = parents.pop(), right = children.pop(), left = children.pop();

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

}