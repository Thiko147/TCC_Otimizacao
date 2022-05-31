package Classes;

public class Pedido {

    private final double tamanhoTotalPedido;
    private final Cliente cliente;
    private boolean pedidoPronto;
    private boolean pedidoExpresso;

    public Pedido(double tamanhoTotalPedido, Cliente cliente) {
        this.tamanhoTotalPedido = tamanhoTotalPedido;
        this.cliente = cliente;
        this.pedidoPronto = false;
    }

    public boolean isPedidoPronto() {
        return pedidoPronto;
    }

    public void setPedidoPronto(boolean pedidoPronto) {
        this.pedidoPronto = pedidoPronto;
    }

    public boolean isPedidoExpresso() {
        return pedidoExpresso;
    }

    public void setPedidoExpresso(boolean pedidoExpresso) {
        this.pedidoExpresso = pedidoExpresso;
    }

    public double getTamanhoTotalPedido() {
        return tamanhoTotalPedido;

    }

    public Cliente getCliente() {
        return cliente;
    }
}
