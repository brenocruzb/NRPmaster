package PSO;

import java.util.ArrayList;

import NRP.Cliente;
import NRP.NRP;
import NRP.Solucao;

public class Particula {
	
	private Solucao pBest;	
	
	private double[] velocidade;	
	private Solucao posicao;	
	private int dimensao;		
	private NRP nrp;
	
	private static Solucao gBest;
	
	private static final double V_MAX = 4.0;
	private static final double V_MIN = -4.0;
	
	public Particula(NRP nrp, int dimensao){
		this.dimensao = dimensao;
		this.nrp = nrp;
				
		this.posicao 	= new Solucao(nrp.getClientesTotal());
		this.pBest 		= new Solucao(nrp.getClientesTotal());
		Particula.setgBest(new Solucao(nrp.getClientesTotal()));
		this.velocidade = new double[nrp.getClientesTotal()];
		for(int i = 0; i < this.velocidade.length; i++) 
			this.velocidade[i] = 0;

		this.gerarSolucao();
		
		for(int i = 0; i < this.posicao.tamanho(); i++)
			this.pBest.getSolucao()[i] = this.posicao.getSolucao()[i];
	}
	
	//Gera uma solucao randomica, caso passe da dimensao, o avalia corrige
	private void gerarSolucao(){
		for(int i = 0; i < this.posicao.tamanho(); i++)
			this.posicao.getSolucao()[i] = (Math.random() <= 0.5) ? 0 : 1;
		this.avalia(this.posicao);
	}
	
	public void avalia(Solucao solucao){
		aval(solucao);				
		while (solucao.getCusto() > this.dimensao){			
			int pos = (int) (solucao.tamanho() * Math.random());
			if(solucao.getSolucao()[pos] == 1){
				solucao.getSolucao()[pos] = 0;				
				aval(solucao);				
			}
		}
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
		double phi1 = Math.random() * V_MAX;
		double phi2 = Math.random() * V_MAX;
		for (int i = 0; i < this.velocidade.length-1; i++) {			
			double soma1 = phi1 * (pBest.getSolucao()[i] - this.posicao.getSolucao()[i]);
			double soma2 = phi2 * (getgBest().getSolucao()[i] - this.posicao.getSolucao()[i]);
			
			if((this.velocidade[i] + soma1 + soma2) > V_MAX) 
				this.velocidade[i+1] = V_MAX;
			
			else if ((this.velocidade[i] + soma1 + soma2) < V_MIN) 
				this.velocidade[i+1] = V_MIN;
			
			else 
				this.velocidade[i+1] = this.velocidade[i] + soma1 + soma2;
		}		
	}
	
	public void atualizarLideres(){
		avalia(this.pBest);
		if(this.posicao.getSatisfacao() > this.pBest.getSatisfacao())
			for(int i = 0; i < this.posicao.tamanho(); i++)
				this.pBest.getSolucao()[i] = this.posicao.getSolucao()[i];
				
		avalia(Particula.getgBest());
		if(this.posicao.getSatisfacao() > Particula.getgBest().getSatisfacao())
			for(int i = 0; i < this.posicao.tamanho(); i++)
				Particula.getgBest().getSolucao()[i] = this.posicao.getSolucao()[i];
	}
	
	private void aval(Solucao solucao){
		ArrayList<Cliente> c = new ArrayList<>();
		for (int i = 0; i < solucao.tamanho(); i++) {
			if(solucao.getSolucao()[i] == 1){
				c.add(nrp.getClientes().get(i));
			}
		}
		
		solucao.setCusto(nrp.getCustoDosClientes(c));
		solucao.setSatisfacao(nrp.getSatisfacaoDosClientes(c));	
	}
	
	private double sigmoid(double velocidade){
		return 1/(1 + Math.exp(-velocidade));
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

	public int getDimensao() {
		return dimensao;
	}

	public void setDimensao(int dimensao) {
		this.dimensao = dimensao;
	}

	public static Solucao getgBest() {
		return gBest;
	}

	public static void setgBest(Solucao gBest) {
		Particula.gBest = gBest;
	}
	
// Gera uma solucao dentro da dimensao	
//	private void gerarSolucao(){					
//	
//	int[] solu = new int[this.posicao.tamanho()];
//	
//	for(int i = 0; i < this.posicao.tamanho(); i++) solu[i] = 0;
//	this.posicao.setSolucao(solu);
//	
//	for(int i = 0; i < this.posicao.tamanho(); i++){
//		
//		if(this.posicao.getCusto() < this.dimensao){
//			solu[i] = (Math.random() <= 0.5) ? 0 : 1;
//			if(solu[i] == 1){
//				this.posicao.setSolucao(solu);
//				this.avalia();
//			}
//			
//			if(this.posicao.getCusto() > this.dimensao){
//				solu[i] = 0;
//				this.posicao.setSolucao(solu);
//				this.avalia();
//			}
//		}else break;										
//	}
//	
//}

}
