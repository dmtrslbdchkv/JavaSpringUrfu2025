package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.springboot.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class QuarterBonusServiceImpl implements QuarterBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays, int currentYear, int quarter) {
        if (!position.isManager()) {
            throw new IllegalArgumentException("Квартальная премия только для менеджеров");
        }

        int daysInYearQuarter = getCountDaysInYearQuarter(currentYear, quarter);
        return salary * bonus * daysInYearQuarter * position.getPositionCoefficient();
    }

    private int getCountDaysInYearQuarter(int currentYear, int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Номер квартала должен быть от 1 до 4");
        }

        int answer = 0;
        for (int month = (quarter - 1) * 3 + 1; month <= (quarter - 1) * 3 + 3; month++) {
            answer += Year.of(currentYear).atMonth(month).lengthOfMonth();
        }
        return answer;
    }
}
