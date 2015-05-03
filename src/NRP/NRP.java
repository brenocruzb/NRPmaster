package NRP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NRP {
	private ArrayList<Requisito> requisitos;
	private ArrayList<Cliente> clientes;
	
	private ArrayList<ArrayList<Integer>> matrizAdjacente;

	private int numeroDeRequisitos;
	private int custoTotal;
	private int clientesTotal;
	
//	private Solucao solucao;

	private File entrada;
	private Scanner file;

	public NRP(String file) {
		this.requisitos = new ArrayList<>();
		this.requisitos.add(new Requisito(0, 0));
		this.clientes = new ArrayList<>();
		this.clientes.add(new Cliente(0, 0));

		this.matrizAdjacente = new ArrayList<>();

		this.numeroDeRequisitos = 0;
		custoTotal = 0;

		this.entrada = new File(file);

		try {
			this.file = new Scanner(this.entrada);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.carregar();
	}

	private void carregar() {

		// Requisitos do problema
		int nivel = this.file.nextInt();
		int id = 1;
		for (int i = 0; i < nivel; i++) {
			int req = this.file.nextInt();
			this.numeroDeRequisitos += req;

			for (int j = 0; j < req; j++) {
				int custo = this.file.nextInt();
				this.requisitos.add(new Requisito(id, custo));
				custoTotal += custo;
				id++;
			}
		}

		// Zerando a matriz adjacente
		for (int i = 0; i <= this.numeroDeRequisitos; i++) {
			this.matrizAdjacente.add(new ArrayList<Integer>());

			for (int j = 0; j <= this.numeroDeRequisitos; j++) {
				this.matrizAdjacente.get(i).add(0);
			}
		}

		// Criando a matriz adjacente decorrente do grafo criado pelas dependencias do problema
		int dependencias = this.file.nextInt();
		int reqA, reqB;
		for (int i = 0; i < dependencias; i++) {
			reqA = this.file.nextInt();
			reqB = this.file.nextInt();

			this.matrizAdjacente.get(reqA).set(reqB, 1);
		}

		// Clientes com seus respectivos pesos e requisitos
		this.clientesTotal = this.file.nextInt();		
//		this.solucao = new Solucao(this.clientesTotal);//Inicializa a solução
		for (int i = 1; i <= this.clientesTotal; i++) {
			int satisfacao = this.file.nextInt();
			int reqDoCliente = this.file.nextInt();
			Cliente cliente = new Cliente(i, satisfacao);

			for (int j = 0; j < reqDoCliente; j++) {
				int requisito = this.file.nextInt();				
				cliente.getRequisitos().add(this.requisitos.get(requisito));
			}
			this.clientes.add(cliente);
		}

	}

	//Retorna o custo total para implementar os requisitos de um cliente
	public int getCustoDoCliente(Cliente c) {
		ArrayList<Integer> listaDeRequisitos = new ArrayList<>();		
		int custo = 0;
		
		for (int i = 1; i < c.getRequisitos().size(); i++) {
			listaDeRequisitos.add(c.getRequisitos().get(i).getId());			
		}
		
		for (int i = 0; i < listaDeRequisitos.size(); i++) {			
			for (int j = 0; j < this.matrizAdjacente.size(); j++) {
				int bool = this.matrizAdjacente.get(j).get(listaDeRequisitos.get(i));
				if(bool == 1){
					int elementoDaMatriz = this.requisitos.get(j).getId();
					if(!listaDeRequisitos.contains(elementoDaMatriz)){
						listaDeRequisitos.add(elementoDaMatriz);
					}
				}				
			}			
		}
		
		
		for (int i = 0; i < listaDeRequisitos.size(); i++) {
			custo += this.requisitos.get(listaDeRequisitos.get(i)).getCusto();			
		}
				
		return custo;
	}
	
	public int getCustoDosClientes(ArrayList<Cliente> lista){
		ArrayList<Integer> listaDeRequisitos = new ArrayList<>();
		int custoTotal = 0;
		
		for (int i = 0; i < lista.size(); i++) {
			Cliente c = lista.get(i);
			
			for (int j = 1; j < c.getRequisitos().size(); j++) {				
				if(!listaDeRequisitos.contains(c.getRequisitos().get(j).getId())){
					listaDeRequisitos.add(c.getRequisitos().get(j).getId());
				}
			}
		}
			
		for (int j = 0; j < listaDeRequisitos.size(); j++) {			
			for (int k = 0; k < this.matrizAdjacente.size(); k++) {
				int bool = this.matrizAdjacente.get(k).get(listaDeRequisitos.get(j));
				if(bool == 1){
					int elementoDaMatriz = this.requisitos.get(k).getId();
					if(!listaDeRequisitos.contains(elementoDaMatriz)){
						listaDeRequisitos.add(elementoDaMatriz);
					}
				}				
			}	
		}	
					
		for (int j = 0; j < listaDeRequisitos.size(); j++) {
			custoTotal += this.requisitos.get(listaDeRequisitos.get(j)).getCusto();			
		}		
		
		return custoTotal;
	}
	
	public int getSatisfacaoDosClientes(ArrayList<Cliente> lista){
		int satisfacaoTotal = 0;
		for (int i = 1; i < lista.size(); i++) {
			satisfacaoTotal += lista.get(i).getPeso();
		}
		return satisfacaoTotal;
	}

	public int getNumeroDeRequisitos() {
		return this.numeroDeRequisitos;
	}

	public int getCustoTotal() {
		return this.custoTotal;
	}
	
	public int getClientesTotal(){
		return this.clientesTotal;
	}

	public ArrayList<Requisito> getRequisitos() {
		return requisitos;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<ArrayList<Integer>> getMatrizAdjacente() {
		return matrizAdjacente;
	}

	public void setMatrizAdjacente(ArrayList<ArrayList<Integer>> matrizAdjacente) {
		this.matrizAdjacente = matrizAdjacente;
	}
	
//	public Solucao getSolucao() {
//		return this.solucao;
//	}


}
