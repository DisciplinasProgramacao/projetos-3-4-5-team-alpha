package business;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import business.veiculos.Veiculo;
import business.pattern.Observer;

public class Frota implements Serializable, Observer {
    private Set<Veiculo> veiculos = new HashSet<Veiculo>();

    public boolean inserirVeiculo(Veiculo veiculo) {
        veiculo.addObserver(this);
        veiculos.add(veiculo);
        return true;
    }

    public Veiculo localizar(String placa) throws NoSuchFieldException {
        for (Veiculo veiculo : veiculos) {
            if (placa.equals(veiculo.getPlaca())) {
                return veiculo;
            }
        }
        throw new NoSuchFieldException ("Não existe um veículo na frota com esta placa");
    }

    public Veiculo[] toArray() {
        
        Veiculo[] array = new Veiculo[veiculos.size()];
        return veiculos.toArray(array);
    }

    public List<Rota> localizarRotasPorData(LocalDate data) throws NoSuchFieldException {
        List<Rota> rotasEncontradas = new ArrayList<Rota>();

        for(Veiculo veiculo:veiculos) {
            veiculo.getRota().stream()
            .filter(rote -> rote.getData().equals(data))
            .forEach(rote -> rotasEncontradas.add(rote));
        }

        if(rotasEncontradas.size() != 0) {
            return rotasEncontradas;
        } else
            throw new NoSuchFieldException ("Nenhuma rota encontrada");
    }
    public List<Veiculo> ordenarCustosDecrescentes() {
        List<Veiculo> list = new ArrayList<Veiculo>(veiculos);

        list.sort((veiculo1, veiculo2) -> veiculo2.compareTo(veiculo1));
        
        return list;
    }
    
    public List<Veiculo> veiculosComMaisRotas() {
    	List<Veiculo> list = new ArrayList<Veiculo>(veiculos);
    	
    	list.sort((veiculo1, veiculo2) -> {
            if (veiculo2.getQuantRotas() < veiculo1.getQuantRotas()) {
                return -1;
            } else if (veiculo2.getQuantRotas() > veiculo1.getQuantRotas()) {
                return 1;
            }
    
            return 0;
        });

    	if(list.size() > 3) {
            return list.subList(0, 3);
        }

        return list;
    }
    
    public double quilometragemMedia() {
    	double media = veiculos.stream()
    			.mapToInt(Veiculo::getKm_rodados)
    			.sum();
    			
    	return media / veiculos.stream()
    			.mapToInt(Veiculo::getQuantRotas)
    			.sum();
    }

    @Override
    public void update() {

    }
}
