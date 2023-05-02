package org.aton.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.aton.internship.Runnable.CharacterThread;

public class Main {
    public static void main(String[] args) {
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        CharacterThread chandler = new CharacterThread("Chandler");
        CharacterThread joey = new CharacterThread("Joey");
        CharacterThread monica = new CharacterThread("Monica");
        CharacterThread phoebe = new CharacterThread("Phoebe");
        CharacterThread rachel = new CharacterThread("Rachel");
        CharacterThread ross = new CharacterThread("Ross");
        System.out.println("\nType Exit to finish \n");

        try {
            String line = bufferedReader.readLine();
            while(!line.equals("Exit")){
                chandler.run(line);
                joey.run(line);
                monica.run(line);
                phoebe.run(line);
                rachel.run(line);
                ross.run(line);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}