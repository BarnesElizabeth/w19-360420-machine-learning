import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;



public class kNNMain{

	public static void main(String... args) throws FileNotFoundException{
for(int k = 1; k<681; k+=2){	// there are 681 data points in the data set

	


	int repetitions = 1000;
	
	/** (the k value is controlled by the for loop)
	int k = 5;
	Scanner keyboard = new Scanner(System.in);
	System.out.print("k: ");
	
	k = keyboard.nextInt();
	**/

	double[] accuracy = new double[repetitions];
	double[] precision = new double[repetitions];
	double[] recall = new double[repetitions];
	
	for(int r=0; r<repetitions; r++){

    // TASK 1: Point DataSet.readDataSet method to the desired file. 
	List <DataPoint> allTheData = DataSet.readDataSet(args[0]);
		
	String label = allTheData.get(0).getLabel();
	double[] X = allTheData.get(0).getX();	
		
	double train = 0.3; // the % of data to reserve for later
	
    // TASK 2: Split the fullDataSet into Training and Held Out Test Dataset
	List<DataPoint> testData = DataSet.getTestSet(allTheData, train); 
	
	String testLabel = testData.get(0).getLabel();
	double[] testX = testData.get(0).getX();	
		
	List<DataPoint> trainData = DataSet.getTrainingSet(allTheData, (1-train)); //will train with ohter set of data
	
	String trainLabel = trainData.get(0).getLabel();
	double[] trainX = trainData.get(0).getX();	
		
    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.
	
	KNNClassifier myNewClassifier = new KNNClassifier(k);

	String prediction = myNewClassifier.predict(trainData, testData.get(0));
	String answer = testData.get(0).getLabel();
	
	int i = 0;
	
	int benignBenign = 0;			// predicted as benign, actually benign			(true negative)
	int benignMalignant = 0;		// predicted as benign, actually malignant		(false negative)
	int malignantBenign = 0;		// predicted as malignant, actually benign		(false positive)
	int malignantMalignant = 0;		// predicted as malignant, actually malignant	(true positive)
	
	DataSet.printLabelFrequencies(allTheData);
	
	
	int luckyNumber = (int)(Math.random()*testData.size());
		for(i = 0; i<testData.size(); i++){
			prediction = myNewClassifier.predict(trainData, testData.get(i));
//			if(Math.random()>0.5){ 	//to assign predictions randomly
//			if(i == 0 && r==0){	//to control rate of predictions
//			prediction = "malignant";
//			}else{
//			prediction = "benign";
//			}
			answer = testData.get(i).getLabel();
			
//			System.out.print("prediction: " + prediction);
//			System.out.print("\t\t\tanswer: " + answer);
			
			// benign has length 6
			// malignant has length 9
			
			if(prediction.length() == answer.length()){ // check lengths because checking values did not work
//				System.out.println("\t\t\tYES");

				if(answer.length() == "benign".length()){
					benignBenign ++;
				}else{
					malignantMalignant ++;
				}
				
			}else{
//				System.out.println("\t\t\tNO");

				if(answer.length() == "benign".length()){
					malignantBenign ++;
				}else{
					benignMalignant ++;
				}

			}
		
		}
		
	double correct = benignBenign + malignantMalignant;
	double actuallyMalignant = malignantMalignant + benignMalignant;
	double predictedMalignant = malignantMalignant + malignantBenign;
		
		
		
		accuracy[r] = (correct/testData.size());
		precision[r] = (malignantMalignant/predictedMalignant);
		recall[r] =  (malignantMalignant/actuallyMalignant);
		
		
//		System.out.println(correct + " out of " + i);
  
	} // 1000 repetitions
	
/**	
	for(int i=0; i<accuracy.length; i++){
		System.out.print(accuracy[i] + ", ");
	}
*/		
	System.out.print(k + ", " + mean(accuracy) + ", " + standardDeviation(accuracy));
	System.out.print(", " + mean(precision) + ", " + standardDeviation(precision));
	System.out.println(", " + mean(recall) + ", " + standardDeviation(recall));
	
	}
	
  } // main

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
