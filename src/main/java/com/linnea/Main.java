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

public class Main extends Application {

    private List<Member> memberList = new ArrayList<>();

    @Override
    public void start(Stage stage)  {
        mainMenuWindow(stage);
    }

    public void mainMenuWindow(Stage stage) {

        stage.setTitle("HUVUDMENY");

        Label menuLabel = new Label("Välj vad du vill göra");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        menuLabel.setStyle("-fx-padding: 30");

        Button memberButton = new Button();
        memberButton.setText("Medlemsformulär");
        memberButton.setPrefSize(230, 60);
        memberButton.setOnAction(e -> memberFormWindow(stage));

        Button timerButton = new Button();
        timerButton.setText("Tidtagarur");
        timerButton.setPrefSize(230, 60);
        timerButton.setOnAction(e -> timerWindow(stage));

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

    public void memberFormWindow(Stage stage) {

        stage.setTitle("MEDLEMSFORMULÄR");

        Button toMenuButton = new Button("Huvudmeny");
        toMenuButton.setPrefSize(115, 40);
        toMenuButton.setOnAction(e -> start(stage));

        Label headingLabel = new Label("Fyll i uppgifter om medlem");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

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

        Label savedStatusLabel = new Label();
        savedStatusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label savedMemberLabel = new Label();
        savedMemberLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Button saveButton = new Button("Spara");
        saveButton.setPrefSize(115, 40);
        saveButton.setOnAction(e -> {

            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                    || phoneNrField.getText().isEmpty() || addressField1.getText().isEmpty()
                    || addressField2.getText().isEmpty()) {

                savedStatusLabel.setText("Du har inte fyllt i alla fält!");
                savedStatusLabel.setStyle("-fx-text-fill: red");
                return;
            }

            Member member = new Member(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    phoneNrField.getText(),
                    addressField1.getText(),
                    addressField2.getText());

            memberList.add(member);

            savedStatusLabel.setStyle("-fx-text-fill: black");
            savedStatusLabel.setText("Följande uppgifter är sparade:");

            savedMemberLabel.setText(member.toString());

            firstNameField.clear();
            lastNameField.clear();
            phoneNrField.clear();
            addressField1.clear();
            addressField2.clear();
        });

        Button printAllButton = new Button("Medlemsregister");
        printAllButton.setPrefSize(115, 40);
        printAllButton.setOnAction(e -> memberRegistryWindow(stage));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-padding: 30");
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.add(headingLabel, 0, 0);
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
        gridPane.add(savedStatusLabel, 0, 14);
        gridPane.add(savedMemberLabel, 0, 15);

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

    public void memberRegistryWindow(Stage stage)    {

        stage.setTitle("MEDLEMSREGISTER");

        Label headingLabel = new Label("Registrerade medlemmar");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headingLabel.setStyle("-fx-padding: 30");
        headingLabel.setAlignment(Pos.CENTER);

         if (memberList.isEmpty()) {
            headingLabel.setText("Saknas registrerade medlemmar");
        }

        TextFlow textFlow = new TextFlow();
        textFlow.setLineSpacing(5);

        for (Member member : memberList) {

            Text firstNameTitle = new Text("Förnamn: ");
            firstNameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text firstNameValue = new Text(member.getFirstName() + "\n");
            firstNameValue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            Text lastNameTitle = new Text("Efternamn: ");
            lastNameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text lastNameValue = new Text(member.getLastName() + "\n");
            lastNameValue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            Text phoneNrTitle = new Text("Telefonnummer: ");
            phoneNrTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text phoneNrValue = new Text(member.getPhoneNr() + "\n");
            phoneNrValue.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            Text addressLine1Title = new Text("Adress: ");
            addressLine1Title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text addressLine1Value = new Text(member.getAddressLine1() + "\n");

            Text addressLine2Title = new Text("Postnummer och ort: ");
            addressLine2Title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            Text addressLine2Value = new Text(member.getAddressLine2() + "\n\n");
            addressLine2Value.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            textFlow.getChildren().addAll(firstNameTitle, firstNameValue, lastNameTitle, lastNameValue, phoneNrTitle,
                    phoneNrValue, addressLine1Title, addressLine1Value, addressLine2Title, addressLine2Value
            );
        }

        ScrollPane scrollPane = new ScrollPane(textFlow);

        Button returnToForm = new Button("Medlemsformulär");
        returnToForm.setPrefSize(115, 40);
        returnToForm.setOnAction(e -> memberFormWindow(stage));

        Button returnToMenu = new Button("Huvudmeny");
        returnToMenu.setPrefSize(115, 40);
        returnToMenu.setOnAction(e -> start(stage));

        HBox hBox = new HBox();
        hBox.setSpacing(32);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setStyle("-fx-padding: 30");
        hBox.getChildren().addAll(returnToForm, returnToMenu);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(headingLabel);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 450, 750);
        stage.setScene(scene);
        stage.show();
    }

    public void timerWindow(Stage stage) {

        stage.setTitle("TIDTAGARUR");

        Label timeLabel = new Label("Tid: ");
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label showTimeLabel = new Label();
        showTimeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));

        Timer timer = new Timer(showTimeLabel);

        Button startTimerButton = new Button("Start");
        startTimerButton.setPrefSize(115, 40);
        startTimerButton.setOnAction(e -> timer.startTimer());

        Button stopTimerButton = new Button("Stopp");
        stopTimerButton.setPrefSize(115, 40);
        stopTimerButton.setOnAction(e -> timer.stopTimer());

        Button clearTimerButton = new Button("Nollställ");
        clearTimerButton.setPrefSize(115, 40);
        clearTimerButton.setOnAction(e -> timer.clearTimer());

        Button returnToMenu = new Button("Huvudmeny");
        returnToMenu.setPrefSize(115, 40);
        returnToMenu.setOnAction(e -> start(stage));

        HBox hboxTop = new HBox();
        hboxTop.getChildren().addAll(timeLabel, showTimeLabel);
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






