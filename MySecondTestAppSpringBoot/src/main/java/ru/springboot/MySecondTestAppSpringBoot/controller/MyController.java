package ru.springboot.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.springboot.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.springboot.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.springboot.MySecondTestAppSpringBoot.model.*;
import ru.springboot.MySecondTestAppSpringBoot.service.*;
import ru.springboot.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;
    private final QuarterBonusService quarterBonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifyComplexRequestService") ModifyRequestService modifyRequestService,
                        AnnualBonusService annualBonusService,
                        QuarterBonusService quarterBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
        this.quarterBonusService = quarterBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("request = {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemName(request.getSystemName())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("response created: {}", response);

        try {
            validationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            return generateErrorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ValidationFailedException e) {
            return generateErrorResponse(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return generateErrorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setAnnualBonus(annualBonusService.calculate(
                request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays(),
                Year.now().getValue()));

        if (request.getPosition().isManager()) {
            response.setQuarterlyBonus(quarterBonusService.calculate(
                    request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays(),
                    Year.now().getValue(),
                    getCurrentQuarter()
            ));
        }

//        modifyRequestService.modify(request);

        Response modifiedResponse = modifyResponseService.modify(response);

        log.info("response modify: {}", modifiedResponse);

        return new ResponseEntity<>(modifiedResponse, HttpStatus.OK);
    }

    private ResponseEntity<Response> generateErrorResponse(Response response, ErrorCodes code, ErrorMessages message,
                                                           HttpStatus status) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(code);
        response.setErrorMessage(message);
        log.info("add response error data: {}", response);
        return new ResponseEntity<>(response, status);
    }

    private int getCurrentQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int monthValue = calendar.get(Calendar.MONTH);
        return monthValue / 3;
    }
}
