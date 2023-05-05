package org.aton.internship.Runnable;

public class CharacterThread extends Thread{
    private final String characterName;

    public CharacterThread(String name) {
        this.characterName = name;
    }

    public void run(String line){
        if (line.startsWith(characterName + ": ")) {
            System.out.println(characterName + ": " + line.substring((characterName + ": ").length()));
        }
    }
}
