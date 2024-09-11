package view;

import java.util.concurrent.Semaphore;

import controller.SemaforoEx01;

public class Main {

	public static void main(String[] args) {
			int permissao = 1;
			Semaphore semaforo = new Semaphore(permissao);
			
			for ( int i = 0; i < 4; i++) {
				SemaforoEx01 m = new SemaforoEx01(i, semaforo);
				m.start();
			}
		
	}

}