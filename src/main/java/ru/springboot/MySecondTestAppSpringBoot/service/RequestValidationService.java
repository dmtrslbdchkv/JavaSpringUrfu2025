package ru.springboot.MySecondTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.springboot.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.springboot.MySecondTestAppSpringBoot.exception.ValidationFailedException;

import java.util.Objects;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError("uid");
            if (fieldError != null && Objects.equals(fieldError.getRejectedValue(), "123")) {
                log.error("uid is 123, throw UnsupportedException");
                throw new UnsupportedCodeException(bindingResult.getFieldError().toString());
            }
            log.error("bindingResult has errors, throw ValidationFailedException");
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}
