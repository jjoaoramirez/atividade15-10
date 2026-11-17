package br.com.nexuscloud.main;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.CloudManager;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;

public class MainCloud {
    public static void main (String[] args) {
        CloudManager cloudManager = new CloudManager();
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor = new Servidor("192.168.0.1", 5.5, 16);
        cloudManager.registrarCliente(cliente);
        cloudManager.provisionarServidor(cliente, servidor);
        Container container1 = new Container("C-01", "", 10);
        Container container2 = new Container("C-02", "", 8);
        // servidor.alocarContainer(container1);
        servidor.ligar();
        try {
            servidor.alocarContainer(container1);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            servidor.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(cloudManager.calcularFaturaAtiva(cliente));
    }
}
