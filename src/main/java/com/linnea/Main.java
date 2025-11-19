package com.linnea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {

        //Label är innehåll i rutan
        Label label = new Label("Innehåll i rutan");
        Label label1 = new Label("tjing");

        //Scenen är rutan, skickar in label-objekt med innehåll och storlek, antal pixlar.
        Scene scene = new Scene(label, 400, 200);

        stage.setScene(scene);
        stage.setTitle("Rubrik längst uppe");
        stage.show();

//        //En knapp
//        HBox hbox = new HBox();
//        hbox.setSpacing(10);
//        Button enKnapp = new Button("En knapp");
//        enKnapp.setPrefSize(150, 150);
//        hbox.getChildren().add(enKnapp);
//        Scene scene1 = new Scene(hbox, 200, 200);
//        stage.setScene(scene1);

    }

    public static void main(String[] args) {
        launch();
    }
}


//public class Main extends Application {
//
//    private final AtomicInteger counter = new AtomicInteger(0);
//    private Label label;
//
//    @Override
//    public void start(Stage stage)  {
//        label = new Label("Räknare: 0");
//        Button startKnapp = new Button("Starta");
//        startKnapp.setOnAction(e -> startCounting());
//
//        Vbox root = new VBox(10, label, startKnapp);
//        root.setStyle("-fx-padding: 20; -fx-font-size: 16,");
//
//        stage.setScene(new Scene(root, 400, 150));
//        stage.setTitle("Text stage");
//        stage.show();
//    }
//
//    public void startCounting() {
//        Thread worker = new Thread(() -> {
//            for (int i = 1; i <= 10; i++) {
//                int val = counter.incrementAndGet();
//                Platform.runLater(() -> label.setText("Räkning: " + val));
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException ignored) {}
//            }
//        });
//
//        worker.setDaemon(true);
//        worker.start();
//    }
//
//    public static void main(String[] args) {
//        //Detta kör igång JavaFX. Skickar in args i metoden.
//        launch(args);
//    }
//}


