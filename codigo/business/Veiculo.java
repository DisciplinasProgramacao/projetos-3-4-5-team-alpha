package business;

import java.io.Serializable;
import java.util.Random;

abstract class Veiculo implements Serializable {

    private final String _id;
    private final int tanque;
    private float valor_ipva;
    private float valor_seguro;
    private float autonomia;
    private int km_rodados;
    private String placa;
    private Rota rota;
    private float valor_venda;

    public Veiculo(String placa, String cod_id, int tanque, float autonomia, float valor_venda) {
        this.autonomia = autonomia;
        this.km_rodados = 0;
        this.placa = placa;
        this.valor_venda = valor_venda;
        this.tanque = tanque;

        Random aleatorio = new Random();
        this._id = cod_id + Integer.toString(aleatorio.nextInt(8999) + 1000);
    }

    public String getId() {
        return this._id;
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

    public abstract double getGastos();

    public abstract void calcular_ipva();

    public abstract void calcular_seguro();

    @Override
    public String toString() {
        return ("Placa: " + this.getPlaca());
    }
}