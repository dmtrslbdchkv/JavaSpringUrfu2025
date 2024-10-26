package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.springboot.MySecondTestAppSpringBoot.model.Request;

@Service
@Qualifier("ModifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSource("service-1-source");
    }
}
