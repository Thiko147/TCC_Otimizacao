package Classes;

import Classes.NonUserInteractable.Expressa;

public class RestauranteComExPressa extends Restaurante {

    public RestauranteComExPressa() {
    }

    @Override
    public boolean getExpressaDisponibilidade() {
        return true;
    }

    @Override
    public void AdicionaPedido( Pedido pedido) {
        filaDePedidos.add(pedido);
        Entregador entregador = entregadores.remove();
        entregador.addPedido(pedido);
        pedido.getCliente().setPedidoFeito(true);
        double capacidade = entregador.getCapacidade();
        entregadoresComPedidos.add(entregador);
        if (pedido.getTamanhoTotalPedido() <= capacidade / 2) {
            Expressa expressa = new Expressa(this, capacidade, pedido.getCliente());
            if (expressa.findPedidoExpresso()) {
                pedido.setPedidoExpresso(true);
                filaDePedidos.add(0, expressa.getPedidoExpresso());
                entregador.addPedidoExpressa(expressa.getPedidoExpresso());
            }
        }

    }
}
