package com.linnea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Main extends Application {

    //De här är från Tomas exempel
    private final AtomicInteger counter = new AtomicInteger(0);
    //private Label label;


    private List<Member> memberList = new ArrayList<>();


    @Override
    public void start(Stage stage) {

        stage.setTitle("HUVUDMENY");

        Button memberButton = new Button();
        memberButton.setText("Medlemsformulär");
        memberButton.setPrefSize(230, 100);
        //Nedan är lambda, kolla upp vad exakt som görs i bakgrunden.
        memberButton.setOnAction(e -> memberFormSidan(stage));

        Button timerButton = new Button();
        timerButton.setText("Tidtagarur");
        timerButton.setPrefSize(230, 100);
        timerButton.setOnAction(e -> timerSidan(stage));

        TextField textField = new TextField();
        textField.setText("Välj vad du vill göra");
        textField.setPrefSize(50, 50);
        textField.setStyle("-fx-background-color: #e8bdf1;");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(memberButton, timerButton);
        hBox.setSpacing(60);
        hBox.setStyle("-fx-background-color: #fff7cb; -fx-padding: 60");

        Scene scene = new Scene(hBox, 600, 300);
        stage.setScene(scene);
        stage.show();

//        //Får inte till det med Color som argument.
//        Scene scene = new Scene(stackPane, 400, 400);
//        stage.setScene(scene);
//        stage.show();

    }

    //Ha medlemssidan som en egen klass?
    public void memberFormSidan(Stage stage) {

        stage.setTitle("MEDLEMSFORMULÄR");

        Button toMenuButton = new Button();
        toMenuButton.setText("Till huvudmeny");
        toMenuButton.setPrefSize(100, 40);
        toMenuButton.setStyle("-fx-font-size: 11");
        //Nedan är lambda, kolla upp vad exakt som görs i bakgrunden.
        toMenuButton.setOnAction(e -> start(stage));

        Label infoLabel = new Label("Fyll i uppgifter om medlem");
        infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label firstNameLabel = new Label("Förnamn ");
        firstNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label lastNameLabel = new Label("Efternamn ");
        lastNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label phoneNrLabel = new Label("Telefonnummer ");
        phoneNrLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label addressLabel1 = new Label("Adress ");
        addressLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Label addressLabel2 = new Label("Postnummer och ort ");
        addressLabel2.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField phoneNrField = new TextField();
        TextField addressField1 = new TextField();
        TextField addressField2 = new TextField();

        Label utskriftSparaLabel = new Label();     //BYT NAMN!


        Label printMemberLabel = new Label();
        printMemberLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Button saveButton = new Button();
        saveButton.setText("Spara");
        saveButton.setPrefSize(100, 40);
        saveButton.setOnAction(e -> {

            Member member = new Member(firstNameField.getText(), lastNameField.getText(),
                    phoneNrField.getText(), addressField1.getText(), addressField2.getText());

            memberList.add(member);

            utskriftSparaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            utskriftSparaLabel.setText("Följande uppgifter är sparade:");

            //Om i konsolen och använder System.out så fattar alltså att ska anropa toString
            //men inte om så här?
            printMemberLabel.setText(member.toString());

            //HÄR VILL JAG OCKSÅ RENSA FÄLTET
            //Och sen kunna lägga till ny medlem

        });

        //Sen när tryckt på Spara ska det bli utskrift "Sparad blabla"
        //i antingen textfält/ruta eller label längst ner i fönstret.


//        //Komma till nytt fönster?
//        Button printAllButton = new Button();
//        printButton.setText("Skriv ut alla medlemmar");
//        printButton.setPrefSize(100, 40);
//        for (Member m : memberList)  {
//            System.out.println(member);
//        }

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);       //avstånd mellan kolumner
        gridPane.setVgap(10);       //avstånd mellan rader
        gridPane.add(infoLabel, 0, 0);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 0, 3);
        gridPane.add(lastNameLabel, 0, 4);
        gridPane.add(lastNameField, 0, 5);
        gridPane.add(phoneNrLabel, 0, 6);
        gridPane.add(phoneNrField, 0, 7);
        gridPane.add(addressLabel1, 0, 8);
        gridPane.add(addressField1, 0, 9);
        gridPane.add(addressLabel2, 0, 10);
        gridPane.add(addressField2, 0, 11);
        gridPane.add(saveButton, 0, 14);
        gridPane.add(utskriftSparaLabel, 0, 17);
        gridPane.add(printMemberLabel, 0, 18);

        gridPane.add(toMenuButton, 18, 20);

        //HA EN BORDERPANE HÄR? Fylla den gridPane till vänster
        // och andra grejer till höger och sen skicka den till scenen?

        Scene scene = new Scene(gridPane, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public void addMember(Member member) {

        //Lägga själva addandet här?

    }

    public void timerSidan(Stage stage) {

        stage.setTitle("TIDTAGARUR");
        Label label = new Label("En label som ska visa tiden");
        //T.ex. timme:minut:sekund


        Button startTimer = new Button();
        startTimer.setText("Start");
        startTimer.setOnAction(e -> timer());

        Button stopTimer = new Button();
        stopTimer.setText("Stopp");
        //stopTimer.setOnAction    kod som stannar

        Button clearTimer = new Button();
        clearTimer.setText("Nollställ");
        //clearTimer.setOnAction    kod som rensar allt


        Button returnToMenu = new Button();
        returnToMenu.setText("Till huvudmenyn");
        returnToMenu.setPrefSize(80, 70);
        returnToMenu.setStyle("-fx-font-size: 11");
        //Nedan är lambda, kolla upp vad exakt som görs i bakgrunden.
        returnToMenu.setOnAction(e -> start(stage));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(label, startTimer, stopTimer, returnToMenu);
        hBox.setStyle("-fx-background-color: #f0d7f1; -fx-padding: 60");

        Scene scene = new Scene(hBox, 600, 300);
        stage.setScene(scene);
        stage.show();

    }

    //Nåt i den här stilen...
    public void timer() {
        Thread worker = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                int value = counter.incrementAndGet();
                //Platform.runLater(() -> label.setText("Räkning: " + value));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
            }
        });

        worker.setDaemon(true);
        worker.start();
    }



    public static void main(String[] args) {
        launch(args);
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
//}


