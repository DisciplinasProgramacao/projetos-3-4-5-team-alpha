package business;

public class Utilitario {
    private String tipo_utilitario;
    private static double VALOR_ALINHAMENTO = 120.00;
    private static final int QUILOMETRO_ALINHAMENTO=10000;
    private static double VALOR_VISTORIA = 1000.00;
    private static final int QUILOMETRO_VISTORIA=10000;
    private int qtdVistoria;
    private int qtdAlinhamento;

    public int getQtdAlinhamento() {
        return qtdAlinhamento;
    }

    public void setQtdAlinhamento(int qtdAlinhamento) {
        this.qtdAlinhamento = qtdAlinhamento;
    }

    public String getTipo_utilitario() {
        return tipo_utilitario;
    }

    public static double getVALOR_ALINHAMENTO() {
        return VALOR_ALINHAMENTO;
    }

    public int getQUILOMETRO_VISTORIA() {
        return QUILOMETRO_VISTORIA;
    }

    public static double getVALOR_VISTORIA() {
        return VALOR_VISTORIA;
    }

    public int getQUILOMETRO_ALINHAMENTO() {
        return QUILOMETRO_ALINHAMENTO;
    }

    public double getGastos() {
        return 0.00;
    }

    public void calcukar_seguro() {

    }

    public void setTipo_utilitario(String tipo_utilitario) {
        this.tipo_utilitario = tipo_utilitario;
    }

    public static void setVALOR_ALINHAMENTO(double vALOR_ALINHAMENTO) {
        VALOR_ALINHAMENTO = vALOR_ALINHAMENTO;
    }

    public static int getQuilometroAlinhamento() {
        return QUILOMETRO_ALINHAMENTO;
    }

    public static void setVALOR_VISTORIA(double vALOR_VISTORIA) {
        VALOR_VISTORIA = vALOR_VISTORIA;
    }

    public static int getQuilometroVistoria() {
        return QUILOMETRO_VISTORIA;
    }

    public int getQtdVistoria() {
        return qtdVistoria;
    }

    public void setQtdVistoria(int qtdVistoria) {
        this.qtdVistoria = qtdVistoria;
    }
}
