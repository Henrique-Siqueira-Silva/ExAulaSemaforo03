package controller;

import java.util.concurrent.Semaphore;

public class SemaforoEx01 extends Thread {

	private int id;
	private Semaphore semaforo;
	private static int tamanho = 2000;
	private static boolean tocha = true;
	private static boolean Rocha = true;
	private static int[] escolha = new int [] {0,0,0,0};
	private int posicao = 1;
	private int pescolida;

	public SemaforoEx01(int idcarro, Semaphore permissao) {
		id = idcarro;
		semaforo = permissao;
	}

	@Override
	public void run() {
		
		
		while (tamanho > 0) {
			tamanho = Corrida(tamanho);
			if (tamanho < 1500 && tocha) {
				try {
					semaforo.acquire();
					PegaTocha();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}

			if (tamanho < 500 && Rocha) {
				try {
					semaforo.acquire();
					PegaRocha();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}

		}
		try {
			semaforo.acquire();
			Termino();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void Termino() {
		System.out.println("O jogador " + id + " Chegou em " + posicao);
		posicao++;

		try {
			semaforo.acquire();
			Porta();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		if (pescolida == 2) {
			System.out.println("O cavaleiro "+id+ " sobrevivel");
		} else {
			System.out.println("O cavaleirto " +id+ " encontrou um monstro, e MORREU ...");
		} 
		
	}

	private void Porta() {
		boolean foi = false;
		while (!foi) {
			int porta = (int) Math.random() * 4 + 0;
			if (escolha[porta] > 0) {

			} else {
				foi = true;
				escolha[porta] = 1;
				pescolida = porta;
			}
		}
		
	}

	private void PegaRocha() {
		if (Rocha && !tocha) {
				System.out.println("O corredor " + id + " Pegou a Rocha");
				Rocha = false;
		} else {
			System.out.println("Já pegaram a Rocha");
		}
	}

	private void PegaTocha() {

		if (tocha) {
			System.out.println("O corredor " + id + " Pegou a Tocha");
			tocha = false;
		} else {
			System.out.println("Já pegaram a tocha");
		}

	}

	public int Corrida(int tamanho) {
		int corrida = (int) Math.random() * 2 + 2;
		if (tocha) {
			corrida += 2;
		}
		if (Rocha) {
			corrida += 2;
		}

		tamanho = tamanho - corrida;
		try {
			sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return tamanho;
	}

}
