import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Testing the random generation of the letters inside buttons. Buttons have no functions except the SHUFFLE!! button
 */
public class testingRandomGeneration extends Application{
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Random Letter Test");
        Group root = new Group();
        Scene scene = new Scene(root, 400,400);
        primaryStage.setScene(scene);
        String LETTERS = "AAAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUVVWWXYYZ";
        //String LETTERS = "QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ";
        //If you wish to test only Q, use the above line
        String LETTERS_ARRAY[] = LETTERS.split("(?!^)"); //gets an array of each character. Type is String
        Collections.shuffle(Arrays.asList(LETTERS_ARRAY));
        for(String letter: LETTERS_ARRAY) {
            //Checking to see if successful
            System.out.println(letter);
        }
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(primaryScreenBounds.getHeight());
        System.out.println(primaryScreenBounds.getWidth());
        double X = primaryScreenBounds.getWidth();
        double Y = primaryScreenBounds.getHeight();
        //Here I'm checking the user's screen size and basing button sizes on that. 800x600 doesn't work too well
        //the font size doesn't fit too well. Just going for demo here though.
        GridPane mainPain = new GridPane();

        Button shuffle = new Button();
        shuffle.setLayoutY(Y/2);
        shuffle.setLayoutX(X/2);
        shuffle.setPrefWidth(X/16);   //Setting niceness!
        shuffle.setPrefHeight(X/16);
        shuffle.setText("SHUFFLE!!");


        ArrayList<GridPane> grids = new ArrayList<>(); //To access the individual buttons, gridpanes are in an arraylist
        //This for loop may need explaining. I'm creating 3 gridpanes that contain 9 buttons each. the nested j and k
        //loops tell the buttons where in the gridpane to go. Gridpanes can be accessed, so individual buttons can be
        //played around with.
        for(int i = 0; i < 3; i++){
            GridPane gridPane = new GridPane();
            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 3; k++){
                    Button btn = new Button();
                    btn.setPrefWidth(X/32);
                    btn.setPrefHeight(X/32);
                    gridPane.add(btn, j, k);
                }
            }
            gridPane.setAlignment(Pos.CENTER); //This makes it so the gridpanes are central on the screen
            grids.add(gridPane);
            mainPain.add(gridPane, i, 0);
        }
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //Reshuffles on click!
            public void handle(ActionEvent event) {
                reshuffle(grids, LETTERS_ARRAY);
            }
        });
        mainPain.setPrefWidth(X);
        mainPain.setAlignment(Pos.BOTTOM_CENTER);
        //ColumnConstraints pad the gridpane based on the avaliable space given to it. Because the space provided to it
        //is the entire X axis, this will span it across the screen evenly.
        mainPain.getColumnConstraints().setAll(ColumnConstraintsBuilder.create().percentWidth(100/3.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/3.0).build()
        );
        reshuffle(grids, LETTERS_ARRAY);
        root.getChildren().addAll(mainPain, shuffle);
        primaryStage.setMaximized(true);
        primaryStage.show();


    }

    private void reshuffle(ArrayList<GridPane> grids, String[]LETTERS_ARRAY){
        Collections.shuffle(Arrays.asList(LETTERS_ARRAY));
        int x = 0;
        //Takes the first 9 characters in the LETTERS_ARRAY and then sets button text to the character it is given
        for(GridPane grid: grids){
            for(Node button: grid.getChildren()){
                Button btn = (Button) button;
                switch (LETTERS_ARRAY[x]){
                    //Because Q needs a u with it, there is a special case to show that here.
                    case "Q":
                        btn.setText("Qu");
                        break;
                    default:
                        btn.setText(LETTERS_ARRAY[x]);
                }
                x++;
            }
        }
    }

}


