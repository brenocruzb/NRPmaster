package PSO;

import NRP.NRP;
import NRP.Solucao;

public class PSO {
	
	private int tamPopulacao;
	private Particula[] p;		
	protected Solucao gBest;

	public PSO(NRP nrp, int numPopulacao, int dimensao){	
		this.tamPopulacao = numPopulacao;		
		this.p = new Particula[numPopulacao];
		this.gBest = new Solucao(nrp.getClientesTotal());
		
		this.criarParticulas(nrp, dimensao);					
	}
	
	private void criarParticulas(NRP nrp, int dimensao){
		
		for (int i = 0; i < this.p.length; i++)
			this.p[i] = new Particula(nrp, dimensao);											
	}
	
	public void avaliarParticulas(int criterioParada){
		Particula.getgBest().Clone(this.gBest);
				
		for (int j = 0; j < criterioParada; j++)
			for (int i = 0; i < this.p.length; i++) {
				p[i].atualizarPosicao();				
				p[i].atualizarLideres();			
			}
		
		this.gBest.Clone(Particula.getgBest());		
	}	
	
	//Get Set

	public int getTamPopulacao() {
		return tamPopulacao;
	}

	public void setTamPopulacao(int tamPopulacao) {
		this.tamPopulacao = tamPopulacao;
	}
	
	public Particula[] getP() {
		return p;
	}

	public void setP(Particula[] p) {
		this.p = p;
	}

}
