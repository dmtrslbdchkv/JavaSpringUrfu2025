package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.springboot.MySecondTestAppSpringBoot.model.Positions;

import java.util.Calendar;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays, int currentYear) {
        int daysInYear = getCountDaysInYear(currentYear);
        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }

    private int getCountDaysInYear(int currentYear) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, currentYear);
        return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    }
}
