public class Quocient extends OperatorNode{
    Quocient(String data, Node parent, Node left, Node right) {
        super(data, parent, left, right);
    }

    Quocient(String data) {
        super(data, null, null, null);
    }

    Quocient() {
        super("");
    }

    @Override
    public float visit(){
        if(this.getRight().visit() == 0) return Float.NaN;
        return this.getLeft().visit() / this.getRight().visit();
    }
}
