package business;

import java.io.Serializable;

import business.custos.Custos;

public abstract class Veiculo implements Serializable {

    private final float PERCENTUAL_IPVA;
    private final int tanque;
    private float autonomia, valor_venda;
    private int km_rodados;
    private String placa;
    private Rota rota;
    private Custos custos;

    public Veiculo(String placa, int tanque, float autonomia, float valor_venda, float percental_ipva) {
        this.autonomia = autonomia;
        this.km_rodados = 0;
        this.placa = placa;
        this.valor_venda = valor_venda;
        this.tanque = tanque;
        this.PERCENTUAL_IPVA = percental_ipva;
        custos = new Custos();
    }

    public int getTanque() {
        return tanque;
    }

    public float getAutonomia() {
        return autonomia;
    }

    public int getKm_rodados() {
        return km_rodados;
    }

    public String getPlaca() {
        return placa;
    }

    public Rota getRota() {
        return rota;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setRota(Rota rota) throws Exception {
        this.rota = rota;
    }

    public abstract float getGastos() throws Exception;

    public abstract float calcular_seguro();

    public abstract float getGastosAdicionais();

    public float calcular_ipva() {
        custos.calcularIpva(this.getValor_venda(), PERCENTUAL_IPVA);
        return custos.getIpva();
    }

    @Override
    public String toString() {
        if (rota != null) {
            return ("Placa: " + this.getPlaca() + " - Rota: " + rota.getDistancia() + "km" + " no dia: "
                    + rota.getData());
        }
        return ("Placa: " + this.getPlaca() + " - Tanque: " + this.getTanque());
    }
}