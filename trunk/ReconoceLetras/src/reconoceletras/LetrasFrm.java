/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconoceletras;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class LetrasFrm extends javax.swing.JFrame {

    DrawingPanel drawingPanel;
    MousePencil mousePencil;
    int matrixInput[][];
    
    double momentum;
    double maxError;
    double learningRate;
    int maxIterations;
    int hiddenLayers;
    int iterations;
    
    LinkedList<Letra> letras = new LinkedList<Letra>();
    
    /**
     * Creates new form LetrasFrm
     */
    public LetrasFrm() {
        initComponents();


        matrixInput = new int[5][5];

        //resetMatrix();
        
        
    }

    private void resetMatrix()
    {
        for (int i = 0; i < Letra.FILAS; i++) {
            
            for (int j = 0; j < Letra.COLUMNAS; j++) {
                matrixInput[i][j] = 0;
            }            
        }
    }
    
    private TrainingSet getTrainingSet()
    {
        TrainingSet<SupervisedTrainingElement> trainingSet = new TrainingSet<SupervisedTrainingElement>(Letra.COLUMNAS*Letra.FILAS, Letra.SALIDAS);
        for (Letra letra : letras)
        {
            trainingSet.addElement( letra.getTrainedElement() );
        }
        
        return trainingSet;
    }
    
    String getLetterOfOutput(double vector[])
    {
        for (Letra letra : letras)
        {
            if (letra.isVectorEqualTo(vector))
                return letra.getLetra();
        }
                
        return "";
    }
    
    void append2File(String file2Append) {
        
        String letter = txtLetter.getText();
        String strOutput = txtOutput.getText().trim();
        
        
        
        
        if (letter.length() != 1)
            return;
        
        
        
        if (strOutput.length() != Letra.COLUMNAS*2-1)
            return;
        
        StringTokenizer token = new StringTokenizer( txtOutput.getText() );
        while (token.hasMoreTokens())
        {
            token.nextToken();
        }
        System.out.println("letra len: " + letter.length() + " - salida len: " + strOutput.length());
        
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(file2Append, true));
            pw.println(letter + " " + strOutput);
            
            
            for (int i=0;i<Letra.FILAS; i++)
            {
                String strRow = "";
                for (int j=0;j<Letra.COLUMNAS;j++)
                {
                    strRow += matrixInput[i][j] + " ";
                }
                pw.println(strRow.trim());
            }
            
            pw.println("");
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(LetrasFrm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    void readFile(String file2Read) 
    {
        
        try {
            FileInputStream fstream = new FileInputStream(file2Read);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            
            int col = 0;
            int row = 0;
    
            resetMatrix();
            String strMatriz = "";
            //Read File Line By Line
            Letra letra = new Letra();
            int readedNumber = 0;
            while ((strLine = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(strLine, " ");
                
                if (strLine.length() == 2 + Letra.SALIDAS*2-1)
                {
                    letra.setOutputFromRowString(strLine);                    
                }
                else if (strLine.length() == Letra.COLUMNAS*2-1)
                {
    
                    while (tokenizer.hasMoreTokens())
                    {
                        try
                        {
                            readedNumber = Integer.parseInt( tokenizer.nextToken() );
                        } catch (NumberFormatException e) {
                            readedNumber = 0;
                        }
                        matrixInput[row][col] = readedNumber;
                        col++;
                    }
                    row++;
                    col = 0;

                    if (row == Letra.FILAS)
                    {
                        row = 0;

                        letra.setMatrixInput(matrixInput);
                        letras.add(letra);
                        
                        resetMatrix();

                        letra = new Letra();
                    }
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            e.printStackTrace();
            //System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDrawing = new javax.swing.JPanel();
        cmdClose = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtOutput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLetter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmdSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmdSaveTraining = new javax.swing.JButton();
        cmdLoadTraining = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cmdLoadLetters2Train = new javax.swing.JButton();
        cmdAprendizaje = new javax.swing.JButton();
        spinIterations = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLearningRatio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinMaxIterations = new javax.swing.JSpinner();
        txtMomentum = new javax.swing.JTextField();
        spinHiddenLayers = new javax.swing.JSpinner();
        txtMaxError = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmdDetect = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtLetterDetected = new javax.swing.JTextField();
        cmdClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perceptrón Multicapa");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelDrawing.setBackground(new java.awt.Color(204, 204, 204));
        panelDrawing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelDrawingMousePressed(evt);
            }
        });
        panelDrawing.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelDrawingMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout panelDrawingLayout = new javax.swing.GroupLayout(panelDrawing);
        panelDrawing.setLayout(panelDrawingLayout);
        panelDrawingLayout.setHorizontalGroup(
            panelDrawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        panelDrawingLayout.setVerticalGroup(
            panelDrawingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        cmdClose.setText("Cerrar");
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Guardar"));

        txtOutput.setText("0 0 0 1 1");

        jLabel7.setText("Salida:");

        txtLetter.setText("D");

        jLabel1.setText("Letra:");

        cmdSave.setText("Guardar");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLetter, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdSave)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(txtOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Almacenamiento"));

        cmdSaveTraining.setText("Guardar Entrenamiento");
        cmdSaveTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveTrainingActionPerformed(evt);
            }
        });

        cmdLoadTraining.setText("Cargar Entrenamiento");
        cmdLoadTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadTrainingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdSaveTraining, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(cmdLoadTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdLoadTraining)
                .addGap(6, 6, 6)
                .addComponent(cmdSaveTraining)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrenamiento"));

        cmdLoadLetters2Train.setText("Cargar letras");
        cmdLoadLetters2Train.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadLetters2TrainActionPerformed(evt);
            }
        });

        cmdAprendizaje.setText("Aprendizaje");
        cmdAprendizaje.setEnabled(false);
        cmdAprendizaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAprendizajeActionPerformed(evt);
            }
        });

        spinIterations.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spinIterations.setValue(1);

        jLabel6.setText("Capas ocultas:");

        jLabel5.setText("Máximo Iteraciones:");

        jLabel9.setText("Iteraciones:");

        txtLearningRatio.setText("0.5");

        jLabel3.setText("Máximo Error:");

        jLabel2.setText("Momentum:");

        spinMaxIterations.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));

        txtMomentum.setText("0.15");

        spinHiddenLayers.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), Integer.valueOf(1), null, Integer.valueOf(1)));

        txtMaxError.setText("0.1");

        jLabel4.setText("Razón de Aprendizaje:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdLoadLetters2Train, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAprendizaje, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaxError)
                            .addComponent(txtLearningRatio)
                            .addComponent(txtMomentum)
                            .addComponent(spinIterations)
                            .addComponent(spinHiddenLayers)
                            .addComponent(spinMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaxError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLearningRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spinMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spinHiddenLayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(spinIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdLoadLetters2Train)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAprendizaje)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Probar"));

        cmdDetect.setText("Detectar");
        cmdDetect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDetectActionPerformed(evt);
            }
        });

        jLabel8.setText("Letra detectada:");

        txtLetterDetected.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLetterDetected, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdDetect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cmdDetect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLetterDetected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmdClear.setText("Limpiar");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdClose))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelDrawing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cmdClear))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClear))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(panelDrawing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(cmdClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    
    private MultiLayerPerceptron mlPerceptron = null;
    
    private void cmdAprendizajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAprendizajeActionPerformed
        // TODO add your handling code here:
        try
        {
            maxIterations = (Integer)spinMaxIterations.getValue();
            maxError = Double.parseDouble( txtMaxError.getText() );
            learningRate = Double.parseDouble( txtLearningRatio.getText() );
            momentum = Double.parseDouble( txtMomentum.getText() );
            iterations = (Integer)spinIterations.getValue();

            Letra.maxError = maxError;

            hiddenLayers = (Integer)spinHiddenLayers.getValue();
        
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar solo números.");
            return;
        }
        for (Letra letra : letras)
        {
            letra.print();
        }
        
        mlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, Letra.COLUMNAS*Letra.FILAS, hiddenLayers, Letra.SALIDAS);
        
        MomentumBackpropagation mb = new MomentumBackpropagation();
        mb.setNeuralNetwork(mlPerceptron);
        mb.setMomentum( momentum );
        mb.setMaxError( maxError );
        mb.setLearningRate( learningRate );       
        
        mlPerceptron.deleteObservers();
        
        mlPerceptron.setLearningRule(mb);
        
        
        long t1, t2;
        t1 = System.currentTimeMillis();
        
        TrainingSet trainingSet = getTrainingSet();
        //mb.learn( trainingSet );
        for (int c=0;c< iterations;c++)
        {
            mlPerceptron.learn(trainingSet);
        }
        t2 = System.currentTimeMillis();        
        
        testNeuralNetwork(mb.getNeuralNetwork(), trainingSet);
        
        
        
        //mb.getNeuralNetwork().save("red.nnet");
        
        System.out.println("Iteraciones: " + mb.getCurrentIteration());
    }//GEN-LAST:event_cmdAprendizajeActionPerformed

    private final int squareWidth = 40;
    private final int squareHeight = 40;
    
    void checkMouse(java.awt.event.MouseEvent evt)
    {
        Graphics g = panelDrawing.getGraphics();
        int fX = evt.getX();
        int fY = evt.getY();
        
        final int width = squareWidth;
        final int height = squareHeight;
        
        int maxX = width * Letra.COLUMNAS;
        int maxY = height * Letra.FILAS;
        
        int offset = 4;
        
        if (fX < maxX && fY < maxY)
        {
            int i = fX / width;
            int j = fY / height;
            
            int posx = fX % width;
            int posy = fY % height;
            
            if (posx > offset && posy > offset && posx < width - offset && posy < height - offset)
            {
            
                int inix = i * width;
                int iniy = j * height;
                
                matrixInput[j][i] = 1;

                //System.out.println("x: " + i + " y: " + j);

                g.fillRect(inix+1, iniy+1, width-2, height-2);
            }
        }                
    }
    
    private void panelDrawingMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawingMouseDragged
        // TODO add your handling code here:
        checkMouse(evt);

        
    }//GEN-LAST:event_panelDrawingMouseDragged

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        // TODO add your handling code here:
        String strOutput = txtOutput.getText();
        if (!strOutput.matches("^[0-9] [0-9] [0-9] [0-9] [0-9]$"))
        {
            JOptionPane.showMessageDialog(rootPane, "La salida debe tener el formato: # # # # #");
        }
        else if (txtLetter.getText().length() != 1)
            JOptionPane.showMessageDialog(rootPane, "Debes ingresar una sola letra.");
        else
            append2File("letras.txt");
    }//GEN-LAST:event_cmdSaveActionPerformed

    void resetDrawing()
    {
        Graphics g = panelDrawing.getGraphics();
        g.setColor( Color.LIGHT_GRAY );
        g.fillRect(0, 0, panelDrawing.getWidth(), panelDrawing.getHeight());
        g.setColor(Color.WHITE);
        
        
        int x2 = Letra.COLUMNAS*squareWidth;
        for (int i = 0;i<=Letra.FILAS;i++) {
            g.drawLine(0, i*squareHeight, x2, i*squareHeight);
        }
        
        int y2 = Letra.FILAS * squareHeight;
        for (int j = 0; j <= Letra.COLUMNAS; j++) {
            g.drawLine(j*squareWidth, 0, j*squareWidth, y2);
        }
        resetMatrix();        
    }
    
    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        // TODO add your handling code here:
        resetDrawing();
    }//GEN-LAST:event_cmdClearActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        resetDrawing();
    }//GEN-LAST:event_formWindowOpened

    private void cmdLoadLetters2TrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoadLetters2TrainActionPerformed
        // TODO add your handling code here:
        letras.clear();
        readFile("letras.txt");
        
        cmdAprendizaje.setEnabled(true);
        
        System.out.println("Cargados: " + letras.size());
    }//GEN-LAST:event_cmdLoadLetters2TrainActionPerformed

    private void cmdSaveTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveTrainingActionPerformed
        // TODO add your handling code here:
        if (mlPerceptron != null)
            mlPerceptron.save("red.nnet");
        else
            JOptionPane.showMessageDialog(rootPane, "Primero debe cargar o entrenar la red");
    }//GEN-LAST:event_cmdSaveTrainingActionPerformed

    private void cmdDetectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDetectActionPerformed
        // TODO add your handling code here:
        txtLetterDetected.setText("");
        if (mlPerceptron != null &&letras.size() > 0 )
        {
            maxError = Double.parseDouble( txtMaxError.getText() );
            Letra.maxError = maxError;
            testDrawnLetter();
        }
        else if (letras.size() == 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Deben existir letras cargadas.");
        }
    }//GEN-LAST:event_cmdDetectActionPerformed

    private void cmdLoadTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoadTrainingActionPerformed
        // TODO add your handling code here:
        mlPerceptron = (MultiLayerPerceptron)NeuralNetwork.load("red.nnet");
    }//GEN-LAST:event_cmdLoadTrainingActionPerformed

    private void panelDrawingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawingMousePressed
        // TODO add your handling code here:
        checkMouse(evt);
    }//GEN-LAST:event_panelDrawingMousePressed

    private void testDrawnLetter()
    {
        mlPerceptron.setInput( getVector(matrixInput) );
        mlPerceptron.calculate();
        
        double[] networkOutput = mlPerceptron.getOutput();
        System.out.println("Input: " + Arrays.toString( getVector(matrixInput) ));
        System.out.println(" Output: " + Arrays.toString(networkOutput));
        
        //networkOutput
        txtLetterDetected.setText( getLetterOfOutput(networkOutput) );
    }
    
    private double [] getVector(int matrix[][])
    {
        double vector[] = new double[matrix.length*matrix[0].length];
        
        int n = 0;
        for (int i=0;i<matrix.length;i++)
        {
            for (int j=0;j<matrix[i].length;j++)
            {
                vector[n] = matrixInput[i][j];
                n++;
            }
        }
        
        return vector;
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;


                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LetrasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LetrasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LetrasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LetrasFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                LetrasFrm frm = new LetrasFrm();
                frm.setVisible(true);
                frm.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAprendizaje;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdDetect;
    private javax.swing.JButton cmdLoadLetters2Train;
    private javax.swing.JButton cmdLoadTraining;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSaveTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panelDrawing;
    private javax.swing.JSpinner spinHiddenLayers;
    private javax.swing.JSpinner spinIterations;
    private javax.swing.JSpinner spinMaxIterations;
    private javax.swing.JTextField txtLearningRatio;
    private javax.swing.JTextField txtLetter;
    private javax.swing.JTextField txtLetterDetected;
    private javax.swing.JTextField txtMaxError;
    private javax.swing.JTextField txtMomentum;
    private javax.swing.JTextField txtOutput;
    // End of variables declaration//GEN-END:variables
}
