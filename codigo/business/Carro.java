package business;

public class Carro extends Veiculo {
    public Carro(int tanque, float valor_ipva, float valor_seguro, float autonomia, int km_rodados, String placa, float valor_venda) {
        super(tanque, valor_ipva, valor_seguro, autonomia, km_rodados, placa, valor_venda);
    }

    private static double VALOR_ALINHAMENTO = 80.00;
    private static final int QUILOMETRO_ALINHAMENTO = 10000;
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
        return 0.0F;
    }

    public void calcular_seguro() {

    }

    @Override
    public void calcular_ipva() {
        
    }

    @Override
    public void setRota(Rota rota) throws Exception{
        if((this.getKm_rodados() + rota.getDistancia()) < QUILOMETRO_ALINHAMENTO){
                super.setRota(rota);
        }else{
            throw new Exception("Essa viagem excede os km necessário para o alinhamento, por favor realiza-lo");
        }
    }
}
