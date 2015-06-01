package PSO;

import java.util.ArrayList;
import java.util.Collections;

import NRP.Cliente;
import NRP.NRP;
import NRP.Solucao;

public class Particula {
	
	private Solucao pBest;	
	private static Solucao gBest;	
	
	private double[] velocidade;	
	private Solucao posicao;	
	private int dimensao;		
	private NRP nrp;		
	
	private static final double V_MIN = -4.0;
	private static final double V_MAX = 4.0;
	
	public Particula(NRP nrp, int dimensao){
		//Apontadores
		this.dimensao = dimensao;
		this.nrp = nrp;
				
		//Objetos
		this.posicao 	= new Solucao(nrp.getClientesTotal());
		this.pBest 		= new Solucao(nrp.getClientesTotal());
		Particula.gBest = new Solucao(nrp.getClientesTotal());
		this.velocidade = new double[nrp.getClientesTotal()];
		for(int i = 0; i < this.velocidade.length; i++) 
			this.velocidade[i] = 0;
		
		this.gerarSolucao();
				
		this.pBest.Clone(posicao);
		Particula.getgBest().Clone(posicao);
	}
	
	//Gera uma solucao randomica que não ultrapassa a restrição
	private void gerarSolucao(){
		ArrayList<Cliente> c = new ArrayList<>();
		while(true){
			int posRand = (int) (Math.random() * this.posicao.tamanho());
			if(this.posicao.getSolucao()[posRand] == 0){
				c.add(nrp.getClientes().get(posRand));//lista de clientes ativos
				int custo = nrp.getCustoDosClientes(c);
				
				if(custo <= this.dimensao){
					this.posicao.getSolucao()[posRand] = 1;
					this.posicao.setCusto(custo);
				}else{
					break;
				}		
			}			
		}								
			
		this.avalia(this.posicao);
	}		
	
	/**A lista de Clientes c, irá conter todos os clientes ativos (1) da solucao, após isso, 
	 * a solucao irá receber o custo e a satisfacao baseada nessa lista**/
	public void avalia(Solucao solucao){
		ArrayList<Cliente> c = new ArrayList<>();
		ArrayList<Cliente> cNaoInfluentes = new ArrayList<>();		
		Cliente aux = new Cliente(0,0);		
		
		//Adicionando os clientes ativos à lista de clientes c
		for (int i = 0; i < solucao.tamanho(); i++) 
			if(solucao.getSolucao()[i] == 1)
				c.add(nrp.getClientes().get(i));
				
		this.ordenarPorCusto(c);
		
		solucao.setCusto(nrp.getCustoDosClientes(c));
		
		while(solucao.getCusto() > this.dimensao){
			int custoAnterior = nrp.getCustoDosClientes(c);			
			aux.Clone(c.get(0));//Segura o cliente que vai ser removido
			c.remove(0);		//remove ele da lista de clientes ativos
			int custoAtual = nrp.getCustoDosClientes(c);
			
			if(custoAtual < custoAnterior){//Retira apenas clientes que reduzam o custo		
				solucao.getSolucao()[aux.getId()] = 0;//retira esse cliente da solucao temporaria atual											
				solucao.setCusto(custoAtual);//recalcula o custo
				if(!cNaoInfluentes.isEmpty()){
					c.addAll(cNaoInfluentes);//Adiciona todos os clientes sem influencia devolta, para serem avaliados novamente.
					this.ordenarPorCusto(c);
					cNaoInfluentes.clear();//Limpa a lista de clientes sem influencia
				}
				
			}else{				
				Cliente cliente = new Cliente(0,0);
				cliente.Clone(aux);
				cNaoInfluentes.add(cliente);//Armazena os clientes que não influencia no custo								
			}						
		}													
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
		double phi1 = (Math.random() * V_MAX);
		double phi2 = (Math.random() * V_MAX);
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
		
		if(this.posicao.getSatisfacao() > this.pBest.getSatisfacao())
			this.pBest.Clone(posicao);

		if(this.posicao.getSatisfacao() > Particula.getgBest().getSatisfacao())
			Particula.gBest.Clone(posicao);			
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
