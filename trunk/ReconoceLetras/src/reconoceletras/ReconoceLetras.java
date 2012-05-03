/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconoceletras;

import java.util.Arrays;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author Marcelo
 */
public class ReconoceLetras {

    public static void main(String[] args) {

        // create training set (logical XOR function)
        TrainingSet<SupervisedTrainingElement> trainingSet = new TrainingSet<SupervisedTrainingElement>(2, 1);
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{0, 0}, new double[]{0}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{0, 1}, new double[]{1}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{1, 0}, new double[]{1}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{1, 1}, new double[]{0}));

        
        // perceptron multi capa
        // entrada: 2
        // oculto: 3
        // salidas: 1
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);
        // learn the training set
        
        
        MomentumBackpropagation mb = new MomentumBackpropagation();
        mb.setNeuralNetwork(myMlPerceptron);
        mb.setMomentum(0);
        mb.setMaxError(0.001);
        mb.setLearningRate(0.5);
        //mb.setMaxIterations(10000);

        long t1,t2;
        t1 = System.currentTimeMillis();
        mb.learn(trainingSet);
        t2 = System.currentTimeMillis();
        
        System.out.println("tiepo de entrenamiento: " + (t2 - t1) + " ms");
        //myMlPerceptron.learn(trainingSet);
        
        // test perceptron
        System.out.println("Testing trained neural network");
        //testNeuralNetwork(myMlPerceptron, trainingSet);
        testNeuralNetwork(mb.getNeuralNetwork(), trainingSet);

        System.out.println("Iteraciones: " + mb.getCurrentIteration());
        // save trained neural network
        //myMlPerceptron.save("myMlPerceptron.nnet");
        mb.getNeuralNetwork().save("myMlPerceptron.nnet");

        // load saved neural network
        NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("myMlPerceptron.nnet");

        // test loaded neural network
        System.out.println("Testing loaded neural network");
        testNeuralNetwork(loadedMlPerceptron, trainingSet);

    }

    public static void testNeuralNetwork(NeuralNetwork nnet, TrainingSet tset) {

        TrainingSet<TrainingElement> trainset = tset;
        for (TrainingElement trainingElement : trainset.elements()) {

            nnet.setInput(trainingElement.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));

        }

    }
}
