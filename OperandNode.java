public class OperandNode extends Node {
    OperandNode(String data, Node parent, Node left, Node right){
        super(data, parent, left, right);
    }

    OperandNode(String data){
        super(data, null, null, null);
    }

    OperandNode(){
        super("");
    }

    @Override
    public float visit(){   // Se for um operando, é folha. Logo, converto o conteudo do nó e retorno
        return Float.parseFloat(this.getData());
    }
}