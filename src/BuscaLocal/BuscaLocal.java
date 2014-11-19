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
	
	public BuscaLocal(NRP nrp){
		this.nrp = nrp;		
		this.vizinhos = new ArrayList<Solucao>();
		this.solucaoAtual = this.nrp.getSolucao();
		this.avalia(this.solucaoAtual);
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
		
		solucao.setCusto(this.nrp.getCustoDosCLientes(c));
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
	
	public Solucao melhorSolucao(int n, int numVizinhos) {		
		Solucao melhorViznho = null;	
		for (int i = 0; i <= n; i++) {
			this.gerarVizinhos(numVizinhos);
			melhorViznho = this.melhorVizinho(this.vizinhos);
			if(this.solucaoAtual.getSatisfacao() < melhorViznho.getSatisfacao()){
				this.solucaoAtual = melhorViznho;
			}else{
				break;
			}
		}		
		return melhorViznho;
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
	
	public ArrayList<Solucao> getVizinhos(){
		return this.vizinhos;
	}
	
	public Solucao getSolucaoAtual(){
		return this.solucaoAtual;
	}
}
