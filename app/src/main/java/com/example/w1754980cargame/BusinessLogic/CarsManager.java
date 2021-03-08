package com.example.w1754980cargame.BusinessLogic;

import android.content.Context;
import android.util.Log;

import com.example.w1754980cargame.Models.Car;
import com.example.w1754980cargame.R;

import java.util.ArrayList;
import java.util.Random;

public class CarsManager {

    public ArrayList<Car> cars;

    private ArrayList<Car> usedCars = new ArrayList<Car>(30);
    private Context context;

    public CarsManager(Context context) {
        this.context = context;
    }

    // Public methods

    public void createCars() {
        String[] carNames = context.getResources().getStringArray(R.array.names);

        cars = new ArrayList<Car>(30);

        for (String carName: carNames) {
            String lowerCasedCarName = carName.toLowerCase();
            Car car = new Car(lowerCasedCarName, lowerCasedCarName);
            cars.add(car);
        }
    }

    public Car getRandomCar() {
        // https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java/21726085
        Car randomCar = cars.get(new Random().nextInt(cars.size()));

        if (cars.isEmpty()) {
            return null;
        }

        if (usedCars.isEmpty()) {
            cars.remove(randomCar);
            usedCars.add(randomCar);
            return randomCar;
        } else {
            if (usedCars.contains(randomCar)) {
                getRandomCar();
                return null;
            } else {
                usedCars.add(randomCar);
                cars.remove(randomCar);
                return randomCar;
            }
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public String[] getCarNames() {
        int counter = 0;
        String[] names = new String[30];

        if ((cars.size() != 0) && (cars.size() == 30)) {
            for (Car car: cars) {
                names[counter] = car.getName();
                counter++;
            }
        } else {
            System.out.println("FAILEDDDDDD");
            return null;
        }

        return names;
    }
}
