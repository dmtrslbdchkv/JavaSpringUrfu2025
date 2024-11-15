package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.springboot.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface QuarterBonusService {
    double calculate(Positions position, double salary, double bonus, int workDays, int currentYear, int quarter);
}
