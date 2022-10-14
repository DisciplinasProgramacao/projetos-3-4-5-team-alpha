package business;

public class Utilitario {
    private String tipo_utilitario;
    private static double VALOR_ALINHAMENTO = 120.00;
    private int max_quilometro_alinhamento;
    private static double VALOR_VISTORIA = 1000.00;
    private int max_quilometro_vistoria;

    public String getTipo_utilitario() {
        return tipo_utilitario;
    }

    public static double getVALOR_ALINHAMENTO() {
        return VALOR_ALINHAMENTO;
    }

    public int getMax_quilometro_vistoria() {
        return max_quilometro_vistoria;
    }

    public static double getVALOR_VISTORIA() {
        return VALOR_VISTORIA;
    }

    public int getMax_quilometro_alinhamento() {
        return max_quilometro_alinhamento;
    }

    public double getGastos() {
        return 0.00;
    }

    public void calcukar_seguro() {

    }
}
