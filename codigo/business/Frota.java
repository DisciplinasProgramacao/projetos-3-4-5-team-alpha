package business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Frota {
    private Veiculo[] veiculos;
    private int contadorVeiculos;

    public Frota() {
        veiculos = new Veiculo[10];
        this.contadorVeiculos = 0;
    }

    public boolean inserirVeiculo(Veiculo veiculo) {
        if (contadorVeiculos < this.veiculos.length) {
            this.veiculos[contadorVeiculos++] = veiculo;
            return true;
        }

        return false;
    }

    public void salvar_arquivo(String filename) throws Exception {
        String path = "codigo/app/arquivos/";
        File directory = new File("codigo/app/arquivos/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(path + filename + ".bin", false))) {
            for (Veiculo veiculo : veiculos) {
                saida.writeObject(veiculo);
                if (veiculo.getRota() != null)
                    saida.writeObject(veiculo.getRota());
            }
            saida.flush();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void carregar_arquivo(String nome) {

    }

    public Veiculo localizar(String placa) throws Exception {
        for (int i = 0; i < veiculos.length; i++) {
            if ((veiculos[i].getPlaca().compareTo(placa)) == 0) {
                return veiculos[i];
            }
        }
        throw new Exception("Não existe um veículo na frota com esta placa");
    }

    public Veiculo[] localizar() {
        return veiculos;
    }

    public void imprimir(String placa) {
        System.out.println(this.veiculos[Integer.parseInt(placa)].toString());
    }
}
