package business.veiculos;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import business.custos.*;
import business.Enuns.*;
import business.pattern.*;
import business.Rota;

public abstract class Veiculo implements Serializable, Comparable<Veiculo>, Sujeito {
    private static final long serialVersionUID = 1L;
    private final String PLACA;
    protected Custos custosFixos;
    protected List<Custos> custosAdicionais;
    private List<Rota> rotas = new ArrayList<Rota>();
    private List<Observer> observers;
    private int km_rodados;
    private Tanque tanque;
    private double valorVenda;

    public Veiculo(String placa, Combustivel combustivel, Capacidades capacidade, double valorVenda) throws IllegalArgumentException {
        if(capacidade.getCombustiveis().contains(combustivel)) {
            this.km_rodados = 0;
            this.PLACA = placa;
            this.tanque = new Tanque(combustivel, capacidade);
            this.valorVenda = valorVenda;

            custosAdicionais = new ArrayList<Custos>();
            observers = new ArrayList<Observer>();
        } else
            throw new IllegalArgumentException(capacidade + " permite apenas " + capacidade.getCombustiveisString() + " como combustível");
    }

    public double getValorVenda() {
        return this.valorVenda;
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

    public boolean addRota(Rota rota) throws ArithmeticException {
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

    public void addNovoCustoAdicional(String descricao, double preco) {
        custosAdicionais.add(new CustosVariaveis(descricao, preco));
    }

    public String getGastos() {
        return "&IPVA: R$ " + String.format("%.02f", this.calcularIpva()) + "#&SEGURO: R$ " + String.format("%.02f", this.calcularSeguro()) + "#&MANUTENÇÃO: #";
    }

    protected double getGastoTotal() {
        return this.custosFixos.calcularCustoTotal() + this.getTotalCustosAdicionais();
    }

    public abstract double calcularSeguro();

    public abstract double calcularIpva();

    public double getTotalCustosAdicionais() {
        return this.custosAdicionais.stream()
            .filter(custoVariavel -> custoVariavel instanceof CustosVariaveis)
            .mapToDouble(Custos::calcularCustoTotal)
            .sum();
    }

    @Override
    public String toString() {
        String saida = "- " + this.getPlaca() + " (R$ " + String.format("%.02f", this.getValorVenda()) + ")" +
            "# #KM RODADOS: " + this.getKm_rodados() + "km" +
            "#TANQUE: " + tanque.getCombustivel() +
            "#LITRAGEM ATUAL: " + String.format("%.02f", tanque.getLitragemAtual()) + "L" + " (" + String.format("%.02f", tanque.getCapacidadeMaxima()) + "L)" +
            "#GASTOS: #" + this.getGastos();

        if (rotas.size() > 0) {
            String stringRotas = new String();

            int i = 0;
            for (Rota rota : rotas) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
                stringRotas += "#&" + ++i + ". " + formato.format(rota.getData()) + " - " + rota.getDistancia() + "km";
            }

            saida += "# #ROTAS (" + rotas.size() + "): " + stringRotas;
        }

        return saida;
    }

    protected List<Custos> getCustosAdicionais() {
        return this.custosAdicionais;
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
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void updateAll() {
        this.observers.stream().forEach(observer -> observer.update());
    }
}