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
    double maxIterations;
    int hiddenLayers;
    
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
                        int readedNumber = Integer.parseInt( tokenizer.nextToken() );

                        matrixInput[row][col] = readedNumber;
                        System.out.println("r: " + readedNumber);
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
        txtMomentum = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaxError = new javax.swing.JTextField();
        txtLearningRatio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaxIterations = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHiddenLayers = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtOutput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLetter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmdSave = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmdLoadTraining = new javax.swing.JToggleButton();
        cmdSaveTraining = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cmdLoadLetters2Train = new javax.swing.JButton();
        cmdAprendizaje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelDrawing.setBackground(new java.awt.Color(204, 204, 204));
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

        txtMomentum.setText("0.15");

        jLabel2.setText("Momentum:");

        jLabel3.setText("Máximo Error:");

        jLabel4.setText("Razón de Aprendizaje:");

        txtMaxError.setText("0.2");

        txtLearningRatio.setText("0.5");

        jLabel5.setText("Máximo Iteraciones:");

        txtMaxIterations.setText("10");

        jLabel6.setText("Capas ocultas:");

        txtHiddenLayers.setText("100");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        cmdClear.setText("Limpiar");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLetter, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOutput))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdSave)
                    .addComponent(cmdClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Almacenamiento"));

        cmdLoadTraining.setText("Cargar Entrenamiento");
        cmdLoadTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadTrainingActionPerformed(evt);
            }
        });

        cmdSaveTraining.setText("Guardar Entrenamiento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdLoadTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdSaveTraining, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdLoadTraining)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSaveTraining)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrenamiento"));

        cmdLoadLetters2Train.setText("Cargar letras para entrenar");
        cmdLoadLetters2Train.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadLetters2TrainActionPerformed(evt);
            }
        });

        cmdAprendizaje.setText("Aprendizaje");
        cmdAprendizaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAprendizajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdLoadLetters2Train, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAprendizaje, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cmdLoadLetters2Train)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAprendizaje)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaxIterations)
                                    .addComponent(txtHiddenLayers, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtLearningRatio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(52, 52, 52)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaxError, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(134, 134, 134)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDrawing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMomentum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtMaxError, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLearningRatio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtHiddenLayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDrawing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdClose)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdAprendizajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAprendizajeActionPerformed
        // TODO add your handling code here:
        maxIterations = Double.parseDouble(txtMaxIterations.getText());
        maxError = Double.parseDouble( txtMaxError.getText() );
        learningRate = Double.parseDouble( txtLearningRatio.getText() );
        momentum = Double.parseDouble( txtMomentum.getText() );
        
        hiddenLayers = Integer.parseInt( txtHiddenLayers.getText() );
        
        
        
        for (Letra letra : letras)
        {
            letra.print();
        }
        
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, Letra.COLUMNAS*Letra.FILAS, hiddenLayers, Letra.SALIDAS);
        
        MomentumBackpropagation mb = new MomentumBackpropagation();
        mb.setNeuralNetwork(myMlPerceptron);
        mb.setMomentum( momentum );
        mb.setMaxError( maxError );
        mb.setLearningRate( learningRate );       
        
        myMlPerceptron.deleteObservers();
        
        myMlPerceptron.setLearningRule(mb);
        
        
        long t1, t2;
        t1 = System.currentTimeMillis();
        
        TrainingSet trainingSet = getTrainingSet();
        //mb.learn( trainingSet );
        myMlPerceptron.learn(trainingSet);
        
        t2 = System.currentTimeMillis();        
        
        testNeuralNetwork(mb.getNeuralNetwork(), trainingSet);
        
        mb.getNeuralNetwork().save("red.nnet");        
        
        System.out.println("Iteraciones: " + mb.getCurrentIteration());
    }//GEN-LAST:event_cmdAprendizajeActionPerformed

    private final int squareWidth = 40;
    private final int squareHeight = 40;
    
    private void panelDrawingMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDrawingMouseDragged
        // TODO add your handling code here:

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
        
    }//GEN-LAST:event_panelDrawingMouseDragged

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        // TODO add your handling code here:
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
        
        System.out.println("Cargados: " + letras.size());
    }//GEN-LAST:event_cmdLoadLetters2TrainActionPerformed

    private void cmdLoadTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoadTrainingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdLoadTrainingActionPerformed

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
                new LetrasFrm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAprendizaje;
    private javax.swing.JButton cmdClear;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdLoadLetters2Train;
    private javax.swing.JToggleButton cmdLoadTraining;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSaveTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelDrawing;
    private javax.swing.JTextField txtHiddenLayers;
    private javax.swing.JTextField txtLearningRatio;
    private javax.swing.JTextField txtLetter;
    private javax.swing.JTextField txtMaxError;
    private javax.swing.JTextField txtMaxIterations;
    private javax.swing.JTextField txtMomentum;
    private javax.swing.JTextField txtOutput;
    // End of variables declaration//GEN-END:variables
}
