package business;

abstract class Veiculo {
    private int tanque;
    private float valor_ipva;
    private float valor_seguro;
    private float autonomia;
    private int km_rodados;
    private String placa;
    private Rota rota;
    private float valor_venda;
    
    
    

    public Veiculo(int tanque, float valor_ipva, float valor_seguro, float autonomia, int km_rodados, String placa, float valor_venda) {
        this.tanque = tanque;
        this.valor_ipva = valor_ipva;
        this.valor_seguro = valor_seguro;
        this.autonomia = autonomia;
        this.km_rodados = km_rodados;
        this.placa = placa;
        this.valor_venda = valor_venda;
    }



    public int getTanque() {
        return tanque;
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
    
    

    public void setRota(Rota rota) throws Exception{
        this.rota = rota;
    }
    
    protected void setValorIpva(float valor) {
    	this.valor_ipva = valor;
    }
    
    protected void setValorSeguro(float valor) {
    	this.valor_seguro = valor;
    }
    
    protected void setTanque(int tanque) {
    	this.tanque = tanque;
    }
    
    
    
    public abstract double getGastos();

    public abstract void calcular_ipva();
    
    public abstract void calcular_seguro();


    @Override
    public String toString(){
        return ("Placa: "+ this.getPlaca()+ ", " + "Proxima viagem: " +  this.getRota().getData());
    }
}