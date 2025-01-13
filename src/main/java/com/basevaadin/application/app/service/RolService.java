package com.basevaadin.application.app.service;

import com.basevaadin.application.app.facade.RolFacade;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private final RolFacade rolFacade;

    public RolService(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

}
