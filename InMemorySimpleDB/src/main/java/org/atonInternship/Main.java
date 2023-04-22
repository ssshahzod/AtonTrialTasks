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
                System.out.println("""
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
                        //dataBase.insertData(entity);
                        System.out.println("New entry: " + entity + " was successfully added!");
                    }
                    case edit: {

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
}