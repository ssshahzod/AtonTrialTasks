package org.atonInternship.Object;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SimpleDBObject implements DBObject {
    private Long account;
    private String name;
    private Double value;

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
}
