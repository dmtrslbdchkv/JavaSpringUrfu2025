package ru.springboot.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.springboot.MyThirdTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
