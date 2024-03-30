public class Sum extends OperatorNode{
    Sum(String data, Node parent, Node left, Node right) {
        super(data, parent, left, right);
    }

    Sum(String data) {
        super(data, null, null, null);
    }

    Sum() {
        super("");
    }

    @Override
    public float visit(){
        Float left = this.getLeft() == null ? 0.0f : this.getLeft().visit(), right = this.getRight().visit();
        return left + right;
    }
}
