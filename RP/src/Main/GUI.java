package Main;

import java.awt.Panel;
import ParticleSwarm.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;

import GeneticAlgorithm.*;

import Function.Functions;

public class GUI extends Application{

//Attributes
	TextField tfGAiterations = new TextField("100");
	TextField tfGAPopulation = new TextField("50");
	TextField tfGAMutationRate = new TextField(".01");
	Button btGAStartIterations = new Button("Solve");
	
	TextField tfPSOiterations = new TextField("100");
	TextField tfPSOPopulation = new TextField("50");
	TextField tfPSOInertia = new TextField("0.729844");
	TextField tfPSOCognitive = new TextField("1.496180");
	TextField tfPSOSocial = new TextField("1.496180");
	Button btPSOStartIterations = new Button("Solve");

	
	//public static final double DEFAULT_INERTIA = 0.729844;
    //public static final double DEFAULT_COGNITIVE = 1.496180; // Cognitive component.
    //public static final double DEFAULT_SOCIAL = 1.496180; // Social component.
    

	int currentFunction = -1;
	
	double y = -1;
	double x = -1;
	
	double GAiterationNumber = 0;
	double PSOiterationNumber = 0;
	int n;
	Timeline timeline = null;

	
	@Override // Override the start method in the Application class
	public void start(Stage stage)throws Exception 
	{
		Pane PSOoptionsPane = new Pane();
		PSOoptionsPane.setLayoutX(10);
		PSOoptionsPane.setLayoutY(10);
		PSOoptionsPane.setPrefHeight(200);
		PSOoptionsPane.setPrefWidth(300);
		PSOoptionsPane.setStyle("-fx-background-color: '#eaeaea';");
		
		Text PSOTitle = new Text("Particle Swarm Optimization");
		PSOTitle.setLayoutX(10);
		PSOTitle.setLayoutY(20);
		PSOTitle.setStyle("-fx-font-size: 14;");
		
		Text PSOiterations = new Text("# of Iterations");
		PSOiterations.setLayoutX(10);
		PSOiterations.setLayoutY(50);
		
		tfPSOiterations.setLayoutX(10);
		tfPSOiterations.setLayoutY(60);	
		tfPSOiterations.setPrefWidth(50);
		
		Text PSOPopulation = new Text("Population");
		PSOPopulation.setLayoutX(10);
		PSOPopulation.setLayoutY(100);
		
		tfPSOPopulation.setLayoutX(10);
		tfPSOPopulation.setLayoutY(105);	
		tfPSOPopulation.setPrefWidth(50);
		
		
		Text PSOInertia = new Text("Inertia");
		PSOInertia.setLayoutX(10);
		PSOInertia.setLayoutY(145);
		
		tfPSOInertia.setLayoutX(10);
		tfPSOInertia.setLayoutY(150);	
		tfPSOInertia.setPrefWidth(100);
		
		Text PSOCognitive = new Text("Cognitive");
		PSOCognitive.setLayoutX(120);
		PSOCognitive.setLayoutY(50);
		
		tfPSOCognitive.setLayoutX(120);
		tfPSOCognitive.setLayoutY(60);	
		tfPSOCognitive.setPrefWidth(100);

		Text PSOSocial = new Text("Social");
		PSOSocial.setLayoutX(120);
		PSOSocial.setLayoutY(100);
		
		tfPSOSocial.setLayoutX(120);
		tfPSOSocial.setLayoutY(105);	
		tfPSOSocial.setPrefWidth(100);
		
		btPSOStartIterations.setLayoutX(240);
		btPSOStartIterations.setLayoutY(160);

		
		
		Pane GAoptionsPane = new Pane();
		GAoptionsPane.setLayoutX(10);
		GAoptionsPane.setLayoutY(220);
		GAoptionsPane.setPrefHeight(200);
		GAoptionsPane.setPrefWidth(300);
		GAoptionsPane.setStyle("-fx-background-color: '#eaeaea';");
		
		Text GATitle = new Text("Genetic Algorithm");
		GATitle.setLayoutX(10);
		GATitle.setLayoutY(20);
		GATitle.setStyle("-fx-font-size: 14;");
		
		Text GAiterations = new Text("# of Iterations");
		GAiterations.setLayoutX(10);
		GAiterations.setLayoutY(50);
		
		tfGAiterations.setLayoutX(10);
		tfGAiterations.setLayoutY(60);	
		tfGAiterations.setPrefWidth(50);
		
		Text GAPopulation = new Text("Population");
		GAPopulation.setLayoutX(10);
		GAPopulation.setLayoutY(100);
		
		tfGAPopulation.setLayoutX(10);
		tfGAPopulation.setLayoutY(105);	
		tfGAPopulation.setPrefWidth(50);

		
		Text GAMutationRate = new Text("Mutation Rate");
		GAMutationRate.setLayoutX(10);
		GAMutationRate.setLayoutY(150);
		
		btGAStartIterations.setLayoutX(240);
		btGAStartIterations.setLayoutY(160);
		
		tfGAMutationRate.setLayoutX(10);
		tfGAMutationRate.setLayoutY(155);	
		tfGAMutationRate.setPrefWidth(50);

		Pane GraphPane = new Pane();
		GraphPane.setLayoutX(350);
		GraphPane.setLayoutY(10);
		GraphPane.setPrefHeight(625);
		GraphPane.setPrefWidth(625);
		GraphPane.setStyle("-fx-background-color: '#c9c9c9';");
		
		Pane bestSolutionPane = new Pane();
		bestSolutionPane.setLayoutX(350);
		bestSolutionPane.setLayoutY(10);
		bestSolutionPane.setPrefHeight(625);
		bestSolutionPane.setPrefWidth(625);
		

		
		
		Pane FunctionPane = new Pane();
		FunctionPane.setLayoutX(10);
		FunctionPane.setLayoutY(440);
		FunctionPane.setPrefHeight(200);
		FunctionPane.setPrefWidth(300);
		FunctionPane.setStyle("-fx-background-color: '#eaeaea';");
		
		Text FunctionTitle = new Text("Function");
		FunctionTitle.setLayoutX(10);
		FunctionTitle.setLayoutY(20);
		FunctionTitle.setStyle("-fx-font-size: 14;");

		
		
		Button btFunction1 = new Button("Function 1");
		btFunction1.setLayoutX(10);
		btFunction1.setLayoutY(80-30);
		btFunction1.setStyle("-fx-background-color: 'white'; -fx-font-color: 'black'; -fx-border-width: 0.3; -fx-border-style: solid;");
		
		Button btFunction2 = new Button("Function 2");
		btFunction2.setLayoutX(10);
		btFunction2.setLayoutY(110-30);
		btFunction2.setStyle("-fx-background-color: 'white'; -fx-font-color: 'black'; -fx-border-width: 0.3; -fx-border-style: solid;");
	
		Button btFunction3 = new Button("Function 3");
		btFunction3.setLayoutX(10);
		btFunction3.setLayoutY(140-30);
		btFunction3.setStyle("-fx-background-color: 'white'; -fx-font-color: 'black'; -fx-border-width: 0.3; -fx-border-style: solid;");
	
		Button btFunction4 = new Button("Function 4");
		btFunction4.setLayoutX(10);
		btFunction4.setLayoutY(170-30);
		btFunction4.setStyle("-fx-background-color: 'white'; -fx-font-color: 'black'; -fx-border-width: 0.3; -fx-border-style: solid;");
	
		Button[] btFunctionArray = {btFunction1, btFunction2, btFunction3, btFunction4};
		
		
		
		

		
		
		Pane DisplayPane = new Pane();
		DisplayPane.setLayoutX(10);
		DisplayPane.setLayoutY(660);
		DisplayPane.setPrefHeight(100);
		DisplayPane.setPrefWidth(750);
		DisplayPane.setStyle("-fx-background-color: '#eaeaea';");
		
		Text GAiterationtxt = new Text("GA iterations: ");
		GAiterationtxt.setLayoutX(10);
		GAiterationtxt.setLayoutY(10);
		
		Text GAX = new Text("X: ");
		GAX.setLayoutX(10);
		GAX.setLayoutY(30);
		
		Text GAY = new Text("Y: ");
		GAY.setLayoutX(10);
		GAY.setLayoutY(50);
		
		
		Text PSOiterationstxt = new Text("PSO iterations: ");
		PSOiterationstxt.setLayoutX(210);
		PSOiterationstxt.setLayoutY(10);	
		
		Text PSOX = new Text("X: ");
		PSOX.setLayoutX(210);
		PSOX.setLayoutY(30);
		
		Text PSOY = new Text("Y: ");
		PSOY.setLayoutX(210);
		PSOY.setLayoutY(50);
		
		Text PSOGBEST = new Text("Global Best: ");
		PSOGBEST.setLayoutX(210);
		PSOGBEST.setLayoutY(70);
		
		Text Functiontxt = new Text("Function: ");
		Functiontxt.setLayoutX(410);
		Functiontxt.setLayoutY(10);
		
		DisplayPane.getChildren().add(PSOGBEST);
		DisplayPane.getChildren().add(Functiontxt);
		DisplayPane.getChildren().add(GAX);
		DisplayPane.getChildren().add(GAY);
		DisplayPane.getChildren().add(PSOX);
		DisplayPane.getChildren().add(PSOY);
		DisplayPane.getChildren().add(PSOiterationstxt);
		DisplayPane.getChildren().add(GAiterationtxt);
		
		Button runBoth = new Button("run both");
		runBoth.setLayoutX(600);
		runBoth.setLayoutY(20);
		DisplayPane.getChildren().add(runBoth);
		
		
		Pane pane = new Pane();
		
		FunctionPane.getChildren().add(FunctionTitle);
		FunctionPane.getChildren().add(btFunction1);
		FunctionPane.getChildren().add(btFunction2);
		FunctionPane.getChildren().add(btFunction3);
		FunctionPane.getChildren().add(btFunction4);
		
		GAoptionsPane.getChildren().add(GATitle);
		GAoptionsPane.getChildren().add(GAiterations);
		GAoptionsPane.getChildren().add(tfGAiterations);
		GAoptionsPane.getChildren().add(GAPopulation);
		GAoptionsPane.getChildren().add(tfGAPopulation);
		GAoptionsPane.getChildren().add(GAMutationRate);
		GAoptionsPane.getChildren().add(tfGAMutationRate);
		GAoptionsPane.getChildren().add(btGAStartIterations);
		
		PSOoptionsPane.getChildren().add(PSOInertia);
		PSOoptionsPane.getChildren().add(tfPSOInertia);
		PSOoptionsPane.getChildren().add(PSOCognitive);
		PSOoptionsPane.getChildren().add(tfPSOCognitive);
		PSOoptionsPane.getChildren().add(PSOSocial);
		PSOoptionsPane.getChildren().add(tfPSOSocial);
		PSOoptionsPane.getChildren().add(PSOTitle);
		PSOoptionsPane.getChildren().add(PSOiterations);
		PSOoptionsPane.getChildren().add(tfPSOiterations);
		PSOoptionsPane.getChildren().add(PSOPopulation);
		PSOoptionsPane.getChildren().add(tfPSOPopulation);
		PSOoptionsPane.getChildren().add(btPSOStartIterations);

		pane.getChildren().add(GraphPane);
		pane.getChildren().add(PSOoptionsPane);
		pane.getChildren().add(GAoptionsPane);
		pane.getChildren().add(FunctionPane);
		pane.getChildren().add(DisplayPane);
		pane.getChildren().add(bestSolutionPane);


		btGAStartIterations.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event)
	    	{
	    		bestSolutionPane.getChildren().clear();
	    		btGAStartIterations.setDisable(true);
	    		//	public FunctionGA(int populationSize, double mutationRate, int function)
	    		FunctionGA geneticAlgorithm = new FunctionGA(Integer.parseInt(tfGAPopulation.getText()), Double.parseDouble(tfGAMutationRate.getText()), currentFunction);
	    		timeline = new Timeline();
				timeline.setCycleCount((Integer.parseInt(tfGAiterations.getText())));
				n = 0;
				KeyFrame keyframe = new KeyFrame(Duration.millis(75), action-> 
				{
					//put x and y on grid then evolve
					
					bestSolutionPane.getChildren().clear();
					n++;
					GAiterationtxt.setText("GA Iteration: " + n);
					
					GAX.setText("X: " + geneticAlgorithm.ChromosomeToDecimalValue(geneticAlgorithm.getBestChromosome()));
					GAY.setText("Y: " + geneticAlgorithm.getFitness(geneticAlgorithm.getBestChromosome()));
					
					
					if(n == (Integer.parseInt(tfGAiterations.getText())))
			    		btGAStartIterations.setDisable(false);
					
					for(int i = 0; i < geneticAlgorithm.getPopulationSize(); i++)
					{
						Circle tempcircle = new Circle();
						tempcircle.setRadius(4);
						tempcircle.setFill(Color.DARKGREEN);
						tempcircle.setLayoutX(260+geneticAlgorithm.ChromosomeToDecimalValue(geneticAlgorithm.getPopulation()[i])*90);
						tempcircle.setLayoutY(530+geneticAlgorithm.getFitness(geneticAlgorithm.getPopulation()[i])*-90);
						bestSolutionPane.getChildren().add(tempcircle);
					}
					
					
					Circle circle = new Circle();
					circle.setRadius(4);
					circle.setFill(Color.GREEN);
					circle.setLayoutX(260+geneticAlgorithm.ChromosomeToDecimalValue(geneticAlgorithm.getBestChromosome())*90);
					circle.setLayoutY(530+geneticAlgorithm.getFitness(geneticAlgorithm.getBestChromosome())*-90);
					
					
					
					
					bestSolutionPane.getChildren().add(circle);
					
					geneticAlgorithm.Evolve();
					
					if(geneticAlgorithm.getGeneration() == (Integer.parseInt(tfGAiterations.getText())))
					{
						timeline.stop();
					}

				});
				
				timeline.getKeyFrames().add(keyframe);
				timeline.play();

	    		
	    	}

	    	
		});
		
		
		btPSOStartIterations.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event)
	    	{
	    		bestSolutionPane.getChildren().clear();
	    		btPSOStartIterations.setDisable(true);
	    		
	    		System.out.println(currentFunction);
	    		Swarm swarm = new Swarm(Integer.parseInt(tfPSOPopulation.getText()), currentFunction, Double.parseDouble(tfPSOInertia.getText()), Double.parseDouble(tfPSOCognitive.getText()), Double.parseDouble(tfPSOSocial.getText()));
	    		
	    		timeline = new Timeline();
				timeline.setCycleCount((Integer.parseInt(tfPSOiterations.getText())));
				n = 0;
				KeyFrame keyframe = new KeyFrame(Duration.millis(75), action-> 
				{
					
					bestSolutionPane.getChildren().clear();
					n++;
					PSOiterationstxt.setText("PSO Iteration: " + n);
					
					PSOX.setText("X: " + swarm.getBestPosition().getX());
					PSOY.setText("Y: " + swarm.bestPositionsY());

					//PSOY.setText("Y: " + swarm.ds();
					
					
					if(n == (Integer.parseInt(tfPSOiterations.getText())))
			    		btPSOStartIterations.setDisable(false);
					
					for(int i = 0; i < swarm.getParticles().length; i++)
					{
						Circle tempcircle = new Circle();
						tempcircle.setRadius(4);
						tempcircle.setFill(Color.RED);
						tempcircle.setLayoutX(260+swarm.getParticles()[i].getPosition().getX()*90);
						tempcircle.setLayoutY(530+swarm.getParticles()[i].eval()*-90);
						bestSolutionPane.getChildren().add(tempcircle);
					}
					
					swarm.updateVelocities();
					
					if(swarm.getEpoch() == (Integer.parseInt(tfPSOiterations.getText())))
					{
						timeline.stop();
					}
				
				});
				
				timeline.getKeyFrames().add(keyframe);
				timeline.play();
	    		
	    	}

	    	
		});
		
		//PSO first
		runBoth.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event)
	    	{
	    		bestSolutionPane.getChildren().clear();
	    		btPSOStartIterations.setDisable(true);
	    		
	    		System.out.println(currentFunction);
	    		Swarm swarm = new Swarm(Integer.parseInt(tfPSOPopulation.getText()), currentFunction, Double.parseDouble(tfPSOInertia.getText()), Double.parseDouble(tfPSOCognitive.getText()), Double.parseDouble(tfPSOSocial.getText()));
	    		
	    		timeline = new Timeline();
				timeline.setCycleCount((Integer.parseInt(tfPSOiterations.getText())));
				n = 0;
				KeyFrame keyframe = new KeyFrame(Duration.millis(75), action-> 
				{
					
					bestSolutionPane.getChildren().clear();
					n++;
					PSOiterationstxt.setText("PSO Iteration: " + n);
					
					PSOX.setText("X: " + swarm.getBestPosition().getX());
					PSOY.setText("Y: " + swarm.bestPositionsY());
					
					PSOGBEST.setText("Global best: " + swarm.getBestPosition().getX());
					
					
					if(n == (Integer.parseInt(tfGAiterations.getText())))
			    		btPSOStartIterations.setDisable(false);
					
					for(int i = 0; i < swarm.getParticles().length; i++)
					{
						Circle tempcircle = new Circle();
						tempcircle.setRadius(4);
						tempcircle.setFill(Color.RED);
						tempcircle.setLayoutX(260+swarm.getParticles()[i].getPosition().getX()*90);
						tempcircle.setLayoutY(530+swarm.getParticles()[i].eval()*-90);
						bestSolutionPane.getChildren().add(tempcircle);
					}
					
					swarm.updateVelocities();
					
					if(swarm.getEpoch() == (Integer.parseInt(tfGAiterations.getText())))
					{
						timeline.stop();
					}
				
				});
				
				timeline.getKeyFrames().add(keyframe);
				timeline.play();	
	    	}

	    	
		});


		Scene scene = new Scene(pane, 990, 780);
		stage.setTitle("PSO-GA research project"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
		
		for(int i = 0; i < btFunctionArray.length; i++)
		{
			
			final int ii = i;
			btFunctionArray[i].setOnAction(new EventHandler<ActionEvent>() {
		    	@Override
		    	public void handle(ActionEvent event)
		    	{
		    		try
		    		{
		    		timeline.stop();
		    		}catch(NullPointerException e)
		    		{
		    			
		    		}
		    		bestSolutionPane.getChildren().clear();

		    		for(int i = 0; i < btFunctionArray.length; i++)
			    		btFunctionArray[i].setStyle("-fx-background-color: 'white'; -fx-text-fill: 'black'; -fx-border-width: 0.3; -fx-border-style: solid;");

		    		btFunctionArray[ii].setStyle("-fx-background-color: 'black'; -fx-text-fill: 'white'; -fx-border-width: 0.3; -fx-border-style: solid;");
		    		btGAStartIterations.setDisable(false);
		    		btPSOStartIterations.setDisable(false);

		    		
		    		GraphPane.getChildren().clear();
		    		ArrayList<double[]> points = new ArrayList<double[]>();
		    		
		    		for(double i = -10; i < 10; i+=.01)
		    		{
		    			double[] point = new double[2];
		    			point[0] = i;
		    			
		    			switch(ii)
		    			{
		    				case 0: point[1] = Function.Functions.firstFunction(i);currentFunction = 1;
		    					break;
		    				case 1: point[1] = Function.Functions.secondFunction(i);currentFunction = 2;
	    						break;

		    				case 2: point[1] = Function.Functions.thirdFunction(i);currentFunction = 3;
	    						break;

		    				case 3: point[1] = Function.Functions.fourthFunction(i);currentFunction = 4;
	    						break;
		    			}
		    			points.add(point);
		    		}
		    		
		    		for(int i = 0; i < points.size()-1; i++)
		    		{
		    				Line newVector = new Line(260+points.get(i)[0]*90,530+points.get(i)[1]*-90,260+points.get(i+1)[0]*90, 530+points.get(i+1)[1]*-90);
		    				GraphPane.getChildren().add(newVector);
		    		}
		    	}
			});
		}
  }

  public static void main(String[] args) {
    launch(args);
  }
}



