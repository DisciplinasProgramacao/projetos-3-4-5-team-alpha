package business;

public class Carro extends veiculo {
    private static double VALOR_ALINHAMENTO = 80.00;
    private static final int QUILOMETRO_ALINHAMENTO=10000;
    private int qtdAlinhamentos;

    public int getQtdAlinhamentos() {
        return qtdAlinhamentos;
    }

    public void setQtdAlinhamentos(int qtdAlinhamentos) {
        this.qtdAlinhamentos = qtdAlinhamentos;
    }

    public static double getVALOR_ALINHAMENTO() {
        return VALOR_ALINHAMENTO;
    }

    public int getQUILOMETRO_ALINHAMENTO() {
        return QUILOMETRO_ALINHAMENTO;
    }

    public double getGastos() {
        return 0.0;
    }

    public void calcular_seguro() {

    }
}
