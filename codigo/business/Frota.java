package business;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

import business.veiculos.Veiculo;

public class Frota implements Serializable{
    private Set<Veiculo> veiculos = new HashSet<Veiculo>();

    public boolean inserirVeiculo(Veiculo veiculo) {
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

    public ArrayList<Rota> localizarRotasPorData(LocalDate data){
        ArrayList<Rota> aux1 = new ArrayList<Rota>();
        for(Veiculo veiculo:veiculos){
            veiculo.getRota().stream()
            .filter(rote -> rote.getData().equals(data))
            .forEach(rote -> aux1.add(rote));
        }
        return aux1;
    }
    public List<Veiculo> ordenarCustosDecrescentes() {
        List<Veiculo> list = new ArrayList<Veiculo>(veiculos);

        list.sort((veiculo1, veiculo2) -> veiculo2.compareTo(veiculo1));
        
        return list;
    }
    
    public List<Veiculo> veiculosComMaisRotas() {
    	List<Veiculo> list = new ArrayList<Veiculo>(veiculos);
    	
    	list.sort((veiculo1, veiculo2) -> veiculo2.compararRotas(veiculo1));
    	if(list.size() >= 3) {
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

}
