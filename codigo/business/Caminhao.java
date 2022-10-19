package business;



public class Caminhao extends Veiculo {
    

    private static double VALOR_VISTORIA = 1000.00;
    private static final int QUILOMETRO_VISTORIA = 30000;
    private static double VALOR_MANUTENCAO = 1000.00;
    private static final int QUILOMETRO_MANUTENCAO = 20000;
    private int qtdManutencao;
    private int qtdVistoria;




    public Caminhao(int tanque, float valor_ipva, float valor_seguro, float autonomia, int km_rodados, String placa, float valor_venda) {
        super(tanque, valor_ipva, valor_seguro, autonomia, km_rodados, placa, valor_venda);
    }
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

    public double getGastos() {
        return 0.0;
    }

    public void calcular_seguro() {

    }

    @Override
    public void setRota(Rota rota) throws Exception{
        if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_VISTORIA){
            if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_MANUTENCAO){
                super.setRota(rota);
            }else{
                throw new Exception("Essa viagem excede os km necessário para a manutenção, por favor realiza-la");
            }
        }else{
            throw new Exception("Essa viagem excede os km necessário para a vistoria, por favor realiza-la");
        }
    }

    @Override
    public void calcular_ipva() {
        // TODO Auto-generated method stub

    }

    
}
