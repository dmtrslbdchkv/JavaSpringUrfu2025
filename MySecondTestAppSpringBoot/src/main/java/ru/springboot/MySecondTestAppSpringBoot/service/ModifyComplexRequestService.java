package ru.springboot.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.springboot.MySecondTestAppSpringBoot.model.Request;


@Service
@Qualifier("ModifyComplexRequestService")
public class ModifyComplexRequestService implements ModifyRequestService {

    private final ModifyRequestService sourceService;
    private final ModifyRequestService systemNameService;
    private final ModifyRequestService systemTimeService;

    public ModifyComplexRequestService(
            @Qualifier("ModifySourceRequestService") ModifyRequestService sourceService,
            @Qualifier("ModifySystemNameRequestService") ModifyRequestService systemNameService,
            @Qualifier("ModifySystemTimeRequestService") ModifyRequestService systemTimeService
    ) {
        this.sourceService = sourceService;
        this.systemNameService = systemNameService;
        this.systemTimeService = systemTimeService;
    }


    @Override
    public void modify(Request request) {
        sourceService.modify(request);
        systemTimeService.modify(request);
        systemNameService.modify(request);
    }
}
