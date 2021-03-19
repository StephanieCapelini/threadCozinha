package controller;

import java.util.concurrent.Semaphore;

public class Cozinha extends Thread {
    private String nomePrato;
    private int idPrato, tempoPreparo;
    private Semaphore semaforo;

    public Cozinha(int id, Semaphore semaforo) {
        this.idPrato = id;
        this.semaforo = semaforo;
        this.nomePrato = "";
    }

    @Override
    public void run() {
        if (idPrato % 2 == 0) {
            this.nomePrato = "Lasanha a Bolonhesa";
            this.tempoPreparo = (int) (Math.random() * 601 + 600);

        } else {
            this.nomePrato = "Sopa de Cebola";
            this.tempoPreparo = (int) (Math.random() * 301 + 500);

        }

        cozinharPrato();

        try {
            semaforo.acquire();
            entregarPrato();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }

    }

    private void cozinharPrato () {
        int tempo = 0;
        int por = 0;

        while (tempo < tempoPreparo) {
            tempo += 100;
            por = (int) (((double) tempo/tempoPreparo) * 100);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println((por > 100) ? "O prato " + nomePrato + " #" + idPrato + " já cozinhou 100%." :
                "O prato " + nomePrato + " #" + idPrato + " já cozinhou " + por + "%.");
        }

    }

    private void entregarPrato () {

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("O prato " + nomePrato + " #" + idPrato + " foi entregue.");
    }
}