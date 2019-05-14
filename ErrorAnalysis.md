# K nearest neighbours
## 360-420-DW, Section 2
## Elizabeth Barnes

## Distributions of Model Accuracy

<p>
	Lines 148-150 of DataSet.java call on the shuffle method, 
	which rearranges the order of the DataPoints, 
	so eah time a different 30% of the data is reserved for the test group. 
	Since it is working with different training and test data each time, 
	the predictions and results will change too.	
</p>
<p>
	Line 200 of DataSet,java calls a method to figure out the labels of the most similar data points to the one currently being tested. 
	The most simple baseline to compare the model to would be using a k value of 1, so that it only looks for the most simmilar point, 
	wihtout analyzing the other ones nearby to come up with a potentially better guess.
	As shown in graph 1, this gives the lowest average, of 95.5% ± 1.6.
	Graph 1 also shows that by increasing the value of k, it is possible to generate reletively more accurate predictions.
</p>


## Analysis of different error types
<p>
	In the case of the breast cancer data set, there are two possible mistakes that the cassifier can make.
	<ul>
		<li>false positive: giving the prediction of "malignant" to a tumor that is actually benign </li>
		<li>false negative: giving the prediction of "benign" to a tumor that is actually malignant </li>
	</ul>
</p>
<p>
	Precision compares the number of correct malignant predictions to the total number of malignant predictions.
	The baseline precision value when k=1 is 94.3% ± 5.1. 
	It improves as k increases, as seen in graph 2.
</p>
<p>
	Recall compares the number of correct malignant predictions to the total number of malignant cases.
	The baseline recall value when k=1 is 92.5% ± 9.3.
	As seen in graph 3, it has a maximal value when k=3 and decreases as k increases past 3.
</p>

```java
	accuracy[r] = (correct *100/testData.size());
	precision[r] = (malignantMalignant*100/predictedMalignant);
	recall[r] =  (malignantMalignant*100/actuallyMalignant);
end
```

<p>
	If the classifier were able to perfectly predict each diagnosis, then there would be no difference between the two values,
	since they would both be equal to 1. 
	The changes in all three analytical measures (accuracy, precision and recall) are affected by the value of k.
</p>
<img src="G1_accuracy.png">
<img src="G2_precision.png">
<img src="G3_recall.png">


## Results
<p>
	The calculated accuracy, precision and recall were printed to the console so that they could be copied into excel to produce the graphs included above.
</p>

```java	
	System.out.print(k + ", " + mean(accuracy) + ", " + standardDeviation(accuracy));
	System.out.print(", " + mean(precision) + ", " + standardDeviation(precision));
	System.out.println(", " + mean(recall) + ", " + standardDeviation(recall));
end
```

