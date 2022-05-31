package Classes.NonUserInteractable;

import Classes.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Expressa {

    final double RAIO_EXPRESSA = 1; //km
    final int LIMITE_POPUP = 3;  //Limite de vezes que o programa procura um cliente para aproveitar a promoção expressa
    private final int popUpTime = 10; //segundos, duração do PopUp
    private final int tempoMaxPedido = 10; //segundos, tempo para o cliente fazer o pedido

    final RestauranteComExPressa RESTAURANTE_REQUERENTE;
    private Pedido pedidoExpresso;
    private final double capacidadeEntregador;
    private final Cliente cliente;
    private Queue<Cliente> clientesProximos = new LinkedList<Cliente>();
    boolean pedidoOnTime = false;


    private void encontrarClientesProximos (ArrayList<Cliente> clientes){
        /*cria lista de clientes que estão dentro do raio expressa*/
        for (Cliente c: clientes) {
            if(!c.isPedidoFeito()) {
                if (Math.abs(c.getEndereco() - cliente.getEndereco()) <= RAIO_EXPRESSA)
                    clientesProximos.add(cliente);
                if (clientesProximos.size() > LIMITE_POPUP)
                    return;
            }
        }
    }

        /*executa o popup em ordem segundo a lista do de clientes proximos*/
   private boolean popUp() {
       boolean popUpAceito = false;
       ExecutorService executor = Executors.newFixedThreadPool(10);
       System.out.println("Você tem "+ popUpTime+" segundos para receber a promoção de entrega ExPressa");
       System.out.println("Pressione qualquer tecla para aceitar");
       try {
           RespostaTimeOut respostaTimeOut = new RespostaTimeOut(popUpTime*1000);
           ScanPopUp scan = new ScanPopUp();

           ArrayList<Callable<Boolean>> callables = new ArrayList<>();
           callables.add(respostaTimeOut);
           callables.add(scan);

           boolean t = executor.invokeAny(callables);
           popUpAceito = t;

       } catch (Exception e) {
           e.getSuppressed();
       }
       executor.shutdownNow();
       if (popUpAceito){
           System.out.println("Você aceitou participar da promoção");
       } else System.out.println("Você não conseguiu aceitar a promoção a tempo");

       return popUpAceito;
   }

    public boolean findPedidoExpresso() {
        boolean pedidoEncontrado = false;
        this.encontrarClientesProximos(Cliente.getClientesExistentes());

        for (int i = 0; i < clientesProximos.size(); i++) {
            if (popUp()){
                System.out.println("Você tem " + tempoMaxPedido +" segundos para concluir o pedido");
                anotarPedido(clientesProximos.peek());
            }
            if(pedidoOnTime)
                return true;
        }
        return false;
    }

    /*
    anota pedido, retorna true se feito no tempo maximo,
    false se ultrapassar o tempo
    quando false, entrega o pedido para o restaurante como sendo não expresso*
    */
    private void anotarPedido(Cliente cliente) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            RespostaTimeOut respostaTimeOut = new RespostaTimeOut(tempoMaxPedido*1000);

            ArrayList<Callable<Object>> callables = new ArrayList<>();
            callables.add(respostaTimeOut);
            callables.add(cliente);

            Object future = executor.invokeAny(callables);
            if(future instanceof Pedido){
                Pedido pedido = (Pedido) future;
                if (pedido.getTamanhoTotalPedido() <=capacidadeEntregador/2){
                    pedidoExpresso = pedido;
                    pedidoOnTime = true;
                    return;
                } else {
                    RESTAURANTE_REQUERENTE.AdicionaPedido(pedido);
                    pedidoOnTime = false;
                    return;
                }
            } else {
                System.out.println("Infelizmente você não concluiu o pedido a tempo");
                pedidoOnTime = false;
                return;
            }

        }catch (Exception e){
            e.getSuppressed();
        }

        executor.shutdownNow();

    }

    public Pedido getPedidoExpresso() {
        return pedidoExpresso;
    }

    public Expressa(RestauranteComExPressa RESTAURANTE_REQUERENTE, double capacidade, Cliente cliente){
        this.RESTAURANTE_REQUERENTE = RESTAURANTE_REQUERENTE;
        this.capacidadeEntregador = capacidade;
        this.cliente = cliente;
    }
}