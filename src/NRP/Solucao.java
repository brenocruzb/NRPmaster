package NRP;

public class Solucao {
	private int[] solucao;
	
	private int[] resultado;
	private int custo;
	private int satisfacao;
	private int numElementos;
	NRP a;

	public Solucao(int n){
		this.numElementos = n;
		this.solucao = new int[n];
		this.resultado = new int[2];
		this.gerarSolucao();
	}
	
	private void gerarSolucao(){
		for(int i = 0; i < this.solucao.length; i++){
			
//			if(this.getSatisfacao() < NRP.custoTotal){
				this.solucao[i] = (Math.random() <= 0.5) ? 0 : 1;
//				avalia
				
//				if(this.getSatisfacao() > NRP.custoTotal){
//					this.solucao[i] = 0;
////				avalia
//				}
//			}else{
//				this.solucao[i] = 0;
//			}								
		}
	}
	
	//Gera uma nova solucao baseada na atual
	public Solucao alterarBit(){
		int posicao = (int) (this.numElementos * Math.random());
		Solucao novaSolucao = new Solucao(this.numElementos);
		for (int i = 0; i < novaSolucao.solucao.length; i++) {
			novaSolucao.solucao[i] = this.solucao[i]; 
		}
		
		novaSolucao.solucao[posicao] = (this.solucao[posicao] == 1) ? 0 : 1;		 
		return novaSolucao;
	}
	
	public int[] getResultado(){
		this.resultado[0] = this.custo;
		this.resultado[1] = this.satisfacao;
		return this.resultado;
	}
	
	public int getCusto() {
		return custo;
	}
	
	public void setCusto(int custo) {
		this.custo = custo;
	}
	
	public int getSatisfacao(){
		return this.satisfacao;
	}
	
	public int getNumElementos() {
		return numElementos;
	}
	
	public void setNumElementos(int numElementos) {
		this.numElementos = numElementos;
	}
	
	public void setSatisfacao(int satisfacao){
		this.satisfacao = satisfacao;
	}
	
	public int[] getSolucao(){
		return this.solucao;
	}
	
	public void setSolucao(int[] solucao) {
		this.solucao = solucao;
	}
}
