package business.veiculos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.Rota;

public abstract class Veiculo implements Serializable, Comparable<Veiculo> {

	private static final long serialVersionUID = 1L;
	private final int TANQUE;
    private float autonomia;
    private int km_rodados;
    private String placa;
    private List<Rota> rotas = new ArrayList<Rota>();

    public Veiculo(String placa, int tanque, float autonomia) {
        this.autonomia = autonomia * tanque;
        this.km_rodados = 0;
        this.placa = placa;
        this.TANQUE = tanque;
    }

    public int getTanque() {
        return TANQUE;
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

    public Rota getLastRota() {
        return rotas.get(rotas.size());
    }

    public ArrayList<Rota> getRota() { 
        
        return (ArrayList<Rota>) rotas;
    }

    public boolean setRota(Rota rota) {
        if (rota.getDistancia() <= this.getAutonomia()) {
            rotas.add(rota);
            km_rodados += rota.getDistancia();
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

    public abstract float getGastos();

    public abstract float calcular_Seguro();

    public abstract float calcular_Ipva();

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

            return ("Placa: " + this.getPlaca() + " - Tanque: " + this.getTanque() + " - Custo: R$" + this.getGastos()
                    + " - Km rodados: " + this.getKm_rodados() + "km - Rotas (" + rotas.size() + "): " + stringRotas);
        }

        return ("Placa: " + this.getPlaca() + " - Custo: " + getGastos() + " - Km rodados: " + this.getKm_rodados());
    }

    @Override
    public int compareTo(Veiculo outroVeiculo) {
        if (this.getGastos() < outroVeiculo.getGastos()) {
            return -1;
        }
        if (this.getGastos() > outroVeiculo.getGastos()) {
            return 1;
        }
        return 0;
    }
    
    public int compararRotas(Veiculo outroVeiculo) {
        if (this.rotas.size() < outroVeiculo.rotas.size()) {
            return -1;
        }
        if (this.rotas.size() > outroVeiculo.rotas.size()) {
            return 1;
        }
        return 0;
    }
}