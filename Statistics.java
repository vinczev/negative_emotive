/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileoperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class Statistics {

    public static void main(String[] args) {
        File file = new File("d:\\martina_otka\\VV4_szurt\\teljes_VV4\\");
        listFilesForFolder(file);

    }

    public static void listFilesForFolder(final File folder) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String name = fileEntry.toString();
                if (name.endsWith(".out") //&& name.contains("teljes_NE_korpusz")
                        ) {
                    stat(name);

                }
            }
        }
    }

    static String[][][] read(String file) {
        BufferedReader bufferedReader = null;
        String line = null;

        List<String[]> sentence = null;
        List<String[][]> sentences = null;
        sentence = new ArrayList<String[]>();
        sentences = new ArrayList<String[][]>();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));

            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().equals("")) {
                    sentences.add(sentence.toArray(new String[sentence.size()][]));
                    sentence = new ArrayList<String[]>();
                } else {
                    sentence.add(line.split("\t"));
                }
            }
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sentences.toArray(new String[sentence.size()][][]);
    }

    private static void stat(String name) {
        String[][][] dokument = read(name);
        int tokenNum = 0;
        int sentenceNum = 0;
        int nopunct = 0;

        for (String[][] sentence : dokument) {
            sentenceNum++;

          

            for (String[] token : sentence) {

                tokenNum++;
                
                if (!token[2].equals("PUNCT")) {
                    nopunct++;
                }
                
                if (token[2].toLowerCase().equals("elképesztő")) {
                     for (String[] token1 : sentence) {
                         System.out.print(token1[1] + " ");
                     }
                }
            }
            
        }
        System.out.println("");
        System.out.println(name + "\t" + sentenceNum + "\t" + tokenNum + "\t" + nopunct);
    }

}
