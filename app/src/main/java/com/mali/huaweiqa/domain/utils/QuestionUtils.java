package com.mali.huaweiqa.domain.utils;


import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.questions.QuestionsCategory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class QuestionUtils {


    public static ArrayList<Question>  extractQuestionsFromFile(String path, int n){
        ArrayList<Question> questions = new ArrayList<>();

        String questionBody = "", correctAnswer = "";
        ArrayList<String> choices = new ArrayList<>();
        try {
            File myObj = new File(path);
            //Read text from file
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                System.out.println(line);
                if(line.equals("")){
                    if(choices.size() == 4){
                        // find correct Answer index
                        int ind = -1;
                        for (int i = 0;i<4;i++){
                            if(choices.get(i).equals(correctAnswer))
                                ind = i;
                        }
                        // add a new question
                        questions.add(new Question(questionBody, choices, ind, 1));
                        System.out.println(questionBody);
                    }
                    choices.clear();
                }else{
                    if(line.charAt(0) == '#'){
                        questionBody = line.substring(3);
                    }
                    if(line.charAt(0) == '^'){
                        correctAnswer = line.substring(2);
                    }
                    else{
                        choices.add(line.substring(2));
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static void saveQuestionsToDB(ArrayList<Question> questions){

    }

    public static ArrayList<QuestionsCategory>  extractCategoriesFromPath(String path, int n){
        System.out.println("Extractinggg...");
        System.out.println(path);
        System.out.println(System.getProperty("user.dir"));
        ArrayList<QuestionsCategory> categories = new ArrayList<>();
        File root = new File( path );
        File[] list = root.listFiles();

        System.out.println(list);
        if (list == null) return categories;

        for ( File f : list ) {
            System.out.println(f.getName());
            if (!f.isDirectory() ) {
                categories.add(new QuestionsCategory(f.getName(), extractQuestionsFromFile(f.getAbsolutePath(), n)));
            }
        }
        return categories;
    }
}
