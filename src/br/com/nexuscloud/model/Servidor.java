package br.com.nexuscloud.model;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.exception.ServidorOfflineException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double precoPorHora, double ramTotal) {
        this.ip = ip;
        this.precoPorHora = precoPorHora;
        this.ramTotal = ramTotal;
    }

    public void ligar() {
        this.statusLigado = true;
    }

    public void desligar() {
        this.statusLigado = false;
    }

    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        if (!statusLigado) throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        containers.add(c);
    }

    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(c -> c.getRamNecessaria())
                .sum();
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Servidor that = (Servidor) o;
        return Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ip);
    }
}
