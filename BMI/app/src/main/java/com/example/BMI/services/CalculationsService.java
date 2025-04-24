package com.example.BMI.services;

public class CalculationsService {
    public double calculateBmi(double height, double weight) {
            return weight / Math.pow(height, 2);
    }

    public static double getBenedictHarrisBasalMetabolicRate(boolean isWoman, double weight, double height, int age) {
        double bmr;
        if (isWoman) {
            bmr = 655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age);
        }
        else {
            bmr = 66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age);
        }
        return bmr;
    }
}