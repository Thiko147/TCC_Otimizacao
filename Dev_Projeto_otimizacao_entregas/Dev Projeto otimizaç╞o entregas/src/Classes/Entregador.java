package Classes;

public class Entregador {

    private double capacidade = 10; //tamanho total de pedidos que o entregador pode levar
    private boolean pedidoComExpressa = false;
    private Pedido[] pedidos = new Pedido[2];


    public Entregador() {

    }

    public boolean checarEntregadorPronto(){
        if (pedidos[0].isPedidoPronto()){
            if(pedidoComExpressa){
                if(pedidos[1].isPedidoPronto()) {
                    this.enviarEntregador();

                    System.out.println("O entregador saiu com os pedidos");
                    return true;
                }
            } else  {
                this.enviarEntregador();
                System.out.println("O entregador Saiu com o pedido");
                return true;
            }
        }
        return false;
    }

    private void enviarEntregador(){
        pedidos = null;
        pedidoComExpressa = false;
    }

    public void addPedido(Pedido pedido) {
        this.pedidos[0] = pedido;

    }
    public void addPedidoExpressa(Pedido pedido){
        this.pedidos[1] = pedido;
        pedidoComExpressa = true;
    }


    public double getCapacidade() {
        return capacidade;
    }

}
