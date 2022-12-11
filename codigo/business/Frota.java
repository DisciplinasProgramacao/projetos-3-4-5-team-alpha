package business;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import business.veiculos.Veiculo;
import business.pattern.Observer;
import business.pattern.Sujeito;

public class Frota implements Serializable, Observer {
    private Set<Veiculo> veiculos;
    private Set<Veiculo> veiculosComMaisRotas;

    public Frota() {
        veiculos = new LinkedHashSet<>();
        veiculosComMaisRotas = new LinkedHashSet<>();
    }

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

        for(Veiculo veiculo : veiculos) {
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
    
    private void calcularVeiculosComMaisRotas(Veiculo veiculo) {
        boolean[] inserido = new boolean[1];
        inserido[0] = false;
        
        if(veiculosComMaisRotas.size() < 3) {
            veiculosComMaisRotas.add(veiculo);
            
        } else if( veiculosComMaisRotas.size() == 3) {
            veiculosComMaisRotas.forEach(veiculoDaLista -> {
                if(veiculoDaLista.compareQtdRotas(veiculo) <= 0 && !inserido[0]) {
                    
                    if(!veiculosComMaisRotas.contains(veiculo)) {
                        Veiculo lastElement = null;
                        for (Veiculo x : veiculosComMaisRotas){
                            lastElement = x;
                        }
                        
                        veiculosComMaisRotas.remove(lastElement);
                    }

                    veiculosComMaisRotas.add(veiculo);

                    inserido[0] = true;
                }
            });
        }
    	
        this.ordernarVeiculosComMaisRotas();
    }

    public List<Veiculo> veiculosComMaisRotas() {
        List<Veiculo> list = new ArrayList<Veiculo>(this.veiculosComMaisRotas);
        
        list.sort((veiculo1, veiculo2) -> veiculo2.compareQtdRotas(veiculo1));

        return list;
    }

    public void ordernarVeiculosComMaisRotas() {
        this.veiculosComMaisRotas = this.veiculosComMaisRotas.stream()
            .sorted((veiculo1, veiculo2) -> veiculo2.compareQtdRotas(veiculo1))
            .collect(Collectors.toSet());
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
    public void update(Sujeito veiculo) {
        this.calcularVeiculosComMaisRotas((Veiculo) veiculo);
    }
}
