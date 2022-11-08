package business;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import business.veiculos.Veiculo;

public class Frota {
    private Set<Veiculo> veiculos = new HashSet<Veiculo>();

    public boolean inserirVeiculo(Veiculo veiculo) {   
            veiculos.add(veiculo);
            return true;
    }

    public void salvar_arquivo(String filename) throws Exception {
        String path = "app/arquivos/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (ObjectOutputStream saidaVeiculos = new ObjectOutputStream(new FileOutputStream(path + filename + ".bin", false))) {
            for (Veiculo veiculo : veiculos) {
                if(veiculo != null) {
                    saidaVeiculos.writeObject(veiculo);
                }
            }

            saidaVeiculos.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar dados no disco!");
            e.printStackTrace();
        }

    }

    public void carregar_arquivo(String filename) throws Exception {
        String path = "app/arquivos/";
        File directory = new File(path);
        if (!directory.exists()) {
            throw new Exception();
        }

        try (FileInputStream fis = new FileInputStream(path + filename + ".bin"); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                Veiculo veiculo = (Veiculo) inputFile.readObject();
                this.inserirVeiculo(veiculo);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao carregar '" + filename + "' do disco!");
            e.printStackTrace();
        }
    }

    public Veiculo localizar(String placa) throws Exception {
        for (Veiculo veiculo : veiculos) {
            if (placa.equals(veiculo.getPlaca())) {
                return veiculo;
            }
        }
        throw new Exception("Não existe um veículo na frota com esta placa");
    }

    public Veiculo[] toArray() {
        Set<Veiculo> conjuntoOrdenado = new TreeSet<Veiculo>(veiculos);

        Veiculo[] array = new Veiculo[conjuntoOrdenado.size()];

        return conjuntoOrdenado.toArray(array);
    }
}
