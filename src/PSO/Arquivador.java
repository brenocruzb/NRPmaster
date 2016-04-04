package PSO;

import java.util.ArrayList;

import NRP.Solucao;

//Singleton Model
public class Arquivador {
	
	private static Arquivador instance;
	private static ArrayList<Solucao> arquivador;
	
	private static final int TAMANHO_MAXIMO = 10;
	
	public static Arquivador getInstance(){
		if(Arquivador.instance == null){
			Arquivador.arquivador = new ArrayList<>();
			Arquivador.instance = new Arquivador();			
		}
		
		return Arquivador.instance;				
	}
	
	public ArrayList<Solucao> getArquivador(){
		return Arquivador.arquivador;
	}
	
	public void setArquivador(ArrayList<Solucao> arquivador){
		Arquivador.arquivador = arquivador;
	}
	
	public boolean inserirLider(Solucao solucao){
		if(Arquivador.arquivador.size() < Arquivador.TAMANHO_MAXIMO){
			Arquivador.arquivador.add(solucao);
			return true;
		}		
		return false;		
	}
	
	public int getTamanhoMaximo(){
		return Arquivador.TAMANHO_MAXIMO;
	}
	
	private int domina(Solucao s1, Solucao s2){
		if( s1.getCusto() <= s2.getCusto() && 
			s1.getSatisfacao() >= s2.getSatisfacao()){
			return 1;
		}else if(s1.getCusto() > s2.getCusto() && 
				 s1.getSatisfacao() < s2.getSatisfacao()){
			return 0;
		}
		
		return -1;
	}
}
