package com.linnea;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Timer {

    //private final AtomicInteger counter = new AtomicInteger(0);
    private AtomicInteger counter = new AtomicInteger(0);

    private Label rutaVisaTidiTimer;
    private boolean timerTicking;

    public Timer() {
    }

    //Variabelnamn
    public Timer (Label rutaVisaTidiTimer) {
        this.rutaVisaTidiTimer = rutaVisaTidiTimer;
    }

    public void startTimer() {
        timerTicking = true;

        Thread worker = new Thread(() -> {

            int time = counter.incrementAndGet();

            while (timerTicking) {

                LocalTime tidvisare = LocalTime.ofSecondOfDay(time);
                DateTimeFormatter formatteradTidvisare = DateTimeFormatter.ofPattern("HH:mm:ss");

                Platform.runLater(() -> rutaVisaTidiTimer.setText(formatteradTidvisare.format(tidvisare)));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }

                time++;
            }
        });

        worker.setDaemon(true);
        worker.start();
    }

    public void clearTimer() {
        timerTicking = false;
        rutaVisaTidiTimer.setText("");

        //Måste ju finnas annat betydligt smartare sätt än att göra så här.
        counter = new AtomicInteger(-1);
    }

    //Vissa ggr funkar det men andra gånger så backar den
    //olika antal sekunder. FIXA!
    public void stopTimer() {
        timerTicking = false;
    }
}
