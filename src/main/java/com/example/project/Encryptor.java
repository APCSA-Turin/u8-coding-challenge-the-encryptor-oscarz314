package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if(messageLen == 0){
            return 1;
        }

        if (messageLen % rows != 0){
            return (messageLen / rows) + 1;
        }

        return messageLen / rows;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] list = new String[rows][determineColumns(message.length(), rows)];
        int wordCount = 0;

        // package sentence row by row into array
        for(int i = 0; i < list.length; i++){
            for(int j = 0; j < list[0].length; j++){
                if (wordCount < message.length()){
                    list[i][j] = message.substring(wordCount, wordCount + 1); 
                }
                else{
                    list[i][j] = "=";
                }
                wordCount++;
            }
        }

        return list;
    }

    public static String encryptMessage(String message, int rows){
        String[][] list = generateEncryptArray(message, rows);

        String newWord = "";

        // column major order back
        for(int j = 0; j < list[0].length; j++){
            for(int i = 0; i < list.length; i++){
                newWord += list[i][list[0].length - 1 - j];
            }
        }

        return newWord;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] list = generateEncryptArray(encryptedMessage, determineColumns(encryptedMessage.length(), rows));
        String[][] newList = new String[rows][determineColumns(encryptedMessage.length(), rows)];
        String newWord = "";

        // flip back to row major then reverse list
       for (int j = 0; j < list[0].length; j++){
            for (int i = 0; i < list.length; i++){
                newList[j][i] = list[list.length - 1 - i][j];
            }
       }

        for(int i = 0; i < newList.length; i++){
            for(int j = 0; j < newList[0].length; j++){
                if(!newList[i][j].equals("=")){
                    newWord += newList[i][j];
                }
            }
        }

        return newWord;
    }

    public static void main(String[] args) {

        String word = encryptMessage(" cu…s oeiy d rditelsIvot", 4);
        String decryptWord = decryptMessage(" cu…s oeiy d rditelsIvot", 4);
        System.out.println(word);
        System.out.println(decryptWord);      
    }
}