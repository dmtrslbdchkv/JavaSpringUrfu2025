package ru.springboot.MySecondTestAppSpringBoot.service;

import ru.springboot.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions position, double salary, double bonus, int workDays, int currentYear);
}
