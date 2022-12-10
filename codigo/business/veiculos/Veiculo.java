package business.veiculos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.custos.*;
import business.Enuns.*;
import business.pattern.*;
import business.Rota;

public abstract class Veiculo implements Serializable, Comparable<Veiculo>, Sujeito {
    private static final long serialVersionUID = 1L;
    private final String PLACA;
    protected Custos custosFixo;
    protected List<Custos> custosVariaves;
    private List<Rota> rotas = new ArrayList<Rota>();
    private List<Observer> observers;
    private int km_rodados;
    private Tanque tanque;

    public Veiculo(String placa, Combustivel combustivel, Capacidades capacidade)  {
        this.km_rodados = 0;
        this.PLACA = placa;
        this.tanque = new Tanque(combustivel, capacidade);
        custosVariaves = new ArrayList<Custos>();
        observers = new ArrayList<Observer>();
    }

    public double getAutonomia() {
        return this.tanque.autonomia();
    }

    public int getKm_rodados() {
        return this.km_rodados;
    }

    public String getPlaca() {
        return this.PLACA;
    }

    public Rota getLastRota() {
        return this.rotas.get(rotas.size());
    }

    public List<Rota> getRota() {
        return this.rotas;
    }

    public boolean setRota(Rota rota) throws ArithmeticException {
        if (this.tanque.consumirCombustivel(rota.getDistancia())) {
            this.rotas.add(rota);
            this.km_rodados += rota.getDistancia();

            this.updateAll();
            return true;
        }

        if (this.tanque.AUTONOMIA_MAXIMA >= rota.getDistancia()) {
            this.reabastecer();
            this.tanque.consumirCombustivel(rota.getDistancia());
            this.rotas.add(rota);
            this.km_rodados += rota.getDistancia();

            this.updateAll();
            return true;
        } else {
            throw new ArithmeticException ("A rota deve ter uma distância menor que a autonima do veículo");
        }
    }

    public List<Rota> getRotas() {
        return this.rotas;
    }

    public int getQuantRotas() {
        return this.rotas.size();
    }

    public void reabastecer() {
        this.tanque.reabastecer();
    }

    public void addNovoCustoVariavel(String descricao, double preco) {
        custosVariaves.add(new CustosVariaveis(descricao, preco));
    }

    public String getGastos() {
        return "IPVA: R$ " + String.format("%.02f", this.calcularIpva()) + "#SEGURO: R$ " + String.format("%.02f", this.calcularSeguro()) + "#MANUTENÇÃO: #";
    }

    protected double getGastoTotal() {
        return this.custosFixo.calcularCustoTotal() + this.getTotalCustosVariaveis();
    }

    public abstract double calcularSeguro();

    public abstract double calcularIpva();

    public double getTotalCustosVariaveis() {
        return this.custosVariaves.stream()
            .filter(custoVariavel -> custoVariavel instanceof CustosVariaveis)
            .mapToDouble(Custos::calcularCustoTotal)
            .sum();
    }

    @Override
    public String toString() {
        if (rotas.size() > 0) {
            String stringRotas = new String();

            int i = 0;
            for (Rota rota : rotas) {
                stringRotas += rota.getData() + " - " + rota.getDistancia() + "km";
                if (i++ < rotas.size() - 1) {
                    stringRotas += " | ";
                }
            }

            return ("(" + this.getPlaca() + " - Tanque: " + " - Custo: R$" + this.getGastos()
                    + " - Km rodados: " + this.getKm_rodados() + "km - Rotas (" + rotas.size() + "): " + stringRotas
                    + " Tanque: " + tanque.getCombustivel() + " Litragem: "
                    + String.format("%.02f", tanque.getLitragemAtual()));
        }

        return ("(" + this.getPlaca() + ")" +
            "#KM RODADOS: " + this.getKm_rodados() + "km" +
            "#TANQUE: " + tanque.getCombustivel() +
            "#LITRAGEM ATUAL: " + String.format("%.02f", tanque.getLitragemAtual())) + "L" +
            "#GASTOS: #" + this.getGastos();
    }

    @Override
    public int compareTo(Veiculo outroVeiculo) {
        if (this.getGastoTotal() < outroVeiculo.getGastoTotal()) {
            return -1;
        } else if (this.getGastoTotal() > outroVeiculo.getGastoTotal()) {
            return 1;
        }

        return 0;
    }

    @Override
    public void observar(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void pararObservar(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateAll() {
        this.observers.stream().forEach(observer -> observer.update());
    }
}