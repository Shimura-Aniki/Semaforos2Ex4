package controller;

import java.util.concurrent.Semaphore;

public class ThreadOperacoes extends Thread {
	private int operacao;
	private Semaphore semaforo;
	private int tipoOperacao;

	public ThreadOperacoes(int operacao, int tipoOperacao, Semaphore semaforo) {
		this.operacao = operacao;
		this.semaforo = semaforo;
		this.tipoOperacao = tipoOperacao;
	}
	
	@Override
	public void run() {
		processamento(tipoOperacao);
	}

	private void processamento(int tipoOperacao) {
		int conta = (int)((Math.random()*998)+1);
		int saldoInicial = (int)((Math.random()*9999)+1);
		if(tipoOperacao == 0){
			try {
				semaforo.acquire();
				saque(saldoInicial, conta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		} else {
			try {
				semaforo.acquire();
				deposito(saldoInicial, conta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}

		}
	}
	
	public void saque(int saldoInicial, int conta) {
		int valorSaque = (int)((Math.random()*9999)+1);
		System.out.println("O cliente #" + operacao + " tem na conta " + conta + " o saldo de R$" + saldoInicial + " e está sacando R$" + valorSaque);
		int saldoFinal = saldoInicial - valorSaque;
		System.out.println("O cliente #" + operacao + " tem agora o saldo de R$" + saldoFinal);
	}
	
	public void deposito(int saldoInicial, int conta){
		int valorDeposito = (int)((Math.random()*9999)+1);
		System.out.println("O cliente #" + operacao + " tem na conta " + conta + " o saldo de R$" + saldoInicial + " e está depositando R$" + valorDeposito);
		int saldoFinal = saldoInicial + valorDeposito;
		System.out.println("O cliente #" + operacao + " tem agora o saldo de R$" + saldoFinal);
	}

}
