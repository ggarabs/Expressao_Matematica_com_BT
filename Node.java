public class Node {
    private String data;
    private Node parent, left, right;

    Node(String data, Node parent, Node left, Node right) {
        this.setData(data);
        this.setLeft(left);
        this.setRight(right);
        this.setParent(parent);
    }

    Node(String data) {
        this(data, null, null, null);
    }

    Node() {
        this("");
    }

    public String getData() {
        return this.data;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setData(String newData) {
        this.data = newData;
    }

    public void setParent(Node newParent) {
        this.parent = newParent;
    }

    public void setLeft(Node newLeft) {
        this.left = newLeft;
    }

    public void setRight(Node newRight) {
        this.right = newRight;
    }

    public boolean isRoot() {
        return this.getParent() == null;
    }

    public boolean isLeaf() {
        return this.getDegree() == 0;
    }

    public int getDegree() {
        int degree = 0;
        if (this.getLeft() != null)
            degree++;
        if (this.getRight() != null)
            degree++;
        return degree;
    }

    public int getLevel() {
        if (this.getParent() == null)
            return 0;
        return 1 + this.getParent().getLevel();
    }

    public int getHeight() {
        int leftHeight = this.getLeft() == null ? -1 : this.getLeft().getHeight();
        int rightHeight = this.getRight() == null ? -1 : this.getRight().getHeight();
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void printInformations() {
        System.out.printf("NÓ %s: ", this.getData());
        System.out.printf("É raiz?: %b; ", this.isRoot());
        System.out.printf("É folha?: %b; ", this.isLeaf());
        System.out.printf("Pai: %s; ", (!this.isRoot() ? this.getParent().getData() : "NULL"));
        System.out.printf("Esquerda: %s; ", (this.getLeft() != null ? this.getLeft().getData() : "NULL"));
        System.out.printf("Direita: %s; ", (this.getRight() != null ? this.getRight().getData() : "NULL"));
        System.out.printf("Grau: %d; ", this.getDegree());
        System.out.printf("Nível: %d; ", this.getLevel());
        System.out.printf("Altura: %d; ", this.getHeight());
        System.out.println();
    }

    public float visit() {
        return Float.NaN;
    }

}
