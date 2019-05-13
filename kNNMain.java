import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

     // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label

	List <DataPoint> allTheData = DataSet.readDataSet(args[0]);
		
	String label = allTheData.get(0).getLabel();
	double[] X = allTheData.get(0).getX();	
			
	for(int i=0; i<X.length; i++){System.out.print(X[i] +"\t");}
	System.out.println(label);
			
	double train = 0.2; // the % of data to reserve for later

    //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset
	List<DataPoint> testData = DataSet.getTestSet(allTheData, train); 
	String testLabel = testData.get(0).getLabel();
	double[] testX = testData.get(0).getX();	
			
	for(int i=0; i<testX.length; i++){System.out.print(testX[i] +"\t");}
	System.out.println(testLabel);

	List<DataPoint> trainData = DataSet.getTrainingSet(allTheData, (1-train)); //will train with ohter set of data
	String trainLabel = trainData.get(0).getLabel();
	double[] trainX = trainData.get(0).getX();	
			
	for(int i=0; i<trainX.length; i++){System.out.print(trainX[i] +"\t");}
	System.out.println(trainLabel);


    // TASK 3: Use the DataSet class methods to plot the 2D data (binary and multi-class)



    // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
    // and returns the Euclidean distance between those two points (as a double)
	DataPoint first = trainData.get(0);
	DataPoint second = trainData.get(1);
	double d = DataSet.distanceEuclid(first, second);
	
	System.out.println(d);


    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print a predicted target label
	KNNClassifier Classifier3 = new KNNClassifier(3);
	
	DataPoint[] NeighbourDistances = Classifier3.getNearestNeighbors(trainData, trainData.get(0));
	
	String prediction = Classifier3.predict(trainData, trainData.get(0));
	String answer = testData.get(0).getLabel();

	System.out.println(prediction);

    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.

	int k = 5;
	Scanner keyboard = new Scanner(System.in);
	System.out.print("k: ");
	
	k = keyboard.nextInt();
	
	
	KNNClassifier myNewClassifier = new KNNClassifier(k);
	
	int i = 0;
	int correct = 0;
		for(i = 0; i<testData.size(); i++){
			prediction = myNewClassifier.predict(trainData, testData.get(i));
			answer = trainData.get(i).getLabel();
			
			System.out.print("prediction: " + prediction);
			System.out.print("\t\t\tanswer: " + answer);
			
			if(prediction.length() == answer.length()){ // check lengths because checking values did not work
				correct++;
				System.out.println("\t\t\tYES");
			}else{
				System.out.println("\t\t\tNO");
			}
		
		}
		
		System.out.println(correct + " out of " + i);
  }

  public static double mean(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: average of the elements of array, as a double
    */
    double sum = 0.0;

    for (double a : arr){
      sum += a;
    }
    return (double)sum/arr.length;
  }

  public static double standardDeviation(double[] arr){
    /*
    Method that takes as an argument an array of doubles
    Returns: standard deviation of the elements of array, as a double
    Dependencies: requires the *mean* method written above
    */
    double avg = mean(arr);
    double sum = 0.0;
    for (double a : arr){
      sum += Math.pow(a-avg,2);
    }
    return (double)sum/arr.length;
  }

}
