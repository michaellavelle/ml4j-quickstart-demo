package org.ml4j.nn.demo.util;

import org.ml4j.MatrixFactory;
import org.ml4j.nn.neurons.Neurons;
import org.ml4j.nn.neurons.NeuronsActivation;
import org.ml4j.nn.neurons.NeuronsActivationImpl;
import org.ml4j.nn.neurons.format.NeuronsActivationFormat;
import org.ml4j.nn.quickstart.demos.SimpleTwoLayerNetworkMnistTrainingDemo;
import org.ml4j.util.DoubleArrayMatrixLoader;

/**
 * Mnist data loader.
 * 
 * @author Michael Lavelle
 *
 */
public class MnistData {
	
	private MnistData() {		
	}

	public static NeuronsActivation loadTrainingData(MatrixFactory matrixFactory) {

		DoubleArrayMatrixLoader loader = new DoubleArrayMatrixLoader(SimpleTwoLayerNetworkMnistTrainingDemo.class.getClassLoader());
		// Load Mnist data into double[][] matrices
		float[][] trainingDataMatrix = toFloatArray(loader.loadDoubleMatrixFromCsv("mnist2500_X_custom.csv",
				new PixelFeaturesMatrixCsvDataExtractor(), 0, 1000));		

		return new NeuronsActivationImpl(new Neurons(trainingDataMatrix[0].length, false),
				matrixFactory.createMatrixFromRows(trainingDataMatrix).transpose(),
				NeuronsActivationFormat.ROWS_SPAN_FEATURE_SET, true);
	}
	
	public static NeuronsActivation loadTestSetData(MatrixFactory matrixFactory) {

		DoubleArrayMatrixLoader loader = new DoubleArrayMatrixLoader(SimpleTwoLayerNetworkMnistTrainingDemo.class.getClassLoader());
		// Load Mnist data into double[][] matrices
		float[][] trainingDataMatrix = toFloatArray(loader.loadDoubleMatrixFromCsv("mnist2500_X_custom.csv",
				new PixelFeaturesMatrixCsvDataExtractor(), 1000, 2000));		

		return new NeuronsActivationImpl(new Neurons(trainingDataMatrix[0].length, false),
				matrixFactory.createMatrixFromRows(trainingDataMatrix).transpose(),
				NeuronsActivationFormat.ROWS_SPAN_FEATURE_SET, true);
	}

	public static NeuronsActivation loadTrainingLabels(MatrixFactory matrixFactory) {
		DoubleArrayMatrixLoader loader = new DoubleArrayMatrixLoader(SimpleTwoLayerNetworkMnistTrainingDemo.class.getClassLoader());
		// Load Mnist data into double[][] matrices
		float[][] testDataMatrix = toFloatArray(loader.loadDoubleMatrixFromCsv("mnist2500_labels_custom.csv",
				new SingleDigitLabelsMatrixCsvDataExtractor(), 0, 1000));

		return new NeuronsActivationImpl(new Neurons(testDataMatrix[0].length, false),
				matrixFactory.createMatrixFromRows(testDataMatrix).transpose(),
				NeuronsActivationFormat.ROWS_SPAN_FEATURE_SET, true);
	}
	
	public static NeuronsActivation loadTestSetLabels(MatrixFactory matrixFactory) {
		DoubleArrayMatrixLoader loader = new DoubleArrayMatrixLoader(SimpleTwoLayerNetworkMnistTrainingDemo.class.getClassLoader());
		// Load Mnist data into double[][] matrices
		float[][] testDataMatrix = toFloatArray(loader.loadDoubleMatrixFromCsv("mnist2500_labels_custom.csv",
				new SingleDigitLabelsMatrixCsvDataExtractor(), 1000, 2000));

		return new NeuronsActivationImpl(new Neurons(testDataMatrix[0].length, false),
				matrixFactory.createMatrixFromRows(testDataMatrix).transpose(),
				NeuronsActivationFormat.ROWS_SPAN_FEATURE_SET, true);
	}

	public static float[][] toFloatArray(double[][] data) {
		float[][] result = new float[data.length][data[0].length];
		for (int r = 0; r < data.length; r++) {
			for (int c = 0; c < data[0].length; c++) {
				result[r][c] = (float) data[r][c];
			}
		}
		return result;
	}
}
