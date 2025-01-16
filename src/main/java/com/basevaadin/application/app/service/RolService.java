package com.basevaadin.application.app.service;

import com.basevaadin.application.app.data.DTO.RolDTO;
import com.basevaadin.application.app.facade.RolFacade;
import com.basevaadin.application.app.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    private final RolFacade rolFacade;

    public RolService(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    public List<RolDTO> findAllByOrderByNombreAsc() {
        return Utils.convertToDTOList(rolFacade.findAllByOrderByNombreAsc(), Utils::toDTO);
    }
}
