package business;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {

    private final int tanque;
    private float valor_ipva;
    private float valor_seguro;
    private float autonomia;
    private int km_rodados;
    private String placa;
    private Rota rota;
    private float valor_venda;
    private final float PERCENTUAL_IPVA;



    public Veiculo(String placa, int tanque, float autonomia, float valor_venda, float percental_ipva) {
        this.autonomia = autonomia;
        this.km_rodados = 0;
        this.placa = placa;
        this.valor_venda = valor_venda;
        this.tanque = tanque;
        this.PERCENTUAL_IPVA = percental_ipva;
    }



    public int getTanque() {
        return tanque;
    }

    public float getValor_ipva() {
        return valor_ipva;
    }

    public float getValor_seguro() {
        return valor_seguro;
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

    protected void setValorIpva(float valor) {
        this.valor_ipva = valor;
    }

    protected void setValorSeguro(float valor) {
        this.valor_seguro = valor;
    }

    

    public abstract float getGastos();

    public abstract void calcular_seguro();

    public void calcular_ipva() {
        this.setValorIpva(this.getValor_venda() * PERCENTUAL_IPVA);
    }

    @Override
    public String toString() {
        return ("Placa: " + this.getPlaca());
    }
}