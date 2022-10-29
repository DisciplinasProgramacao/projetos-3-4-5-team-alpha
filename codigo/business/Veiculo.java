package business;

import java.io.Serializable;
public abstract class Veiculo implements Serializable {

    private final int tanque;
    private float autonomia;
    private int km_rodados;
    private String placa;
    private Rota rota;

    public Veiculo(String placa, int tanque, float autonomia) {
        this.autonomia = autonomia * tanque;
        this.km_rodados = 0;
        this.placa = placa;
        this.tanque = tanque;
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

    public boolean setRota(Rota rota) {
        if (rota.getDistancia() <= this.getAutonomia()) {
            this.km_rodados += rota.getDistancia();
            this.rota = rota;
            return true;
        }

        return false;
    }

    public abstract float getGastos();

    public abstract float calcular_Seguro();

    public abstract float calcular_Ipva();

    @Override
    public String toString() {

        if (rota != null) {
            return ("Placa: " + this.getPlaca() + " - Rota: " + rota.getDistancia() + "km" + " no dia: "
                    + rota.getData() + " - Custo: " + getGastos() + " - Km rodados: " + this.getKm_rodados());
        }
        return ("Placa: " + this.getPlaca() + " - Tanque: " + this.getTanque() + " - Custo: " + this.getGastos()
                + " - Km rodados: " + this.getKm_rodados());
    }
}