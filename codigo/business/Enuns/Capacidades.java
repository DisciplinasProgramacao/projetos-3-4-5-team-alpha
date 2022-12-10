package business.Enuns;

import java.util.ArrayList;
import java.util.List;

public enum Capacidades {
    CARRO(50, Combustivel.GASOLINA, Combustivel.ETANOL),
    VAN(60, Combustivel.GASOLINA, Combustivel.DIESEL),
    FURGÃO(80, Combustivel.GASOLINA, null),
    CAMINHÃO(250, Combustivel.DIESEL, null);

    private double capacidadeMaxima;
    private List<Combustivel> combustiveis;

    private Capacidades(double capacidadeMaxima, Combustivel combustivel1, Combustivel combustivel2) {
        combustiveis = new ArrayList<>();
        combustiveis.add(combustivel1);
        combustiveis.add(combustivel2);

        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }

    public List<Combustivel> getCombustiveis() {
        return this.combustiveis;
    }

    public String getCombustiveisString() {
        String saida = "";
        boolean segundo = false;
        int index = 0;

        for(Combustivel combustivelErro : combustiveis) {
            if(combustivelErro != null) {
                if(index > 0 && !segundo) {
                    saida += " e ";
                    segundo = true;
                }

                index++;
                
                saida += combustivelErro.toString();
            }
        }
        
        return saida;
    }

    public String[] getCombustiveisStringArray() {
        int length = this.combustiveis.size();
        String[] saida = new String[length];

        for(int i = 0; i < length; i++) {
            if(this.combustiveis.get(i) != null)
                saida[i] = this.combustiveis.get(i).toString();
        }

        return saida;
    }

    @Override
    public String toString() {
        String str = this.name();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}