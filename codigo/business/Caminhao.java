<<<<<<< Updated upstream
package business;

=======
>>>>>>> Stashed changes
public class Caminhao {
    private static double VALOR_VISTORIA = 1000.00;
    private static final int QUILOMETRO_VISTORIA=30000;
    private static double VALOR_MANUTENCAO = 1000.00;
<<<<<<< Updated upstream
    private static final int QUILOMETRO_MANUTENCAO=20000;
    private int qtdManutencao;
    private int qtdVistoria;
    
    public int getQtdManutencao() {
        return qtdManutencao;
    }
    public void setQtdManutencao(int qtdManutencao) {
        this.qtdManutencao = qtdManutencao;
    }

    public int getQtdVistoria() {
        return qtdVistoria;
    }
    public void setQtdVistoria(int qtdVistoria) {
        this.qtdVistoria = qtdVistoria;
    }
=======
    private int max_quilometro_manutencao;

>>>>>>> Stashed changes
    public static double getVALOR_VISTORIA() {
        return VALOR_VISTORIA;
    }
    public int getQUILOMETRO_VISTORIA() {
        return QUILOMETRO_VISTORIA;
    }
    public static double getVALOR_MANUTENCAO() {
        return VALOR_MANUTENCAO;
    }
    public int getQUILOMETRO_MANUTENCAO() {
        return QUILOMETRO_MANUTENCAO;
    }

    public double getGastos(){
        return 0.0;
    }
    public void calcular_seguro(){

    }
}
