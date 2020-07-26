package namesurfer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * File: NameSurferModel.java
 * -----------------------------
 * This class represents a Model for data storage according to the MVC design model.
 */
public class NameSurferModel implements NameSurferConstants {

    protected LinkedHashMap<String, int[]> dataBase;

    protected NameSurferModel(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            dataBase = new LinkedHashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] list = line.split(" ");
                int[] numbers = new int[list.length - 1];
                String name = list[0];
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = Integer.parseInt(list[i + 1]);
                }
                dataBase.put(name, numbers);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Sorry couldn't process file, so closing: " + e.getMessage());
        }
    }

}


