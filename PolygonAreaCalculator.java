import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PolygonAreaCalculator extends Application {
	
	final int PANEL_WIDTH = 900;
	final int PANEL_HEIGHT = 700;
	
	public void start(Stage primaryStage) {
		Scene scene = new Scene(new PolygonAreaGUI(), PANEL_WIDTH, PANEL_HEIGHT);
		primaryStage.setTitle("Polygon Area Calculator");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
