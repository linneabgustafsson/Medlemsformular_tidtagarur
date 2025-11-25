package com.linnea;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {

    private int count = 0;
    private Label showTimeLabel;
    private boolean timerIsTicking;

    public Timer() {
    }

    public Timer (Label showTimeLabel) {
        this.showTimeLabel = showTimeLabel;
    }

    public void startTimer() {
        timerIsTicking = true;

        Thread worker = new Thread(() -> {

            while (timerIsTicking) {

                LocalTime time = LocalTime.ofSecondOfDay(count);
                DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss");

                Platform.runLater(() -> showTimeLabel.setText(formattedTime.format(time)));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }

                count++;
            }
        });

        worker.setDaemon(true);
        worker.start();
    }

    public void clearTimer() {
        timerIsTicking = false;
        showTimeLabel.setText("");
        count = 0;
    }

    public void stopTimer() {
        timerIsTicking = false;
    }
}
