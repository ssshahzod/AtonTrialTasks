package org.atonInternship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                                1. Get entry\s
                                2. Edit existing entity\s
                                3. Delete entity\s
                                Input number: """);
                String input = bufferedReader.readLine();
                int choice = Integer.parseInt(input);
                Commands commands = Commands.values()[choice];

                switch (commands) {
                    case add -> {
                        var entity = new SimpleDBObject();
                        entity.readData(System.in);
                        if (dataBase.insertData(entity) == 0)
                            System.out.println("New entry: \n" + entity + "was successfully added! \n");
                        else
                            System.out.println("Entry already exists!\n");
                    }
                    case get -> {
                        var tmp = readOneField();
                        var res = dataBase.get(tmp);
                        if (res == null)
                            System.out.println("No such entry in database!");
                        else {
                            System.out.println("\nFound entries: \n");
                            res.forEach(System.out::println);
                        }
                    }
                    case edit -> {
                        var tmp = getOne();
                        if(tmp != null){
                            System.out.println("Insert new values: \n");
                            var newEntity = new SimpleDBObject();
                            newEntity.readData(System.in);
                            dataBase.remove(tmp);
                            dataBase.insertData(newEntity);
                        }

                    }
                    case delete -> {
                        var tmp = getOne();
                        if(tmp != null)
                            dataBase.remove(tmp);
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error while reading input data! \n");
            e.printStackTrace();
        }
    }

    public static SimpleDBObject getOne(){
        var tmp = readOneField();
        var res = dataBase.get(readOneField());
        if(res == null)
            System.out.println("No such entries!\n");
        else{
            while(res != null && res.size() > 1) {
                System.out.println("Several entries with such value were found!");
                readOtherFields(tmp);
                res = dataBase.get(tmp);
            }
        }
        if(res == null)
            return null;
        return res.get(0);
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
                readName(res);
            } else if(choice == 1){
                readAccount(res);
            } else if(choice == 2){
                readValue(res);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return res;
    }

    public static void readOtherFields(SimpleDBObject object){
        int sum = 0;
        sum += object.getAccount() != null ? 1 : 0;
        sum += object.getName() != null ? 2 : 0;
        sum += object.getValue() != null ? 4 : 0;

        if(sum == 1 || sum == 4 || sum == 5){
            readName(object);
        } else if(sum == 2 || sum == 6){
            readAccount(object);
        } else if(sum == 3){
            readValue(object);
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