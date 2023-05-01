package org.atonInternship;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import org.atonInternship.Commands.Commands;
import org.atonInternship.Object.SimpleDBObject;
import org.atonInternship.Server.DB;
import org.atonInternship.Server.SimpleDB;

public class Main {
    private static final DB<SimpleDBObject> dataBase = new SimpleDB();
    public static void main(String[] args) {
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("In memory database. \n"
                + "Choose one of the following operations:");
        try{
            while(true) {
                //add account name value
                //edit byAccount/byName/byValue account/name/value account name value
                //delete byAccount/byName/byValue account/name/value
                System.out.print("""
                                0. Add new entity\s
                                1. Edit existing entity\s
                                2. Delete entity\s
                                Input number: """);
                String input = bufferedReader.readLine();
                int choice = Integer.parseInt(input);
                Commands commands = Commands.values()[choice];

                switch (commands) {
                    case add: {
                        var entity = new SimpleDBObject();
                        entity.readData(System.in);
                        dataBase.insertData(entity);
                        System.out.println("New entry: " + entity + " was successfully added!");
                    }
                    case get:{
                        var tmp = readOneField();
                        var res = dataBase.get(tmp);
                        if(res == null)
                            System.out.println("No such entry in database!");
                        else if (res.size() > 1) {
                            System.out.println("Several entries were found! ");
                        }
                    }
                    case edit: {
                        var tmp = dataBase.get(readOneField());
                        System.out.println("Several entries with");
                    }
                    case delete: {

                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error while reading input data! \n");
            e.printStackTrace();
        }
    }

    public static SimpleDBObject readOneField(){
        var res = new SimpleDBObject();
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("""
                                0. By name\s
                                1. By account\s
                                2. By value\s
                                Input number: """);

        try {
            String input = bufferedReader.readLine();
            int choice = Integer.parseInt(input);
            if(choice == 0){
                System.out.print("Input name: ");
                input = bufferedReader.readLine();
                res.setName(input);
            } else if(choice == 1){
                System.out.print("Input account: ");
                input = bufferedReader.readLine();
                res.setAccount(Long.parseLong(input));
            } else if(choice == 2){
                System.out.print("Input value: ");
                input = bufferedReader.readLine();
                res.setValue(Double.valueOf(input));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return res;
    }

    public static void readOtherFields(int amount, SimpleDBObject object){
        var res = new SimpleDBObject();
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input;
            if(object.getName() != null){
                if(object.getAccount() != null){

                }
            } else if(object.getValue() != null){
                System.out.print("Input name: ");
                input = bufferedReader.readLine();
                object.setName(input);
            } else if(object.getAccount() != null){
                System.out.print("Input value: ");
                input = bufferedReader.readLine();
                object.setValue(Double.valueOf(input));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readName(SimpleDBObject object){
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Input name: ");
            String input = bufferedReader.readLine();
            object.setName(input);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void readAccount(SimpleDBObject object){
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Input account: ");
            String input = bufferedReader.readLine();
            object.setAccount(Long.parseLong(input));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void readValue(SimpleDBObject object){
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Input value: ");
            String input = bufferedReader.readLine();
            object.setValue(Double.valueOf(input));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}