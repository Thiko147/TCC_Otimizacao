package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Restaurante {



    protected ArrayList<Pedido> filaDePedidos = new ArrayList<Pedido>();
    protected Queue<Entregador> entregadores = new LinkedList<Entregador>();
    protected ArrayList<Entregador> entregadoresComPedidos = new ArrayList<>();

    private int pedidosFicandoProntos = -3; //parametro meramente ilustrativo

    public void addEntregador(Entregador entregador){
        entregadores.add(entregador);
    }

    public void pedidoPronto(){
        filaDePedidos.get(0).setPedidoPronto(true);
        filaDePedidos.remove(0);
        pedidosFicandoProntos -= 1;
        for (Entregador entregador : entregadoresComPedidos) {
            if (entregador.checarEntregadorPronto()){
                entregadoresComPedidos.remove(entregador);
                entregadores.add(entregador); //pera fins ilustrativos, os entregadores são instantaneos
                return;
            }
        }
    }

    public boolean getExpressaDisponibilidade(){
        return false;
    }

    public void AdicionaPedido(Pedido pedido){
            filaDePedidos.add(pedido);
            Entregador entregador = entregadores.remove();
            entregador.addPedido(pedido);
            entregadoresComPedidos.add(entregador);
            pedido.getCliente().setPedidoFeito(true);

            //criando rotatividade artificial de pedidos, para fins ilustrativos
            pedidosFicandoProntos += 1;
            if (pedidosFicandoProntos > 0){
                pedidoPronto();
            }
    }



    public Restaurante() {
    }

}
