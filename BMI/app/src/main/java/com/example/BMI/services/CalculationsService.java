package com.example.BMI.services;

public class CalculationsService {
    public double calculateBmi(double height, double weight) {
            return weight / Math.pow(height, 2);
    }
}