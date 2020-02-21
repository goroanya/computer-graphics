import javafx.application.Application;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Computer Graphics lab1");

        Group root = new Group();
        Scene scene = new Scene(root, 1000, 700);
        scene.setFill(Color.rgb(193,193,193));

        //head
        Rectangle head = new Rectangle(390,95,205,135);
        head.setFill(Color.rgb(129, 0, 255));
        root.getChildren().add(head);

        //eyes
        Rectangle leftEye = new Rectangle(440,130,30,20);
        leftEye.setFill(Color.YELLOW);
        root.getChildren().add(leftEye);

        Rectangle rightEye = new  Rectangle(520,130,30,20);
        rightEye.setFill(Color.YELLOW);
        root.getChildren().add(rightEye);

        //body
        Rectangle body = new Rectangle(230,225,530,190);
        body.setFill(Color.rgb(129, 0, 255));
        root.getChildren().add(body);

        //little part of body
        Rectangle littleBody = new Rectangle(230,415,95,15);
        littleBody.setFill(Color.rgb(129, 0, 255));
        root.getChildren().add(littleBody);

        //pelvis(таз)
        Rectangle pelvis = new Rectangle(325,415,365,70);
        pelvis.setFill(Color.rgb(129, 0, 255));
        root.getChildren().add(pelvis);

        //left leg
        Polygon leftLeg = new Polygon();
        leftLeg.getPoints().addAll(325.0, 485.0,
                325.0, 680.0,
                500.0, 680.0,
                500.0, 615.0,
                465.0, 615.0,
                465.0, 485.0);
        leftLeg.setFill(Color.rgb(129, 0, 255));
        root.getChildren().add(leftLeg);

        // right leg
        Polygon rightLeg = new Polygon();
        rightLeg.getPoints().addAll(570.0, 485.0,
                570.0, 680.0,
                735.0, 680.0,
                735.0, 615.0,
                690.0, 615.0,
                690.0, 485.0);
        rightLeg.setFill(Color.rgb(129, 0, 255));
        root.getChildren().add(rightLeg);

        //ears
        Polygon leftEar = new Polygon();
        leftEar.getPoints().addAll(
                280.0,40.0,
                390.0, 10.0,
                390.0, 95.0
                );
        leftEar.setFill(Color.BLUE);
        root.getChildren().add(leftEar);

        Polygon rightEar = new Polygon();
        rightEar.getPoints().addAll(
                595.0, 20.0,
                595.0, 95.0,
                715.0, 95.0
        );
        rightEar.setFill(Color.BLUE);
        root.getChildren().add(rightEar);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
