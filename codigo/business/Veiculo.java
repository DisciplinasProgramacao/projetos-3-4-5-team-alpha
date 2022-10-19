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
    
    

    public void setRota(Rota rota) {
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
}