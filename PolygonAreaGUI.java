import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PolygonAreaGUI extends BorderPane {

	private TextArea taOutputField = new TextArea();
	private TextField tfSizePrompt = new TextField();
	private Label lblAreaOfPolygon = new Label();
	private GridPane gP = new GridPane();

	public PolygonAreaGUI() {
		this.setTop(promptSizePanel());
		this.setCenter(graphicsPanel());
		this.setRight(inputPanel());
		this.setBottom(outputPanel());
	}

	private VBox promptSizePanel() {
		VBox vB = new VBox();
		vB.setSpacing(10);
		Label lblPrompt = new Label("How many points for the polygon?");
		Button btReset = new Button("Reset");
		btReset.setOnAction(e -> {
			gP.getChildren().clear();
			tfSizePrompt.clear();
			taOutputField.clear();
			lblAreaOfPolygon.setText("");
		});
		vB.setAlignment(Pos.CENTER);
		vB.setPadding(new Insets(10, 10, 10, 300));
		vB.setMaxWidth(500);
		vB.getChildren().addAll(lblPrompt, tfSizePrompt, btReset);
		return vB;
	}

	private ScrollPane inputPanel() {
		Button btCalculate = new Button("Calculate");
		Button btClear = new Button("Clear");
		gP.setHgap(10);
		gP.setVgap(10);

		tfSizePrompt.setOnAction(e -> {
			try {
				if (Integer.parseInt(tfSizePrompt.getText()) < 3)
					taOutputField.setText("Polygon must have 3 or more sides!");
				else {
					int sum = 0;
					int numOfTextFields = Integer.parseInt(tfSizePrompt.getText());
					TextField[] xValues = new TextField[numOfTextFields];
					TextField[] yValues = new TextField[numOfTextFields];
					gP.add(btCalculate, 1, sum + numOfTextFields);
					gP.add(btClear, 2, sum + numOfTextFields);

					for (int n = 0; n < numOfTextFields; n++) {
						Label lblX = new Label("X" + (n + 1));
						lblX.setPadding(new Insets(5));
						Label lblY = new Label("Y" + (n + 1));
						lblY.setPadding(new Insets(5));
						xValues[n] = new TextField();
						yValues[n] = new TextField();
						gP.add(xValues[n], 1, sum);
						gP.add(yValues[n], 3, sum);
						gP.add(lblX, 0, sum);
						gP.add(lblY, 2, sum);
						sum += 1;
					}
					btCalculate.setOnAction(a -> {
						try {
							double[] xNew = new double[numOfTextFields];
							double[] yNew = new double[numOfTextFields];
							for (int i = 0; i < Integer.parseInt(tfSizePrompt.getText()); i++) {
								xNew[i] = Double.parseDouble(xValues[i].getText());
								yNew[i] = Double.parseDouble(yValues[i].getText());
							}
							PolygonArea poly = new PolygonArea(numOfTextFields, xNew, yNew);
							if (poly.getIsConvex()) {
								lblAreaOfPolygon
										.setText(String.format("Area of polygon = %.1f", poly.getPolygonArea()));
								taOutputField.clear();
							} else
								lblAreaOfPolygon.setText("Not convex polygon!");
						} catch (Exception error2) {
							taOutputField.setText("ERROR FROM INPUT FIELDS: " + error2.getMessage());
						}
					});
					btClear.setOnAction(a -> {
						for (int i = 0; i < numOfTextFields; i++) {
							xValues[i].clear();
							yValues[i].clear();
							lblAreaOfPolygon.setText("");
							taOutputField.clear();
						}
					});
				}
			} catch (Exception error) {
				taOutputField.setText(("ERROR FROM SIZE PROMPT: " + error.getMessage()));
			}
		});
		ScrollPane sP = new ScrollPane(gP);
		return sP;
	}

	private HBox graphicsPanel() {
		HBox hB = new HBox();
		lblAreaOfPolygon.setStyle("-fx-font: 20 cursive; -fx-text-fill: darkblue; -fx-font-weight: bold;");
		hB.setAlignment(Pos.CENTER);
		hB.getChildren().addAll(lblAreaOfPolygon);
		return hB;
	}

	private HBox outputPanel() {
		HBox hB = new HBox();
		taOutputField.setEditable(false);
		taOutputField.setStyle("-fx-font: 12 cursive; -fx-text-fill: red; -fx-font-weight: bold;");
		hB.setAlignment(Pos.CENTER);
		hB.setPadding(new Insets(30));
		hB.getChildren().addAll(taOutputField);
		return hB;
	}
}
