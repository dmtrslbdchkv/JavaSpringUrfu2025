package ru.springboot.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /**
     * Уникальный идентификатор сообщения
     */
    private String uid;

    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;

    /**
     * Имя системы отправителя
     */
    private Systems systemName;

    /**
     * Время создания сообщения
     */
    private String systemTime;

    /**
     * Код ответа
     */
    private Codes code;

    /**
     * Годовая премия
     */
    private Double annualBonus;

    /**
     * Квартальная премия
     */
    private Double quarterlyBonus;

    /**
     * Код ошибки
     */
    private ErrorCodes errorCode;

    /**
     * Сообщение об ошибке
     */
    private ErrorMessages errorMessage;
}