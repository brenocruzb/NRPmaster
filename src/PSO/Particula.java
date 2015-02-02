package PSO;

import java.util.ArrayList;

import NRP.Cliente;
import NRP.NRP;
import NRP.Solucao;

public class Particula {
	
	public static Solucao gBest;
	private Solucao pBest;	
	
	private double[] velocidade;	
	private Solucao posicao;
	
	private NRP nrp;
	
	public Particula(int dimensao){
		this.posicao = new Solucao(nrp.getClientesTotal());
		this.velocidade = new double[nrp.getClientesTotal()];

		this.gerarSolucao(dimensao);
		this.pBest = this.posicao; 
	}
	
	private void gerarSolucao(int dimensao){					
		
		int[] solu = new int[this.posicao.tamanho()];
		
		for(int i = 0; i < this.posicao.tamanho(); i++) solu[i] = 0;
		this.posicao.setSolucao(solu);
		
		for(int i = 0; i < this.posicao.tamanho(); i++){
			
			if(this.posicao.getCusto() < dimensao){
				solu[i] = (Math.random() <= 0.5) ? 0 : 1;
				if(solu[i] == 1){
					this.posicao.setSolucao(solu);
					this.avalia();
				}
				
				if(this.posicao.getCusto() > dimensao){
					solu[i] = 0;
					this.posicao.setSolucao(solu);
					this.avalia();
				}
			}else break;										
		}
		
	}
		
	public void avalia(){
		
		ArrayList<Cliente> c = new ArrayList<>();
		for (int i = 0; i < this.posicao.getSolucao().length; i++) {
			if(this.posicao.getSolucao()[i] == 1){
				c.add(this.nrp.getClientes().get(i));
			}
		}
		
		this.posicao.setCusto(this.nrp.getCustoDosClientes(c));
		this.posicao.setSatisfacao(this.nrp.getSatisfacaoDosClientes(c));			
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

	public NRP getNrp() {
		return nrp;
	}

	public void setNrp(NRP nrp) {
		this.nrp = nrp;
	}
}
