package PSO;

import NRP.NRP;

public class PSO {
	
	private int tamPopulacao;
	private Particula[] p;			
	private ArquivadorMultiSwarm arquivador;

	public PSO(NRP nrp, int numPopulacao){	
		this.tamPopulacao = numPopulacao;		
		this.p = new Particula[numPopulacao];		
		this.arquivador = new ArquivadorMultiSwarm();
		
		this.criarParticulas(nrp, this.arquivador);					
	}
	
	private void criarParticulas(NRP nrp, ArquivadorMultiSwarm arquivador){
		
		for (int i = 0; i < this.p.length; i++)
			this.p[i] = new Particula(nrp, arquivador);											
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

	public ArquivadorMultiSwarm getArquivador() {
		return arquivador;
	}	

}
