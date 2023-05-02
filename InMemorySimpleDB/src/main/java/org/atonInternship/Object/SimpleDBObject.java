package org.atonInternship.Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;


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

    public int fillness() {
        int sum = 0;
        sum += this.getAccount() != null ? 1 : 0;
        sum += this.getName() != null ? 2 : 0;
        sum += this.getValue() != null ? 4 : 0;
        return sum;
    }

    @Override
    public void readData(InputStream inputStream){
        String[] fields = {"account: ", "name: ", "value: "};
        var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String[] line = new String[3];
            for(int i = 0; i <  3; i++){
                System.out.print("Enter " + fields[i]);
                line[i] = bufferedReader.readLine();
            }
            this.account = Long.decode(line[0]);
            this.name = line[1];
            this.value = Double.valueOf(line[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final SimpleDBObject dbObject)) return false;
        if (!Objects.equals(account, dbObject.account)) return false;
        if (!Objects.equals(name, dbObject.name)) return false;
        return Objects.equals(value, dbObject.value);
    }


    @Override
    public String toString(){
        return "Account: " + account.toString()
                + "\nName: " + name + "\nValue: "
                + value.toString() + "\n";
    }
}
