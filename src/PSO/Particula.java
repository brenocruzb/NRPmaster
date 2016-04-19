package PSO;

import java.util.ArrayList;
import java.util.Collections;

import NRP.Cliente;
import NRP.NRP;
import NRP.Solucao;

public class Particula {
	
	private Solucao pBest;		
	
	private double[] velocidade;	
	private Solucao posicao;	
	private NRP nrp;
	private ArquivadorMultiSwarm arquivador;
	
	private static final double V_MIN = -4.0;
	private static final double V_MAX = 4.0;
	
	public Particula(NRP nrp, ArquivadorMultiSwarm arquivador){
		//Apontadores		
		this.nrp = nrp;
				
		//Objetos
		this.posicao 	= new Solucao(nrp.getClientesTotal());
		this.pBest 		= new Solucao(nrp.getClientesTotal());		
		this.velocidade = new double[nrp.getClientesTotal()];
		this.arquivador = arquivador;
		for(int i = 0; i < this.velocidade.length; i++) 
			this.velocidade[i] = 0;
		
		this.gerarSolucao();
				
		this.pBest.Clone(posicao);		
	}
	
	
	private void gerarSolucao(){
		for(int i = 0; i < this.posicao.tamanho(); i++)
			this.posicao.getSolucao()[i] = (Math.random() <= 0.5) ? 0 : 1;
		
		this.avalia(posicao);
		this.arquivador.atualizarArquivador(posicao);
	}
	
	private void avalia(Solucao solucao){
		ArrayList<Cliente> c = new ArrayList<>();
		
		for (int i = 0; i < solucao.tamanho(); i++) 
			if(solucao.getSolucao()[i] == 1)
				c.add(nrp.getClientes().get(i));
		//this.ordenarPorCusto(c);
		
		solucao.setCusto(nrp.getCustoDosClientes(c));
		solucao.setSatisfacao(nrp.getSatisfacaoDosClientes(c));
					
	}	
			
	public void atualizarPosicao(){
		this.calcularVelocidade();	
		for (int i = 0; i < this.posicao.tamanho(); i++) {
			double s = this.sigmoid(this.velocidade[i]);
			this.posicao.getSolucao()[i] = (Math.random() < s) ? 1 : 0; 																
		}	
		
		avalia(this.posicao);		
	}	
		
	public void calcularVelocidade(){		
		ArrayList<Solucao> lista = this.arquivador.getArquivadorMultiSwarm();
		double phi1 = (Math.random() * V_MAX);
		double phi2 = (Math.random() * V_MAX);
		for (int i = 0; i < this.velocidade.length-1; i++) {	
			int posRand = (int) (Math.random() * lista.size());
			Solucao lBest = lista.get(posRand);
			
			double soma1 = phi1 * (pBest.getSolucao()[i] - this.posicao.getSolucao()[i]);
			double soma2 = phi2 * (lBest.getSolucao()[i] - this.posicao.getSolucao()[i]);
			
			if((this.velocidade[i] + soma1 + soma2) > V_MAX) 
				this.velocidade[i+1] = V_MAX;
			
			else if ((this.velocidade[i] + soma1 + soma2) < V_MIN) 
				this.velocidade[i+1] = V_MIN;
			
			else 
				this.velocidade[i+1] = this.velocidade[i] + soma1 + soma2;
		}		
	}
	
	public void atualizarLideres(){
		
		if(this.posicao.getSatisfacao() > this.pBest.getSatisfacao()){
			this.pBest.Clone(posicao);
			this.arquivador.atualizarArquivador(posicao);
		}							
	}		
	
	private double sigmoid(double velocidade){
		return 1/(1 + Math.exp(-velocidade));
	}
	
	private void ordenarPorCusto(ArrayList<Cliente> cliente){
		ArrayList<Integer> custos = new ArrayList<Integer>();
		for(Cliente cl : cliente)
			custos.add(nrp.getCustoDoCliente(cl));
		
		for(int i = 0; i < cliente.size() - 1; i++){
			int maiorCusto = custos.get(i);
			int posicao = i;
			for(int j = i + 1; j < cliente.size(); j++){
				if(custos.get(j) > maiorCusto){
					maiorCusto = custos.get(j);
					posicao = j;
				}
			}
			Collections.swap(custos, i, posicao);
			Collections.swap(cliente, i, posicao);
		}			
	}
	
	//Get Set
	
	public Solucao getpBest() {
		return pBest;
	}

	public void setpBest(Solucao pBest) {
		this.pBest = pBest;
	}

	public Solucao getPosicao() {
		return posicao;
	}

	public void setPosicao(Solucao posicao) {
		this.posicao = posicao;
	}

	public double[] getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double[] velocidade) {
		this.velocidade = velocidade;
	}
}
