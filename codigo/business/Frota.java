package business;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Frota {
    private Veiculo[] veiculos;
    private int contadorVeiculos;

    public Frota(){
        veiculo = new Veiculo[10];
        this.contadorVeiculos = 0;
    }

    public boolean inserirVeiculo(Veiculo veiculo) {
        if(contadorVeiculos < this.veiculos.length) {
            this.veiculo[contadorVeiculos] = veiculo;
            contadorVeiculos++;
            return true;
        }

        return false;
    }
    public void salvar_arquivo(String filename) throws Exception {
        try (DataOutputStream saida = new DataOutputStream(new FileOutputStream(filename, false))) {
            for (Veiculo veiculo : veiculos) {
                saida.writeUTF(veiculo.getPlaca());
                saida.writeFloat(veiculo.getAutonomia());
                saida.writeInt(veiculo.getKm_rodados());
                saida.writeFloat(veiculo.getValor_venda());
                saida.writeDate(veiculo.getRota().getData());
                saida.writeInt(veiculo.getRota().getDistancia());

            }
            saida.flush();

        } catch (Exception e) {
            throw new Exception(e);
            e.printStackTrace();
        }
    }

    public void carregar_arquivo(String nome) {

    }

    public Veiculo localizar(String placa) {
        return veiculos[0];
    }

    public Veiculo[] localizar() {
        return veiculo;
    }

    public void imprimir(String placa) {

    }
}
