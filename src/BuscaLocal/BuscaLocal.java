package BuscaLocal;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import NRP.Cliente;
import NRP.NRP;
import NRP.Solucao;

public class BuscaLocal {

	private NRP nrp;	
	private ArrayList<Solucao> vizinhos;
	private Solucao solucaoAtual;
	private int custoRestricao;
	
	public BuscaLocal(NRP nrp, int numVizinhos, int numIteracoes){//sem restricao
		this.nrp = nrp;		
		this.custoRestricao = 0;
		this.vizinhos = new ArrayList<Solucao>();
		this.solucaoAtual = new Solucao(nrp.getClientesTotal());
		this.avalia(this.solucaoAtual);		
		this.melhorSolucao(numIteracoes, numVizinhos);
	}
	
	public BuscaLocal(NRP nrp, int custoTotal, int numVizinhos, int numIteracoes){//com restricao
		this.nrp = nrp;		
		this.custoRestricao = custoTotal;
		this.vizinhos = new ArrayList<Solucao>();
		this.solucaoAtual = new Solucao(nrp.getClientesTotal());
		this.avalia(this.solucaoAtual);
		while(this.solucaoAtual.getCusto() > custoTotal){
			this.solucaoAtual = new Solucao(nrp.getClientesTotal());
			this.avalia(this.solucaoAtual);
		}
		this.melhorSolucaoComRestricao(numIteracoes, numVizinhos);
	}
	
	public void gerarVizinhos(int numVizinhos){
		for (int i = 0; i < numVizinhos; i++) {
			this.vizinhos.add(this.solucaoAtual.alterarBit());
		}		
	}
	
	public void avalia(Solucao solucao){
		ArrayList<Cliente> c = new ArrayList<>();
		for (int i = 0; i < solucao.getSolucao().length; i++) {
			if(solucao.getSolucao()[i] == 1){
				c.add(this.nrp.getClientes().get(i));
			}
		}
		
		solucao.setCusto(this.nrp.getCustoDosClientes(c));
		solucao.setSatisfacao(this.nrp.getSatisfacaoDosClientes(c));
	}
	
	public Solucao melhorVizinho(ArrayList<Solucao> vizinhos){
		Solucao melhorVizinho = null;
		this.avalia(vizinhos.get(0));
		for (int i = 1; i < vizinhos.size(); i++) {
			this.avalia(vizinhos.get(i));
			if (vizinhos.get(i-1).getSatisfacao() < vizinhos.get(i).getSatisfacao()) {
				melhorVizinho = vizinhos.get(i);
			}
		}
		
		return melhorVizinho;
	}
	
	public Solucao melhorSolucao(int numIteracoes, int numVizinhos) {		
		Solucao melhorVizinho = null;	
		for (int i = 0; i <= numIteracoes; i++) {
			this.gerarVizinhos(numVizinhos);
			melhorVizinho = this.melhorVizinho(this.vizinhos);
			if(this.solucaoAtual.getSatisfacao() < melhorVizinho.getSatisfacao()){
				this.solucaoAtual = melhorVizinho;
			}else{
				break;
			}
		}		
		return this.solucaoAtual;
	}
	
	public Solucao melhorVizinhoComRestricao(ArrayList<Solucao> vizinhos){
		int i = 0;
		this.avalia(vizinhos.get(0));
		while(vizinhos.get(i).getCusto() > this.custoRestricao){
			i++;
			if(i >= vizinhos.size()) return null;//caso nehum vizinho tenha custo menor que a restricao
			this.avalia(vizinhos.get(i));
		}
		Solucao melhorVizinho = vizinhos.get(i);
		for (int j = i + 1; j < vizinhos.size(); j++) {
			this.avalia(vizinhos.get(i));
			if ((vizinhos.get(j-1).getSatisfacao() < vizinhos.get(j).getSatisfacao()) && 
					(vizinhos.get(j).getCusto() < this.custoRestricao)) {
				melhorVizinho = vizinhos.get(j);
			}
		}		
		return melhorVizinho;
	}
	
	public Solucao melhorSolucaoComRestricao(int numIteracoes, int numVizinhos) {		
		Solucao melhorViznho = null;	
		for (int i = 0; i <= numIteracoes; i++) {
			this.gerarVizinhos(numVizinhos);
			melhorViznho = this.melhorVizinhoComRestricao(this.vizinhos);
			if((melhorViznho != null) && 
			   (this.solucaoAtual.getSatisfacao() < melhorViznho.getSatisfacao())){
				this.solucaoAtual = melhorViznho;
			}else{
				break;
			}
		}		
		return this.solucaoAtual;
	}
	
	public void salvarSolucao() {
		try {
			FileWriter f1 = new FileWriter("Resultados/Solucao.txt");
			FileWriter f2 = new FileWriter("Resultados/Satisfacao e Custo.txt");
			
			PrintWriter p1 = new PrintWriter(f1);
			PrintWriter p2 = new PrintWriter(f2);
			
			
			for (int i = 0; i < this.getSolucaoAtual().getSolucao().length; i++) {				
				p1.print(this.getSolucaoAtual().getSolucao()[i] + " ");
			}
				
			p2.println(this.getSolucaoAtual().getSatisfacao());
			p2.println(this.getSolucaoAtual().getCusto());					
			
			f1.close();
			f2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NRP getNrp(){
		return this.nrp;
	}
	
	public int getCustoRestricao(){
		return this.custoRestricao;
	}
	
	public ArrayList<Solucao> getVizinhos(){
		return this.vizinhos;
	}
	
	public Solucao getSolucaoAtual(){
		return this.solucaoAtual;
	}
}
