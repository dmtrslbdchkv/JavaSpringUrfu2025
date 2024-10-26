package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.springboot.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
