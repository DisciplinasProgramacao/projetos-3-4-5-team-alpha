package business;

import java.io.Serializable;
import java.time.LocalDate;

public class Rota implements Serializable {
    private LocalDate data;
    private int distancia;
    private String placa;

    public Rota(String placa, LocalDate data, int distancia){
        this.data = data;
        this.distancia = distancia;
        this.placa = placa;
    }
    public String getData() {
        return this.data.toString();
    }

    public int getDistancia() {
        return this.distancia;
    }

    public String getPlaca() {
        return this.placa;
    }

}
