package business;

class veiculo {
    private int taque;
    private float valor_ipva;
    private float valor_seguro;
    private float autonomia;
    private int km_rodados;
    private String placa;
    private Rota rota;
    private float valor_venda;
    private final double PERCENTUAL_IPVA=0.0;
 
    public double getGastos(){
        return 0.0;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public void calcular_ipva(float valor_venda){
        this.valor_ipva = valor_venda;
    }

    public void calcular_ipva(){
       
    }

    public void calcukar_seguro(){
        
    }

    public int getTaque() {
        return taque;
    }

    public float getValor_ipva() {
        return valor_ipva;
    }

    public float getValor_seguro() {
        return valor_seguro;
    }

    public float getAutonomia() {
        return autonomia;
    }

    public int getKm_rodados() {
        return km_rodados;
    }

    public String getPlaca() {
        return placa;
    }

    public Rota getRota() {
        return rota;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public double getPERCENTUAL_IPVA() {
        return PERCENTUAL_IPVA;
    }





    
}