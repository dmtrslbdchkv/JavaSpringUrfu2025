package ru.springboot.MySecondTestAppSpringBoot.service;

import ru.springboot.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {
    double calculate(Positions position, double salary, double bonus, int workDays, int currentYear, int quarter);
}
