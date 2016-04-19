package PSO;

import java.util.ArrayList;

import NRP.Solucao;

public class ArquivadorMultiSwarm {	
	private ArrayList<Solucao> arquivador;
	
	private static final int TAMANHO_MAXIMO = 10;
	
	public ArquivadorMultiSwarm(){		
		this.arquivador = new ArrayList<>();				
	}
	
	public ArrayList<Solucao> getArquivadorMultiSwarm(){
		return this.arquivador;
	}	
	
	public boolean inserirLider(Solucao solucao){
		boolean sucesso = false;
		if(this.arquivador.size() == 0){
			this.arquivador.add(solucao);			
		}else{
			for(Solucao s : this.arquivador){
				int domina = ArquivadorMultiSwarm.domina(solucao, s);
				switch (domina) {
				case 1:
					this.arquivador.remove(s);
					this.arquivador.add(solucao);
					return true;
				case -1:
					if(ArquivadorMultiSwarm.TAMANHO_MAXIMO == -1 || this.arquivador.size() < ArquivadorMultiSwarm.TAMANHO_MAXIMO){
											
						sucesso = true;
						break;
					}else{
						return false;
					}					
					
				default:
					return false;					
				}
			}
			if(sucesso){
				this.arquivador.add(solucao);
			}
		}
		return true;							
	}
	
	public int getTamanhoMaximo(){ return ArquivadorMultiSwarm.TAMANHO_MAXIMO; }
	
	/**Verifica se s1 domina s2**/
	private static int domina(Solucao s1, Solucao s2){
		if( s1.getCusto() <= s2.getCusto() && 
			s1.getSatisfacao() >= s2.getSatisfacao()){
			return 1;
		}else if(s1.getCusto() > s2.getCusto() && 
				 s1.getSatisfacao() < s2.getSatisfacao()){
			return 0;
		}		
		return -1;
	}
	
	
	public boolean atualizarArquivador(Solucao solucao) {						
		return this.inserirLider(solucao);				
	}
	
	public void copiarAquivador(ArquivadorMultiSwarm arquivador){	
		for(Solucao s : arquivador.getArquivadorMultiSwarm()){
			this.atualizarArquivador(s);
		}		
	}

}
