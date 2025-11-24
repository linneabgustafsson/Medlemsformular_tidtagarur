package com.linnea;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    //private final AtomicInteger counter = new AtomicInteger(0);Ny klass
    private AtomicInteger counter = new AtomicInteger(0);
    private boolean klockanTickar;

    //Används i båda timer-metoderna
    private Label rutaVisaTid;

    private List<Member> memberList = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        stage.setTitle("HUVUDMENY");

        Label menuLabel = new Label("Välj vad du vill göra");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        menuLabel.setStyle("-fx-padding: 30");

        Button memberButton = new Button();
        memberButton.setText("Medlemsformulär");
        memberButton.setPrefSize(230, 60);
        //Nedan är lambda, kolla upp vad exakt som görs i bakgrunden.
        memberButton.setOnAction(e -> memberForm(stage));

        Button timerButton = new Button();
        timerButton.setText("Tidtagarur");
        timerButton.setPrefSize(230, 60);
        timerButton.setOnAction(e -> timer(stage));

        Button quitButton = new Button();
        quitButton.setText("Avsluta");
        quitButton.setPrefSize(230, 60);
        quitButton.setOnAction(e -> System.exit(0));

        VBox vBox = new VBox();
        vBox.setSpacing(25);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(menuLabel, memberButton, timerButton, quitButton);

        Scene scene = new Scene(vBox, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void memberForm(Stage stage) {

        stage.setTitle("MEDLEMSFORMULÄR");

        Button toMenuButton = new Button();
        toMenuButton.setText("Huvudmeny");
        toMenuButton.setPrefSize(115, 40);
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

        //BYT NAMN!!
        Label utskriftSparaLabel = new Label();
        utskriftSparaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label printMemberLabel = new Label();
        printMemberLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Button saveButton = new Button();
        saveButton.setText("Spara");
        saveButton.setPrefSize(115, 40);
        saveButton.setOnAction(e -> {

            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                    || phoneNrField.getText().isEmpty() || addressField1.getText().isEmpty()
                    || addressField2.getText().isEmpty()) {

                utskriftSparaLabel.setText("Du har inte fyllt i alla fält!");
                utskriftSparaLabel.setStyle("-fx-text-fill: red");
                return;
            }

            Member member = new Member(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    phoneNrField.getText(),
                    addressField1.getText(),
                    addressField2.getText());

            memberList.add(member);

            utskriftSparaLabel.setStyle("-fx-text-fill: black");
            utskriftSparaLabel.setText("Följande uppgifter är sparade:");

            //Om i konsolen och använder System.out så fattar alltså att ska anropa toString
            //men inte om så här.
            printMemberLabel.setText(member.toString());

            firstNameField.clear();
            lastNameField.clear();
            phoneNrField.clear();
            addressField1.clear();
            addressField2.clear();
        });

        Button printAllButton = new Button();
        printAllButton.setText("Medlemsregister");
        printAllButton.setPrefSize(115, 40);
        printAllButton.setOnAction(e -> printMemberRegistry(stage));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-padding: 30");
        gridPane.setAlignment(Pos.TOP_CENTER);
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
        gridPane.add(saveButton, 0, 12);
        gridPane.add(utskriftSparaLabel, 0, 14);
        gridPane.add(printMemberLabel, 0, 15);

        HBox hBox = new HBox();
        hBox.setSpacing(32);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-padding: 30");
        hBox.getChildren().addAll(printAllButton, toMenuButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 450, 750);
        stage.setScene(scene);
        stage.show();
    }

    public void printMemberRegistry(Stage stage)    {

        stage.setTitle("MEDLEMSREGISTER");

        //BYT NAMN VARIABLER
        Label rubrikUtskriftssidaLabel = new Label("Registrerade medlemmar");
        rubrikUtskriftssidaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rubrikUtskriftssidaLabel.setStyle("-fx-padding: 30");
        rubrikUtskriftssidaLabel.setAlignment(Pos.CENTER);

        TextFlow textFlow = new TextFlow();
        textFlow.setLineSpacing(5);

        //VARIABELNAMN
        for (Member member : memberList) {

            Text fnLabel = new Text("Förnamn: ");
            fnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text fnValue = new Text(member.getFirstName() + "\n");
            fnValue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            Text lnLabel = new Text("Efternamn: ");
            lnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text lnValue = new Text(member.getLastName() + "\n");
            lnValue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            Text phoneLabel = new Text("Telefonnummer: ");
            phoneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text phoneValue = new Text(member.getPhoneNumber() + "\n");
            phoneValue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            Text addr1Label = new Text("Adress: ");
            addr1Label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text addr1Value = new Text(member.getAddressLine1() + "\n");

            Text addr2Label = new Text("Postnummer och ort: ");
            addr2Label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text addr2Value = new Text(member.getAddressLine2() + "\n\n");
            addr2Value.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            textFlow.getChildren().addAll(fnLabel, fnValue, lnLabel, lnValue, phoneLabel,
                    phoneValue, addr1Label, addr1Value, addr2Label, addr2Value
            );
        }

        //Skriv hur detta funkar
        ScrollPane scrollPane = new ScrollPane(textFlow);

        Button returnToForm = new Button();
        returnToForm.setText("Medlemsformulär");
        returnToForm.setPrefSize(115, 40);
        returnToForm.setOnAction(e -> memberForm(stage));

        Button returnToMenu = new Button();
        returnToMenu.setText("Huvudmeny");
        returnToMenu.setPrefSize(115, 40);
        returnToMenu.setOnAction(e -> start(stage));

        HBox hBox = new HBox();
        hBox.setSpacing(32);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setStyle("-fx-padding: 30");
        hBox.getChildren().addAll(returnToForm, returnToMenu);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(rubrikUtskriftssidaLabel);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 450, 750);
        stage.setScene(scene);
        stage.show();
    }

    //Vad ska klassen timer heta och vad ska den här sidan heta?
    public void timer(Stage stage) {

        stage.setTitle("TIDTAGARUR");

        Label timeLabel = new Label("Tid: ");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        //Denna label används också i timer-metoden.
        rutaVisaTid = new Label();
        rutaVisaTid.setFont(Font.font("Arial", FontWeight.NORMAL, 24));

        Timer timer = new Timer(rutaVisaTid);

        Button startTimerButton = new Button();
        startTimerButton.setText("Start");
        startTimerButton.setPrefSize(115, 40);
        startTimerButton.setOnAction(e -> timer.startTimer());

        Button stopTimerButton = new Button();
        stopTimerButton.setText("Stopp");
        stopTimerButton.setPrefSize(115, 40);
        stopTimerButton.setOnAction(e -> timer.stopTimer());

        Button clearTimerButton = new Button();
        clearTimerButton.setText("Nollställ");
        clearTimerButton.setPrefSize(115, 40);
        clearTimerButton.setOnAction(e -> timer.clearTimer());

        Button returnToMenu = new Button();
        returnToMenu.setText("Huvudmeny");
        returnToMenu.setPrefSize(115, 40);
        returnToMenu.setOnAction(e -> start(stage));

        HBox hboxTop = new HBox();
        hboxTop.getChildren().addAll(timeLabel, rutaVisaTid);
        hboxTop.setStyle("-fx-padding: 20");
        hboxTop.setAlignment(Pos.BOTTOM_LEFT);

        HBox hBoxCenter = new HBox();
        hBoxCenter.getChildren().addAll(startTimerButton, stopTimerButton, clearTimerButton);
        hBoxCenter.setAlignment(Pos.TOP_CENTER);
        hBoxCenter.setStyle("-fx-padding: 20");
        hBoxCenter.setSpacing(20);

        HBox hBoxBottom = new HBox();
        hBoxBottom.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxBottom.setStyle("-fx-padding: 10");
        hBoxBottom.getChildren().addAll(returnToMenu);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-padding: 30");
        borderPane.setTop(hboxTop);
        borderPane.setCenter(hBoxCenter);
        borderPane.setBottom(hBoxBottom);

        Scene scene = new Scene(borderPane, 450, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//    public void clearTimer()    {
//        klockanTickar = false;
//        rutaVisaTid.setText("");
//
//        //Måste finnas annat sätt än att göra så här?
//        counter = new AtomicInteger(-1);
//
//    }
//
//    //Vissa ggr funkar det men andra gånger så backar den
//    //olika antal sekunder
//    public void stopTimer() {
//        klockanTickar = false;
//    }

//    public void startTimer() {
//
//        klockanTickar = true;
//
//        Thread worker = new Thread(() -> {
//
//            //Nu går den ju till 10 men den ska gå tills trycker på stoppknappen.
//            //Stopp-knappen måste alltså ha en koppling hit, nåt som bryter loopen.
//            //Vad ska då styra i < 10?
//            //Det kanske då inte är en for-loop som ska styra utan ngt annat.
////            for (int i = 1; i <= 10; i++) {
////
////                int time = counter.incrementAndGet();
////
////                //Här ska det då visas i timme:minut:sekund
////                //rutaVisaTid är alltså min label.
////                //Platform.runLater(() -> rutaVisaTid.setText(String.valueOf(time)));
////
////                //LocalTime tidvisare = LocalTime.of(time, 0);
////                LocalTime tidvisare = LocalTime.ofSecondOfDay(time);
////                DateTimeFormatter formatteradTidvisare = DateTimeFormatter.ofPattern("HH:mm:ss");
////                //String formattedDate = tidvisare.format(formatteradTidvisare);
////
////                Platform.runLater(() -> rutaVisaTid.setText(formatteradTidvisare.format(tidvisare)));
////
////                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException ignored) {
////                    }
////            }
//
//            //int time = counter.incrementAndGet();
//            int time = counter.incrementAndGet();
//
//            while (klockanTickar) {
//
//                LocalTime tidvisare = LocalTime.ofSecondOfDay(time);
//                DateTimeFormatter formatteradTidvisare = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//                Platform.runLater(() -> rutaVisaTid.setText(formatteradTidvisare.format(tidvisare)));
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ignored) {
//                    }
//
//                time++;
//            }
//        });
//
//        worker.setDaemon(true);
//        worker.start();
//    }






