package component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityImportComponent {

    public List <String> importCity() throws Exception{
        File file = new File("C:\\Users\\Богдан\\Apps\\TelegramBot\\cities.txt");
        InputStream inputStream = new FileInputStream(file);
        List<String> cities = new ArrayList <String>();

        Scanner sc = new Scanner(inputStream);
        while(sc.hasNext())
            cities.add(sc.nextLine());
        sc.close();

        return cities;
    }

}
