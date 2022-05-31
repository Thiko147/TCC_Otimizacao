package Classes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Cliente implements Callable {

    private final double endereco;
    private double lastAdress = 0;
    private boolean pedidoFeito = false;
    private static ArrayList<Cliente> clientesExistentes = new ArrayList<Cliente>();

    {
        endereco = lastAdress+0.5;
        clientesExistentes.add(this);
    }

    public Cliente() {

    }

    public double getEndereco() {
        return endereco;
    }

    public Pedido fazerPedido(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira os Dados do seu pedido");
        double tamanho = scanner.nextDouble();
        scanner.nextLine();
        Pedido pedido = new Pedido(tamanho, this);
        System.out.println("Pedido realizado com sucesso");
        return pedido;
    }

    public void setPedidoFeito(boolean pedidoFeito) {
        this.pedidoFeito = pedidoFeito;
    }

    public boolean isPedidoFeito() {
        return pedidoFeito;
    }

    public static ArrayList<Cliente> getClientesExistentes() {
        return clientesExistentes;
    }

    @Override
    public Object call() throws Exception {

        return fazerPedido();
    }
}
