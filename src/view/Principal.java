package view;

import java.util.concurrent.Semaphore;

import controller.ThreadOperacoes;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);

		for(int operacao = 1; operacao < 21; operacao++){
			int tipoOperacao = (int)(Math.random()*2);
			ThreadOperacoes tOperacoes = new ThreadOperacoes(operacao, tipoOperacao, semaforo);
			tOperacoes.start();
		}
	}

}
