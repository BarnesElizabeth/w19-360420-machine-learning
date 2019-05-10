import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label

	List <DataPoint> allTheData = DataSet.readDataSet(args[0]);
		
			String label = allTheData.get(0).getLabel();
			double[] X = allTheData.get(0).getX();	
			
				for(int i=0; i<X.length; i++){ 
			
					System.out.print(X[i] +"\t");
		
				}
				System.out.println(label);
			


    //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset
	List<DataPoint> testData = DataSet.getTestSet(allTheData, 0.8); //will train with 80% of data
	String testLabel = testData.get(0).getLabel();
	double[] TestX = testData.get(0).getX();	
			
				for(int i=0; i<TestX.length; i++){ 
			
					System.out.print(TestX[i] +"\t");
		
				}
				System.out.println(testLabel);


    // TASK 3: Use the DataSet class methods to plot the 2D data (binary and multi-class)



    // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
    // and returns the Euclidean distance between those two points (as a double)
	double[] X1 = testData.get(0).getX();
	double[] X2 = testData.get(1).getX();
	double d = DataSet.distanceEuclid(X1, X2);
	
	System.out.println(d);


    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print a predicted target label



    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.


  }

}
