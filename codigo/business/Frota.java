package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Frota {
    private Veiculo[] veiculos;
    private int contadorVeiculos;

    public Frota(){
        veiculos = new Veiculo[10];
        this.contadorVeiculos = 0;
    }

    public boolean inserirVeiculo(Veiculo veiculo) {
        if(contadorVeiculos < this.veiculos.length) {
            this.veiculos[contadorVeiculos++] = veiculo;
            return true;
        }
        
        return false;
    }

    public void salvar_arquivo(String filename) throws Exception {
        String path = "codigo/app/arquivos/";
        File directory = new File(path);
        if(!directory.exists()) {
            directory.mkdirs();
        }

        try (ObjectOutputStream saidaVeiculos = new ObjectOutputStream(new FileOutputStream(path + filename + ".bin", false))) {
            for (Veiculo veiculo : veiculos) {
                saidaVeiculos.writeObject(veiculo);
            }

            saidaVeiculos.flush();

        } catch (Exception e) {
            throw new Exception(e);
        }

        try (ObjectOutputStream saidaRotas = new ObjectOutputStream(new FileOutputStream(path + filename + "-rotas.bin", false))) {
            for (Veiculo veiculo : veiculos) {
                if(veiculo.getRota() != null)
                    saidaRotas.writeObject(veiculo.getRota());
            }

            saidaRotas.flush();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void carregar_arquivo(String filename) throws Exception {
        String path = "codigo/app/arquivos/";
        File directory = new File(path);
        if(!directory.exists()) {
            throw new Exception();
        }

        try (FileInputStream fis = new FileInputStream(path + filename + ".bin"); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				Veiculo veiculo = (Veiculo) inputFile.readObject();
				this.inserirVeiculo(veiculo);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar dados no disco!");
			e.printStackTrace();
		}

        try (FileInputStream fis = new FileInputStream(path + filename + "-rotas.bin"); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				Rota rota = (Rota) inputFile.readObject();
				this.localizar(rota.getPlaca()).setRota(rota);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar dados no disco!");
			e.printStackTrace();
		}
    }

    public Veiculo localizar(String placa) throws Exception {
        for(Veiculo veiculo : veiculos) {
            if(placa.equals(veiculo.getPlaca())) {
                return veiculo;
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
