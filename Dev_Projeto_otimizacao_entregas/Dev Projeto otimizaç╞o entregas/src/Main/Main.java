package Main;


import Classes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        RestauranteComExPressa NekomimiCafe = new RestauranteComExPressa();
        Restaurante MaidCafe = new Restaurante();

        Entregador entregador1 = new Entregador();
        Entregador entregador2 = new Entregador();
        Entregador entregador3 = new Entregador();
        Entregador entregador4 = new Entregador();
        Entregador entregador5 = new Entregador();
        Entregador entregador6 = new Entregador();
        Entregador entregador7 = new Entregador();
        Entregador entregador8 = new Entregador();

        MaidCafe.addEntregador(entregador1);
        MaidCafe.addEntregador(entregador2);
        MaidCafe.addEntregador(entregador3);
        MaidCafe.addEntregador(entregador4);
        MaidCafe.addEntregador(entregador5);
        NekomimiCafe.addEntregador(entregador6);
        NekomimiCafe.addEntregador(entregador7);
        NekomimiCafe.addEntregador(entregador8);

        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        Cliente cliente4 = new Cliente();
        Cliente cliente5 = new Cliente();
        Cliente cliente6 = new Cliente();

        //adiciona pedidos em um restaurante sem expressa
        MaidCafe.AdicionaPedido(cliente1.fazerPedido());
        MaidCafe.AdicionaPedido(cliente1.fazerPedido());
        MaidCafe.AdicionaPedido(cliente1.fazerPedido());
        MaidCafe.AdicionaPedido(cliente1.fazerPedido());


        //adiciona pedidos em um restaurante com expressa
        NekomimiCafe.AdicionaPedido(cliente3.fazerPedido());
        NekomimiCafe.AdicionaPedido(cliente5.fazerPedido());



    }
}
