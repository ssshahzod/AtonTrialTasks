package org.atonInternship.Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class SimpleDBObject implements DBObject {
    private Long account;
    private String name;
    private Double value;

    public SimpleDBObject(){}
    public SimpleDBObject(Long account, String name, Double value){
        this.account = account;
        this.name = name;
        this.value = value;
    }
    public Long getAccount() {
        return account;
    }
    public void setAccount(final Long account) {
        this.account = account;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(final Double value) {
        this.value = value;
    }

    @Override
    public void readData(InputStream inputStream){
        String[] fields = {"account: ", "name: ", "value: "};
        var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String[] line = new String[3];
            for(int i = 0; i <  3; i++){
                System.out.println("Enter " + fields[i]);
                line[i] = bufferedReader.readLine();
                System.out.println("first field: " + Arrays.toString(line) + "\n " );
            }
            this.account = Long.decode(line[0]);
            this.name = line[1];
            this.value = Double.valueOf(line[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return "Account: " + account.toString()
                + "\nName: " + name + "\nValue: "
                + value.toString() + "\n";
    }
}
