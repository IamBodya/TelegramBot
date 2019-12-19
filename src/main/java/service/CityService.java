package service;

import component.CityImportComponent;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CityService {

    private List <String> cities;
    private String currentCity;
    private char lastChar;

    public String startGame() throws Exception {
        cities = new CityImportComponent().importCity();
        currentCity = cities.get(ThreadLocalRandom.current().nextInt(cities.size()));
        lastChar = currentCity.charAt(currentCity.length()-1);
        check();
        prompt();
        return currentCity;
    }

    public void answer(String city) {
        if(Character.toLowerCase(city.charAt(0)) == lastChar){
            if(cities.contains(city)) {
                currentCity = city;
                lastChar = currentCity.charAt(currentCity.length()-1);
                check();
                prompt();
            }
        }
    }

    private void prompt() {
        System.out.println(currentCity);
        System.out.println("Тебе на " + lastChar);
    }

    private void check(){
        if (lastChar == 'ы' || lastChar == 'ь'){
            lastChar = currentCity.charAt(currentCity.length() - 2);
        }
    }

}
