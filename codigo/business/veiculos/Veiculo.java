package business.veiculos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.Capacidades;
import business.Combustivel;
import business.Rota;
import business.custos.Custos;
import business.custos.CustosVariaveis;

public abstract class Veiculo implements Serializable, Comparable<Veiculo> {
    private static final long serialVersionUID = 1L;
    private int km_rodados;
    private final String PLACA;
    private List<Rota> rotas = new ArrayList<Rota>();
    private Tanque tanque;
    protected Custos custosFixo, custosVariaveis;

    public Veiculo(String placa, Combustivel combustivel, Capacidades capacidade)  {
        this.km_rodados = 0;
        this.PLACA = placa;
        this.tanque = new Tanque(combustivel, capacidade);
        custosVariaveis = new CustosVariaveis();
    }
    public Veiculo(String placa, Combustivel combustivel, String tipo) throws Exception {
        
        this.km_rodados = 0;
        this.PLACA = placa;
        if(tipo == "Van"){
            this.tanque = new Tanque(combustivel, Capacidades.VAN);
        }else{
            this.tanque = new Tanque(combustivel, Capacidades.FURGAO);
        }
        custosVariaveis = new CustosVariaveis();
    }

    public float getAutonomia() {
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

    public boolean setRota(Rota rota) {
        if (this.tanque.consumirCombustivel(rota.getDistancia())) {
            this.rotas.add(rota);
            this.km_rodados += rota.getDistancia();

            return true;
        }

        if (this.tanque.AUTONOMIA_MAXIMA >= rota.getDistancia()) {
            this.reabastecer();
            this.tanque.consumirCombustivel(rota.getDistancia());
            this.rotas.add(rota);
            this.km_rodados += rota.getDistancia();
            return true;
        }

        return false;
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

    public void addNovoCustoVariavel(float preco) {
        ((CustosVariaveis) this.custosVariaveis).addNovoCustoVariavel(preco);
    }

    public float getGastos() {
        return this.custosFixo.calcularCustoTotal() + this.custosVariaveis.calcularCustoTotal();
    }

    public abstract float calcularSeguro();

    public abstract float calcularIpva();

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

            return ("Placa: " + this.getPlaca() + " - Tanque: " + " - Custo: R$" + this.getGastos()
                    + " - Km rodados: " + this.getKm_rodados() + "km - Rotas (" + rotas.size() + "): " + stringRotas
                    + " Tanque: " + tanque.getCombustivel() + " Litragem: "
                    + String.format("%.02f", tanque.getLitragemAtual()));
        }

        return ("Placa: " + this.getPlaca() + " - Custo: " + getGastos() + " - Km rodados: " + this.getKm_rodados()
                + " Tanque: " + tanque.getCombustivel() + " Litragem: "
                + String.format("%.02f", tanque.getLitragemAtual()));
    }

    @Override
    public int compareTo(Veiculo outroVeiculo) {
        if (this.getGastos() < outroVeiculo.getGastos()) {
            return -1;
        } else if (this.getGastos() > outroVeiculo.getGastos()) {
            return 1;
        }

        return 0;
    }

    public int compararRotas(Veiculo outroVeiculo) {
        if (this.rotas.size() < outroVeiculo.rotas.size()) {
            return -1;
        } else if (this.rotas.size() > outroVeiculo.rotas.size()) {
            return 1;
        }

        return 0;
    }
}