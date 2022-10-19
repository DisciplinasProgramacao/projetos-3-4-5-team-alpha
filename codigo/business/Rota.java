package business;

import java.time.LocalDate;

public class Rota {
    private LocalDate data;
    private int distancia;

    public Rota(LocalDate data, int distancia){
        this.data = data;
        this.distancia = distancia;
    }
    public LocalDate getData() {
        return data;
    }

    public int getDistancia() {
        return distancia;
    }

}
