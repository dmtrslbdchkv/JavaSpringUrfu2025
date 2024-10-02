package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.springboot.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.springboot.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException {
        if (bindingResult.hasErrors()) {
            if ("123".equals(bindingResult.getFieldValue("uid"))) {
                throw new UnsupportedCodeException(bindingResult.getFieldError().toString());
            }
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}
