/**Класс, реализующий функции для чат-бота*/
/**Автор Колосова Е.К.*/
package org.example.chatbot;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class Chatbot implements IBot{

    //тип указатель на метод
    public interface FunctionPointer {
        void methodSignature() throws IOException;
    }

    /**Вспомогательная строка для ответа*/
    private String ans;

    /**Вспомогательная строка для хранения входной строки*/
    private String inp;

    /**Массив регулярных выражений*/
    public String[] Reg =new String[]{"(.* )?[П,п]ривет(.*)?", "(.* )?[К, к]оторый (сейчас )?час(.*)?", "(.* )?\\d+( )?\\+( )?\\d+(.*)?", "(.* )?[К, к]урс доллара(.*)?"};

    /**Массив ссылок на методы, соотретсвующие регулярным выражениям из массива Reg*/
    public FunctionPointer[] functionPointersArray = new FunctionPointer[]{this::Hello, this::CurrentTime, this::sum, this::weather};

    /**Имя пользователя*/
    private String UserName;


    /**История сообщений*/
    private ArrayList<String> History;

    /**Конструктор без параметров*/
    public Chatbot(){
        UserName="";
        History=new ArrayList<String>();}

    /**Конструктор с параметрами */
    public Chatbot(String Name, ArrayList<String> H){
        UserName = Name;
        History = H;
    }

    /**Возвращает имя пользователя*/
    public String get_UserName(){
        return this.UserName;
    }

    /**Задаёт имя пользователя*/
    public void set_UserName(String Name){
        this.UserName=Name;
    }

    /**Возвращает историю сообщений*/
    public ArrayList<String> get_History(){
        return this.History;
    }

    /**Задаёт историю сообщений*/
    public void set_History(ArrayList<String> H){
        History=H;
    }

    /**Ответ на приветствие*/
    public void Hello(){
        ans= "Привет, "+this.get_UserName()+"!";
    }

    /**Определяет текущее время*/
    public void CurrentTime(){
        java.time.LocalTime currentTime = java.time.LocalTime.now();
        ans = currentTime.toString().substring(0,5);
    }

    /**Добавить строку M в инсторию*/
    public void AddMessage(String M){
        History.add(M);
    }


    /**Отформатировать сообщение M для занесения в историю. type true - сообщение от бота, false - сообщение от пользователя*/
    public String FormatMessage(String M, boolean type){
        java.time.LocalTime currentTime = java.time.LocalTime.now();
        String a1 = currentTime.toString().substring(0,5);
        if (type==false){
            return a1+" "+this.get_UserName()+": "+M;
        }
        else
            return a1+" "+"Bot: "+M;
    }

    /**Запись истории в файл*/
    public void WriteToFileHistory(){
        String s=UserName+".txt"; //имя файла
        try {
            FileWriter FHistory = new FileWriter(s); //выводимая строка
            for (String string : History) {
                FHistory.write(string);
            }
            FHistory.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**Запись истории из файла*/
    public void FillingFromFileHistory() {
        String s = UserName + ".txt"; //имя файла
            try {
                BufferedReader reader = new BufferedReader(new FileReader(s));
                String line = reader.readLine(); //строка, в которую считывается  текст
                while (line != null) {
                    History.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**Возвращает целочисленную сумму a и b*/
    public void sum() {
         String text2 = inp;
         Pattern number = Pattern.compile("\\d+");
         Matcher number_matcher = number.matcher( text2 );
         if ( number_matcher.find() ){
             int a=Integer.parseInt(number_matcher.group());
             if ( number_matcher.find() ){
                 int b=Integer.parseInt(number_matcher.group());
                 int c=a+b;
                 ans=String.valueOf(c);
            }
         }
    }


    private static String downloadWebPage(String url) throws IOException {

        //StringBuilder result = new StringBuilder();
        String result="";
        String result1="";
        String line;

        Pattern number = Pattern.compile("(/*)?<div class=\"fintool_button\" id=\"ft_52148\"><div class=\"content\"><div class=\"info\"><div class=\"title\"><img src=\"/img/fin_up.gif\" width=\"10\" height=\"10\" align=\"absmiddle\" />USD</div><div class=\"value\">\\d{2},\\d{4}</div>(/*)?");
        Pattern number1 = Pattern.compile("(/*)?\\d{2},\\d{4}(/*)?");
        URLConnection urlConnection = new URL(url).openConnection();

        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            while ((line = br.readLine()) != null) {
                Matcher number_matcher = number.matcher(line);
                if (number_matcher.find()) {
                    result = number_matcher.group();
                    Matcher number_matcher1 = number1.matcher(result);
                    if (number_matcher1.find()) {
                        result1 = "1$ = "+ number_matcher1.group()+"Р";
                        return result1;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return result1;

    }

    public void weather() throws IOException {
        ans = downloadWebPage("https://www.finmarket.ru/currency/rates/");
    }

    @Override public String Answer(String input) throws IOException {
        ans="";
        inp=input;
        for (int i = 0; i < Reg.length; i++) {
            if ( input.matches(Reg[i]) ){
                functionPointersArray[i].methodSignature(); //вызов соответствующего метода
                AddMessage(FormatMessage(input, false));
                AddMessage("\n");
                AddMessage(FormatMessage(ans, true));
                AddMessage("\n\n");
                WriteToFileHistory();
                return toString();
            }
        }
        ans="Извините, я это не знаю";
        AddMessage(FormatMessage(input, false));
        AddMessage("\n");
        AddMessage(FormatMessage(ans, true));
        AddMessage("\n\n");
        WriteToFileHistory();
        return toString();
    }

    /**Возвращает историю в строковом формате*/
    public String toString() {
        String line="";
        for (int i=0;i<History.size();i++){
            line=line+History.get(i);
        }
        return line;
    }

}
