public class Product extends OperatorNode{
    Product(String data, Node parent, Node left, Node right) {
        super(data, parent, left, right);
    }

    Product(String data) {
        super(data, null, null, null);
    }

    Product() {
        super("");
    }

    @Override
    public float visit(){
        return this.getLeft().visit() * this.getRight().visit();
    }

}
