/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reconoceletras;

import java.util.StringTokenizer;
import org.neuroph.core.learning.SupervisedTrainingElement;

/**
 *
 * @author Marcelo
 */
public class Letra {
    private int matrixInput[][];
    private double output[];
    
    private String letra = "";
    
    public static final int COLUMNAS = 5;
    public static final int FILAS = 5;
    public static final int SALIDAS = 5;
    
    public Letra()
    {
        matrixInput = new int[FILAS][COLUMNAS];
        output = new double[SALIDAS];
    }
    
    public String vector2String(double vector[])
    {
        String strVector = "";
        for (int i=0;i<vector.length;i++)
        {
            strVector += vector[i] + " ";
        }
        return strVector;
    }
    
    public void print()
    {
        System.out.println( "Letra: "+ letra + " - Salida: " + vector2String(output) + " - Vector letra: " + vector2String(getVector()) );
        
    }
    
    public String getLetra()
    {
        return this.letra;
    }
    
    public void setMatrixInput(int matrix[][])
    {
        for (int i=0;i<matrix.length;i++)
        {
            System.arraycopy(matrix[i], 0, matrixInput[i], 0, matrix.length);
        }
    }
    
    private double [] getVector()
    {
        double vector[] = new double[COLUMNAS*FILAS];
        
        int n = 0;
        for (int i=0;i<FILAS;i++)
        {
            for (int j=0;j<COLUMNAS;j++)
            {
                vector[n] = matrixInput[i][j];
                n++;
            }
        }
        
        return vector;
    }
    
    public void setOutputFromRowString(String rowFormat)
    {
        rowFormat = rowFormat.trim();
        
        StringTokenizer token = new StringTokenizer(rowFormat);
        
        letra = token.nextToken();
        
        int n = 0;
        while (token.hasMoreTokens() && n < SALIDAS)
        {
            output[n] = Integer.parseInt(token.nextToken());
            n++;
        }
    }
    
    public SupervisedTrainingElement getTrainedElement()
    {
        return new SupervisedTrainingElement( getVector(), output);
    }
    
    public void transformaLetra()
    {
        /*
        int col = 0;
        int row = 0;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(strLine, " ");

            while (tokenizer.hasMoreTokens())
            {                    
                int readedNumber = Integer.parseInt( tokenizer.nextToken() );

                matrixInput[row][col] = readedNumber;
                col++;
            }
            row++;

        }        */
    }
    
}
