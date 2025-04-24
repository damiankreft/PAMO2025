package com.example.BMI;

import com.example.BMI.services.CalculationsService;

import org.junit.Assert;
import org.junit.Test;

public class CalculationsTests {
    @Test
    public void get_bmi_returns_valid_bmi_value()
    {
        // arrange
        var isWoman = false;
        var weight = 73.0;
        var height = 180;
        var age = 25;

        // act
        var bmi = CalculationsService.getBenedictHarrisBasalMetabolicRate(isWoman, weight, height, age);

        // assert
        Assert.assertEquals(1802, bmi, 20);
    }
}
