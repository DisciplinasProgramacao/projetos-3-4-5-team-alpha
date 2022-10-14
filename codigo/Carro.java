

public class Carro extends veiculo {
    private static double VALOR_ALINHAMENTO = 80.00;
    private int max_quilometro_alinhamento;

    public static double getVALOR_ALINHAMENTO() {
        return VALOR_ALINHAMENTO;
    }

    public int getMax_quilometro_alinhamento() {
        return max_quilometro_alinhamento;
    }

    public double getGastos() {
        return 0.0;
    }

    public void calcular_seguro() {

    }
}
