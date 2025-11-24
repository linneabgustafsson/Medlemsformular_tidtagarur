package com.linnea;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {

    private int time = 0;
    private Label showTimeLabel;
    private boolean timerIsTicking;

    public Timer() {
    }

    //Variabelnamn
    public Timer (Label showTimeLabel) {
        this.showTimeLabel = showTimeLabel;
    }

    public void startTimer() {
        timerIsTicking = true;


        //Varför inte implements Runnable, jo pga lambdan. Om inte lambda - implementera runnable
        Thread worker = new Thread(() -> {

            while (timerIsTicking) {

                //VARIABELNAMN
                LocalTime tidvisare = LocalTime.ofSecondOfDay(time);
                DateTimeFormatter formatteradTidvisare = DateTimeFormatter.ofPattern("HH:mm:ss");

                //Om inte lambda...
                Platform.runLater(() -> showTimeLabel.setText(formatteradTidvisare.format(tidvisare)));
                System.out.println(tidvisare);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }

                time++;
            }
        });

        //Vad är det här? Verkar fungera även utan.
        worker.setDaemon(true);
        worker.start();
    }

    public void clearTimer() {
        timerIsTicking = false;
        showTimeLabel.setText("");
        time = 0;
        System.out.println("I metoden clearTimer");     //TA BORT RAD SEN
        System.out.println(time);                       //TA BORT RAD SEN
    }

    public void stopTimer() {
        timerIsTicking = false;
        System.out.println("I metoden stopTimer");      //TA BORT RAD SEN
    }



    //DEN TIDIGARE VERSIONEN


//    public void startTimer() {
//        timerTicking = true;
//
//        //Varför inte implements Runnable?
//        Thread worker = new Thread(() -> {
//
//            //VARIABELNAMN
//            int time = counter.incrementAndGet();
//
//            while (timerTicking) {
//
//                LocalTime tidvisare = LocalTime.ofSecondOfDay(time);
//                DateTimeFormatter formatteradTidvisare = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//                //Om inte lambda?
//                Platform.runLater(() -> showTimeLabel.setText(formatteradTidvisare.format(tidvisare)));
//                System.out.println(tidvisare);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ignored) {
//                }
//
//                time++;
//            }
//        });
//
//        //Vad är det här? Verkar fungera även utan.
//        worker.setDaemon(true);
//        worker.start();
//    }
//
//    public void clearTimer() {
//        timerTicking = false;
//        showTimeLabel.setText("");
//
//        //Måste ju finnas annat betydligt smartare sätt än att göra så här.
//        counter = new AtomicInteger(-1);
//        System.out.println("I metoden clearTimer");
//        System.out.println(counter);
//    }
//
//    //Vissa ggr funkar det men andra gånger så backar den
//    //olika antal sekunder. FIXA!
//    public void stopTimer() {
//        timerTicking = false;
//        System.out.println("I metoden stopTimer");
//    }





}
