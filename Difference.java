public class Difference extends OperatorNode{
    Difference(String data, Node parent, Node left, Node right) {
        super(data, parent, left, right);
    }

    Difference(String data) {
        super(data, null, null, null);
    }

    Difference() {
        super("");
    }

    @Override
    public float visit(){   // operação unária: se o filho da esquerda for nulo, retorna 0-direita
        Float left = this.getLeft() == null ? 0.0f : this.getLeft().visit(), right = this.getRight().visit();
        return left - right;
    }

}
