package business;

public class Frota {
    private Veiculo[] veiculo;
    private int contadorVeiculo;

    public Frota(){
        veiculo = new Veiculo[10];
        this.contadorVeiculo =0;
    }

    public boolean inserirVeiculo(Veiculo veiculo){
        if(contadorVeiculo<this.veiculo.length){
            this.veiculo[contadorVeiculo]=veiculo;
            contadorVeiculo++;
            return true;
        }else{
        return false;
    }
    }
    public void salvar_arquivo(String nome) {

    }

    public void carregar_arquivo(String nome) {

    }

    public Veiculo localizar(String placa) {
        return veiculo[0];
    }

    public Veiculo[] localizar() {
        return veiculo;
    }

    public void imprimir(String placa) {

    }
}
