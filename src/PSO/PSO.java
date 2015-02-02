package PSO;

public class PSO {
	
	private int tamPopulacao;
	private Particula[] p;
	
//	private static final double V_MAX = 4.0;
//	private static final double V_MIN = -4.0;
	
	public PSO(int numPopulacao, int dimensao){
		this.tamPopulacao = numPopulacao;		
		this.p = new Particula[numPopulacao];
		
		this.criarParticulas(dimensao);					
	}
	
	private void criarParticulas(int dimensao){
		this.p[0] = new Particula(dimensao);
		for (int i = 1; i < this.p.length; i++){
			this.p[i] = new Particula(dimensao);
			
			if(this.p[i-1].getPosicao().getSatisfacao() < this.p[i].getPosicao().getSatisfacao())
				Particula.gBest = this.p[i].getPosicao();
			else
				Particula.gBest = this.p[i-1].getPosicao();
		}
	}
	
	public void avaliarParticulas(int criterioParada){
		
		for (int i = 0; i < this.p.length; i++) {
			this.atualizarPosicao(this.p[i]);
			this.atualizarLideres(this.p[i]);									
		}						
	}
	
	private double sigmoid(double velocidade){
		return 1/(1 + Math.exp(-velocidade));
	}
	
	private void atualizarPosicao(Particula p){
		this.calcularVelocidade(p);	
		for (int i = 0; i < this.p.length; i++) {
			double s = this.sigmoid(p.getVelocidade()[i]);
			if(Math.random() < s)
				p.getPosicao().getSolucao()[i] = 1;
			else
				p.getPosicao().getSolucao()[i] = 0;										
		}
		
		p.avalia();
	}
	
	
	private void calcularVelocidade(Particula p){
		
	}
	
	private void atualizarLideres(Particula p){
		if(p.getPosicao().getSatisfacao() > p.getpBest().getSatisfacao())
			p.setpBest(p.getPosicao());
		
		if(p.getPosicao().getSatisfacao() > Particula.gBest.getSatisfacao())
			Particula.gBest = p.getPosicao();
	}
	
	//Get Set

	public int getTamPopulacao() {
		return tamPopulacao;
	}

	public void setTamPopulacao(int tamPopulacao) {
		this.tamPopulacao = tamPopulacao;
	}

}
