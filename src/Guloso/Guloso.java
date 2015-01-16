package Guloso;

import java.util.ArrayList;

import NRP.Cliente;
import NRP.NRP;

public class Guloso {
	
	private NRP nrp;
	private int custoEntrada;
	private int custoTotal;
	private ArrayList<Cliente> solucao;
	
	public Guloso (NRP nrp, int custo){
		this.nrp = nrp;
		//this.custoEntrada = custo;
		double valor = (custo/100.0) * this.nrp.getCustoTotal();
		this.custoEntrada = (int) valor;
		this.solucao = this.gerarSolucao();
	}
	
	public ArrayList<Cliente> ordena(){
		ArrayList<Cliente> clientes = new ArrayList<>();		
		ArrayList<Cliente> solucao = new ArrayList<>();
		Cliente melhorAtual = null;
		int posicao = 0;
		
		for (int i = 0; i <= nrp.getClientesTotal(); i++) {
			clientes.add(nrp.getClientes().get(i));
		}
		
		for (int i = 0; i <= nrp.getClientesTotal(); i++) {
			melhorAtual = clientes.get(0);
			posicao = 0;
			for (int j = 1; j < clientes.size(); j++) {
				if(melhorAtual.getPeso() < clientes.get(j).getPeso()){
					melhorAtual = clientes.get(j);
					posicao = j;
				}				
			}
			solucao.add(melhorAtual);
			clientes.remove(posicao);
		}
		return solucao;
	}
	
	public ArrayList<Cliente> gerarSolucao(){
				
		ArrayList<Cliente> cliente = this.ordena();
		ArrayList<Cliente> solucaoAtual = new ArrayList<>();
		ArrayList<Cliente> solucaoAnterior = new ArrayList<>();

		int i = 0;
		while(true){
			if(i < cliente.size()){
				solucaoAtual.add(cliente.get(i));
				int custoAtual = this.nrp.getCustoDosClientes(solucaoAtual); 
				if(custoAtual < this.custoEntrada){
					this.custoTotal = custoAtual;
					solucaoAnterior.add(cliente.get(i));
				}else{
					solucaoAtual.remove(solucaoAtual.size()-1);
				}
				i++;				
			}else{				
				break;
			}
		}
		return solucaoAnterior;
	}
	
	

	public NRP getNrp() {
		return this.nrp;
	}

	public int getCustoEntrada() {
		return this.custoEntrada;
	}

	public void setCustoEntrada(int custoEntrada) {
		this.custoEntrada = custoEntrada;
	}

	public ArrayList<Cliente> getSolucao() {
		return solucao;
	}
	
	public int getCustoTotal() {
		return custoTotal;
	}


}
