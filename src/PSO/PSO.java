package PSO;

import NRP.NRP;

public class PSO {
	
	private int tamPopulacao;
	private Particula[] p;				

	public PSO(NRP nrp, int numPopulacao, int dimensao){	
		this.tamPopulacao = numPopulacao;		
		this.p = new Particula[numPopulacao];
		
		this.criarParticulas(nrp, dimensao);					
	}
	
	private void criarParticulas(NRP nrp, int dimensao){
		this.p[0] = new Particula(nrp, dimensao);
		int melhorPos = 0;
		for (int i = 1; i < this.p.length; i++){
			this.p[i] = new Particula(nrp, dimensao);								
			if(this.p[melhorPos].getPosicao().getSatisfacao() < this.p[i].getPosicao().getSatisfacao())
				melhorPos = i;							
		}				
	}
	
	public void avaliarParticulas(int criterioParada){
		for (int j = 0; j < criterioParada; j++)
			for (int i = 0; i < this.p.length; i++) {
				p[i].atualizarPosicao();				
				p[i].atualizarLideres();			
			}											
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
