/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irtest;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author salas
 */
public class IRTest {
    public static final String DIR_DOC = "D:\\IR_Documents\\doc\\";
    public static final String DIR_STOPWORD = "D:\\IR_Documents\\rule\\stopWord.txt";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           IRTest ir = new IRTest();
           List<String> lstWord = new ArrayList<>();
           List<String> lstWordClear = new ArrayList<>();
           int[][] matrixDocWord;
           Scanner in = new Scanner(System.in);
           String q;
            //Lee el contenido (los archivos) del directorio 
           File[] vctDocuments = ir.readDirectory(DIR_DOC) ;
           
           for(File obj:vctDocuments)
            //lee el contenido del archivo y alamacena en lista la palabras claves 
            lstWord.addAll(ir.keyWord(ir.readText(obj.getAbsolutePath())));
            //java 8 forma de limpir los repetidos de la lista , gnera nueva lista con los terminos 
           lstWordClear = lstWord.stream().distinct().collect(Collectors.toList());
          
           //se arma la matrix documentos vs terminos
           matrixDocWord = ir.matrixDocWord(vctDocuments,lstWordClear);
          
           //Imprimir en pantalla
            lstWordClear.forEach((object) -> {
                System.out.print( " | "+object);
            });
            System.out.println("");
            printArray(matrixDocWord);
            
            //Realizar pregunta : cuál es el caudal del río Danubio
             System.out.println( "Realiza una Pregunta :");
             q = in.nextLine();     
             System.out.println( "Su pregunta es : "+ q);
        } catch (IOException ex) {
            Logger.getLogger(IRTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    /**
     * Metodo que lee el contenido de un directorio y retorna su contenido de archivos (nombre de archivos)(rutas)...
     * @param DirectoryName
     * @return
     * @throws IOException 
     */
    public File[] readDirectory(String DirectoryName) throws IOException{
        File f = new File(DirectoryName);
        return f.listFiles();   
    }        
    /**
     * Metodo que lee el contenido de un archivo
     * @return
     * @throws IOException 
     */
    public String readText(String path) throws IOException{
       return new String(Files.readAllBytes(Paths.get(path)));
    }    
    /**
     * metodo encuentra palabras dentro del contenido de un archivo
     * @param cont
     * @return 
     */
    public List<String> keyWord(String cont) throws IOException{
       String[] word = cont.split(" ");
       List<String> lstKeyWord = new ArrayList<String>();
        for(int i=0,j=0;i<word.length;i++){
          if(!validateStopword(word[i]))  
            lstKeyWord.add(j++,word[i]);
        }
      return lstKeyWord;
    }        
    /**
     * Valida palabras que no agregan valor stopWord
     * @param word
     * @return 
     */
    public boolean validateStopword(String word) throws IOException{
        String[] stopWord = new String(Files.readAllBytes(Paths.get(DIR_STOPWORD))).split(";");
        //String[] stopWord = {"el","los","pasa","por","es","su",",","de","un","si","porque","tiene","mucho","y","tienen","asciende","en"};
        for (String texto : stopWord) {
            if(word.equals(texto))
                return true;
        }       
       return false; 
    }
    
    /**
     * Retorna matriz de 
     * @param documents
     * @param lstWord
     * @return
     * @throws IOException 
     */
    public int[][] matrixDocWord(File[] documents,List<String> lstWord) throws IOException{
     //  int cant = pd.stream().filter(p -> p.equals(t)).collect(Collectors.toList()).size();   
     int[][] matrixDocWord =  new int[documents.length][lstWord.size()];
     List<String> lstWordDoc;
       int i=0;    
       for(File obj:documents){
        lstWordDoc = new ArrayList<>();   
        lstWordDoc = this.keyWord(this.readText(obj.getAbsolutePath()));
         for(int j=0;j<lstWord.size();j++){
            for (String texto : lstWordDoc) {
               if(lstWord.get(j).equals(texto))
                   matrixDocWord[i][j]++;
            }   
        } 
         i++;
       }
      return matrixDocWord;   
     } 
    
    /**
     * imprime matriz 
     * @param matrix 
     */
    public static void printArray(int matrix[][])
    {
       for (int row = 0; row < matrix.length; row++) 
        {
            for (int column = 0; column < matrix[row].length;column++) 
           
                System.out.print(matrix[row][column] + " ");
           
            System.out.println();
        }
    }
     
}
