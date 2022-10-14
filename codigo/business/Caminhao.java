package business;

public class Caminhao {
    private static double VALOR_VISTORIA = 1000.00;
    private int max_quilometro_vistoria;
    private static double VALOR_MANUTENCAO = 1000.00;
    private int max_quilometro_manutencao;


    public static double getVALOR_VISTORIA() {
        return VALOR_VISTORIA;
    }
    public int getMax_quilometro_vistoria() {
        return max_quilometro_vistoria;
    }
    public void setMax_quilometro_vistoria(int max_quilometro_vistoria) {
        this.max_quilometro_vistoria = max_quilometro_vistoria;
    }
    public static double getVALOR_MANUTENCAO() {
        return VALOR_MANUTENCAO;
    }
    public int getMax_quilometro_manutencao() {
        return max_quilometro_manutencao;
    }
    public void setMax_quilometro_manutencao(int max_quilometro_manutencao) {
        this.max_quilometro_manutencao = max_quilometro_manutencao;
    }

    public double getGastos(){
        return 0.0;
    }
    public void calcular_seguro(){

    }
}
