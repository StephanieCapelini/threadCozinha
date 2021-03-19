package view;

import java.util.concurrent.Semaphore;

import controller.Cozinha;

public class principal {

    public static void main(String[] args) {
    int permissoes = 1;

        Semaphore semaforo = new Semaphore(permissoes);
        for(int id = 1; id <= 5; id++) {
            Thread Cozinha = new Cozinha(id, semaforo);
            Cozinha.start();
        }
    }
}
