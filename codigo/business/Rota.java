package business;

import java.io.Serializable;
import java.time.LocalDate;

public class Rota implements Serializable {
    private LocalDate data;
    private int distancia;

    public Rota(LocalDate data, int distancia){
        this.data = data;
        this.distancia = distancia;
    }
    public String getData() {
        return data.toString();
    }

    public int getDistancia() {
        return distancia;
    }

}
