
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class testingTimer extends Application {

    private static final Integer STARTTIME = 40;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private int score = 0;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FX Timer with Binding");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 400);

        // Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(STARTTIME);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME+1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        Button button = new Button();
        button.setText("Start Timer");
        button.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(STARTTIME+1), new KeyValue(timeSeconds, 0)));
                timeline.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        timeSeconds.set(score);
                    }
                });
                timeline.playFromStart();
            }
        });

        Label lbl = new Label();
        lbl.setTextFill(Color.BLUE);
        lbl.setStyle("-fx-font-size: 4em;");
        lbl.setText(Integer.toString(score));
        Button btn = new Button();
        btn.setText("+1");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                score++;
                lbl.setText(Integer.toString(score));
            }
        });


        Button messMeUp = new Button();
        messMeUp.setText("Stress");
        messMeUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int i =0;
                int num =0;
                //Empty String
                String  primeNumbers = "";

                for (i = 1; i <= 100000; i++)
                {
                    int counter=0;
                    for(num =i; num>=1; num--)
                    {
                        if(i%num==0)
                        {
                            counter = counter + 1;
                        }
                    }
                    if (counter ==2)
                    {
                        //Appended the Prime number to the String
                        primeNumbers = primeNumbers + i + " ";
                    }
                }
                System.out.println("Prime numbers from 1 to 100 are :");
                System.out.println(primeNumbers);


            }
        });
        VBox vb = new VBox(20);             // gap between components is 20
        vb.setAlignment(Pos.CENTER);        // center the components within VBox

        vb.setPrefWidth(scene.getWidth());
        vb.getChildren().addAll(button, timerLabel, btn, lbl, messMeUp);
        vb.setLayoutY(30);

        root.getChildren().add(vb);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}



// ALL TAKEN FROM http://asgteach.com/2011/10/javafx-animation-and-binding-simple-countdown-timer-2/
