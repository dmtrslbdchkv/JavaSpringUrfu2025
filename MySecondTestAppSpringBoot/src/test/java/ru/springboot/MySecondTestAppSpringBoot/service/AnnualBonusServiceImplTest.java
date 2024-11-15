package ru.springboot.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.springboot.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.0;
        int year = 2023;

        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays, year);

        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }
}
