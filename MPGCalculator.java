package ch15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Package: ch15
 * 
 * @author Tyler Faircloth and Matthew Goetz Created on: 4/1/2021 Description:
 *         Using JavaFX, create a miles per gallon calculator that allows the
 *         user to enter miles and gallons, then press a button to calculate and
 *         displays the miles per gallon based on those values
 */

public class MPGCalculator extends Application {
	private TextField tfMiles = new TextField();
	private TextField tfGallons = new TextField();
	private TextField calcMPG = new TextField();
	private Button btn1 = new Button("   +   "); // the plus button is bigger than the rest
	private Button btn2 = new Button("    -   ");
	private Button btn3 = new Button("    *   ");
	private Button btn4 = new Button("    /   ");
	private Button btn5 = new Button("Calculate");

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		// Create UI
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(15, 15, 15, 15));
		gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("Enter Miles:"), 0, 0);
		gridPane.add(tfMiles, 1, 0);
		gridPane.add(new Label("Enter Gallons:"), 0, 1);
		gridPane.add(tfGallons, 1, 1);
		gridPane.add(new Label("calculated MPG:"), 0, 3);
		gridPane.add(calcMPG, 1, 3);
		gridPane.add(btn5, 2, 3);
		calcMPG.setEditable(false); // user cant access textField

		GridPane gridPane2 = new GridPane();
		gridPane2.setPadding(new Insets(15, 15, 15, 15));
		gridPane2.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridPane2.setHgap(5);
		gridPane2.setVgap(5);
		gridPane2.add(btn1, 2, 0);
		gridPane2.add(btn2, 3, 0);
		gridPane2.add(btn3, 2, 1);
		gridPane2.add(btn4, 3, 1);

		btn1.setOnAction(e -> clickbtn1());
		btn2.setOnAction(e -> clickbtn2());
		btn3.setOnAction(e -> clickbtn3());
		btn4.setOnAction(e -> clickbtn4());
		btn5.setOnMouseClicked(e -> clickbtn5()); // displays in console btn5 clicked
		btn5.setOnAction(e -> calculateMPG()); // displays to user the mpg
		tfMiles.setOnKeyTyped(e -> enterMiles());
		tfGallons.setOnKeyTyped(e -> enterGallons());

		BorderPane mainPane = new BorderPane();
		mainPane.setLeft(gridPane);
		mainPane.setCenter(gridPane2);

		// Create a scene and place it in the stage
		Scene scene = new Scene(mainPane, 500, 200);
		primaryStage.setTitle("Calculate MPG"); // Set title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}

	private void calculateMPG() { // pulling user data to MPG class and return result

		// Get values from text fields
		double miles = Double.parseDouble(tfMiles.getText());
		double gallons = Double.parseDouble(tfGallons.getText());
		String mathOperation = null;

		if (btn1.isArmed() == true) {
			System.out.println("hey");
			mathOperation = "+";
		} else if (btn2.isPressed() == true) {
			mathOperation = "-";
		} else if (btn3.isPressed() == true) {
			mathOperation = "*";
		} else if (btn4.isPressed() == true) {
			mathOperation = "/";
		}

		MPG mpgCalc = new MPG(miles, gallons, mathOperation);

		// calculated MPG from calcMPG class
		calcMPG.setText(String.format("%.4f", mpgCalc.getCalcMPG()));

	}

	// -----------------------set action for button click ---------------
	
	// display "+" button was clicked
	private void clickbtn1() {
		System.out.printf("Button \"%s\" was clicked%n", btn1.getText());
	}

	// display "-" button was clicked
	private void clickbtn2() {
		System.out.printf("Button \"%s\" was clicked%n", btn2.getText());
	}

	// display "*" button was clicked
	private void clickbtn3() {
		System.out.printf("Button \"%s\" was clicked%n", btn3.getText());
	}

	// display "/" button was clicked
	private void clickbtn4() {
		System.out.printf("Button \"%s\" was clicked%n", btn2.getText());
	}

	// display "calculate" button was clicked
	private void clickbtn5() {
		System.out.printf("Button \"%s\" was clicked%n", btn5.getText());
	}

	// display user entered " value " for miles
	private void enterMiles() {
		System.out.printf("user entered \"%s\" for miles%n", tfMiles.getText());
	}

	// display user entered " value " for gallons
	private void enterGallons() {
		System.out.printf("user entered \"%s\" for gallons%n", tfGallons.getText());
	}

	// --------------------main method -----------------------
	public static void main(String[] args) {
		launch(args);
	}
}

// --------------------------MPG class-----------------------

class MPG {
	private double miles;
	private double gallons;
	private double calcMPG;
	private String mathOperation;

	public MPG() { // no arg constructor
		this(0, 0, "");
	}

	public MPG(double miles, double gallons, String mathOperation) { // constructor
		this.miles = miles;
		this.gallons = gallons;
		this.mathOperation = mathOperation;

	}

	// -----------------------------getters and setters----------------------------

	public String getMathOperation() { // get the button pressed
		return mathOperation;
	}

	public void setMathOperation(String mathOperation) {
		this.mathOperation = mathOperation;
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) { // make sure miles isnt 0
		if (miles <= 0) {
			this.miles = 1;
		} else {
			this.miles = miles;
		}
	}

	public double getGallons() {
		return gallons;
	}

	public void setGallons(double gallons) { // make sure gallons isnt 0
		if (gallons <= 0) {
			this.gallons = 1;
		} else {
			this.gallons = gallons;
		}
	}

	public double getCalcMPG() {
		return calcMPG;
	}

	public void setCalcMPG(double calcMPG) { // calculate MPG

		switch (mathOperation) { // switch between math operation selected
		case "+":
			this.calcMPG = getMiles() + getGallons();
			break;
		case "-":
			this.calcMPG = getMiles() - getGallons();
			break;
		case "*":
			this.calcMPG = getMiles() * getGallons();
			break;
		case "/":
			this.calcMPG = getMiles() / getGallons();
			break;

		}
	}
}
