package project;

	import java.io.OutputStream;
	import java.io.PrintStream;
	import javafx.application.*;
	import javafx.scene.*;
	import javafx.scene.control.*;
	import javafx.scene.input.*;
	import javafx.scene.paint.*;
	import javafx.scene.shape.*;
	import javafx.stage.*;
	import javafx.application.*;
	//import javafx.

	public class GUI extends Application {

	    public static Stage instance;

	    public Shape[] pad = new Rectangle[4];
	    public Shape[][] cells = new Circle[6][6];
	    public Button[] rotate = new Button[8];
	    public Group root = new Group();
	    public Scene scene = new Scene(root ,800 ,595 ,Color.WHITE);
	    public TextArea textArea = new TextArea();

	    @Override
	    public void start(Stage primaryStage) {
	        instance = primaryStage;
	        primaryStage.setScene(scene);
	        primaryStage.setOnCloseRequest(e->System.exit(0));

	        Pentago game = new Pentago();

	        pad[0] = new Rectangle(60 ,10 ,285 ,285);
	        pad[1] = new Rectangle(60 ,300 ,285 ,285);
	        pad[2] = new Rectangle(350 ,10 ,285 ,285);
	        pad[3] = new Rectangle(350 ,300 ,285 ,285);
	        pad[0].setFill(Color.RED);
	        pad[1].setFill(Color.RED);
	        pad[2].setFill(Color.RED);
	        pad[3].setFill(Color.RED);
	        root.getChildren().addAll(pad);

	        rotate[0] = new Button("C");
	        rotate[0].setLayoutX(10);
	        rotate[0].setLayoutY(10);
	        rotate[0].setMinWidth(40);
	        rotate[0].setMaxWidth(40);
	        rotate[0].setMinHeight(25);
	        rotate[0].setMaxHeight(25);
	        rotate[0].setOnMouseClicked(event->game.promptRotate(0 ,false));
	        root.getChildren().add(rotate[0]);


	        rotate[1] = new Button("COC");
	        rotate[1].setLayoutX(10);
	        rotate[1].setLayoutY(40);
	        rotate[1].setMinWidth(40);
	        rotate[1].setMaxWidth(40);
	        rotate[1].setMinHeight(25);
	        rotate[1].setMaxHeight(25);
	        rotate[1].setOnMouseClicked(event->game.promptRotate(0 ,true));
	        root.getChildren().add(rotate[1]);

	        rotate[2] = new Button("C");
	        rotate[2].setLayoutX(10);
	        rotate[2].setLayoutY(535);
	        rotate[2].setMinWidth(40);
	        rotate[2].setMaxWidth(40);
	        rotate[2].setMinHeight(25);
	        rotate[2].setMaxHeight(25);
	        rotate[2].setOnMouseClicked(event->game.promptRotate(1 ,false));
	        root.getChildren().add(rotate[2]);

	        rotate[3] = new Button("COC");
	        rotate[3].setLayoutX(10);
	        rotate[3].setLayoutY(565);
	        rotate[3].setMinWidth(40);
	        rotate[3].setMaxWidth(40);
	        rotate[3].setMinHeight(25);
	        rotate[3].setMaxHeight(25);
	        rotate[3].setOnMouseClicked(event->game.promptRotate(1 ,true));
	        root.getChildren().add(rotate[3]);

	        rotate[4] = new Button("C");
	        rotate[4].setLayoutX(645);
	        rotate[4].setLayoutY(10);
	        rotate[4].setMinWidth(40);
	        rotate[4].setMaxWidth(40);
	        rotate[4].setMinHeight(25);
	        rotate[4].setMaxHeight(25);
	        rotate[4].setOnMouseClicked(event->game.promptRotate(2 ,false));
	        root.getChildren().add(rotate[4]);


	        rotate[5] = new Button("COC");
	        rotate[5].setLayoutX(645);
	        rotate[5].setLayoutY(40);
	        rotate[5].setMinWidth(40);
	        rotate[5].setMaxWidth(40);
	        rotate[5].setMinHeight(25);
	        rotate[5].setMaxHeight(25);
	        rotate[5].setOnMouseClicked(event->game.promptRotate(2 ,true));
	        root.getChildren().add(rotate[5]);

	        rotate[6] = new Button("C");
	        rotate[6].setLayoutX(645);
	        rotate[6].setLayoutY(535);
	        rotate[6].setMinWidth(40);
	        rotate[6].setMaxWidth(40);
	        rotate[6].setMinHeight(25);
	        rotate[6].setMaxHeight(25);
	        rotate[6].setOnMouseClicked(event->game.promptRotate(3 ,false));
	        root.getChildren().add(rotate[6]);

	        rotate[7] = new Button("COC");
	        rotate[7].setLayoutX(645);
	        rotate[7].setLayoutY(565);
	        rotate[7].setMinWidth(40);
	        rotate[7].setMaxWidth(40);
	        rotate[7].setMinHeight(25);
	        rotate[7].setMaxHeight(25);
	        rotate[7].setOnMouseClicked(event->game.promptRotate(3 ,true));
	        root.getChildren().add(rotate[7]);

	        textArea.setWrapText(true);
	        textArea.setLayoutX(645);
	        textArea.setLayoutY(70);
	        textArea.setMinHeight(460);
	        textArea.setMaxHeight(460);
	        textArea.setMinWidth(145);
	        textArea.setMaxWidth(145);
	        textArea.setEditable(false);
	        System.setOut(new TextAreaPrintStream(textArea ,System.out));
	        root.getChildren().add(textArea);


	        for(int i = 0; i < 6; i++) {
	            for(int j = 0; j < 6; j++) {
	                cells[i][j] = new Circle(110 + 95 * i ,60 + 95 * j ,40 ,Color.GRAY);
	                int finalI = i;
	                int finalJ = j;
	                cells[i][j].setOnMouseClicked((MouseEvent e)->game.promptMove(finalI ,finalJ));
	            }
	            root.getChildren().addAll(cells[i]);
	        }
	        primaryStage.show();

	        new Thread(()->game.startGame(this)).start();
	    }

	    class TextAreaPrintStream extends PrintStream {

	        private TextArea status;

	        TextAreaPrintStream(TextArea area ,OutputStream out) {
	            super(out);
	            status = area;
	        }

	        public void println(String string) {
	            status.appendText(string + "\n");
	        }

	        public void print(String string) {
	            status.appendText(string);
	        }
	    }
	}




}
