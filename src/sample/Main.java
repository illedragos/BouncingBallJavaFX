package sample;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.util.Duration;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    /*Aplicatia am facut-o cu programul InteliJ utilizant tehnologia FX
        Aplicatia simuleaza saritura unei mingi virtuale in interiorul ferestrei
        in acesta parte definim variabilele care vor ramane constante pe parcursul
        programului*/

    //Latimea canvasului in pixeli
    private static final int CANVAS_WIDTH = 1400;

    //Inaltimea canvasului in pixeli
    private static final int CANVAS_HEIGHT = 1000;

    //timpul la care se va face update la ecran
    //cu cat valoarea este mai mica cu atat mingea se va deplasa cu o viteza mai mare
    //valoarea o vom folosi ca si milisecunde
    private static int UPDATE_PERIOD = 20;

    // Crearea bilei utilizand forma circle
    private Circle ball;

    //Definirea centrului bilei initiale
    //Setam sa porneasca din centrul ecranului
    private int centerX = 700;
    private int centerY = 500;

    //Definirea razei bilei
    private int raza = 100;

    //directia pe care va fi deplasata bila
    private int xStep = 1, yStep = 3;

    @Override
    public void start(final Stage primaryStage) {

        //Setup canvasul unde se va misca bila
        Pane canvas = new Pane();
        canvas.setPrefSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        //Setam Backgorundul la fereastra
        canvas.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

        //Setam scena
        primaryStage.setScene(new Scene(canvas));
        primaryStage.setTitle("JAVA FX EXAMEN BOUNCING BALL (25 Mai 2021) <<made by Dragos Ille>>");
        primaryStage.show();

        //Bila va avea o alta culoare de fiecare data cand lansam programul
        //Setam bila cu culoare implicita verde
        ball = new Circle(centerX, centerY, raza, Color.GREEN);


        canvas.getChildren().addAll(ball);

        //cream butonul si setam fontul mare
        Button b1 = new Button("Restart cu viteza mai mare");
        b1.setStyle("-fx-font-size:30");
        canvas.getChildren().add(b1);


        // restartam aplicatia si crestem viteza cand userul apasa butonul
        b1.setOnAction( __ ->
        {

            if(UPDATE_PERIOD>=2){
                UPDATE_PERIOD = UPDATE_PERIOD -1;
                //verificare valoare Viteza in consola
                System.out.println(UPDATE_PERIOD);
            }

            primaryStage.close();
            Platform.runLater( () -> new Main().start( new Stage() ) );
        } );




        // Setup un timp la care sa se faca update
        Timeline loop = new Timeline(new KeyFrame(Duration.millis(UPDATE_PERIOD), evt -> {

            // updatam x si y
            centerX += xStep;
            centerY += yStep;

            //daca ne aflam pe marginea ferstrei schimbam directia si generam o culoare aleatoare
            //bila va se va colora in noua culoare de fiecare data cand se loveste de margini
            if (centerX + raza > CANVAS_WIDTH || centerX - raza < 0) {
                xStep = -xStep;
                Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
                ball.setFill(color);
            }
            if (centerY + raza > CANVAS_HEIGHT || centerY - raza < 0) {
                yStep = -yStep;
                Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
                ball.setFill(color);
            }

            //schimbam pozitia bilei
            ball.setCenterX(centerX);
            ball.setCenterY(centerY);
        }));

        // Reluam la infinit desenarea pana cand utilizatorul va iesi din program
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    public static void main(final String[] args) {

        //lansam aplicatia
        launch(args);
    }
}