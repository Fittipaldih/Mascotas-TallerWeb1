package com.tallerwebi.presentacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

@Controller

public class ControladorRedSocialTest {

    private ControladorRedSocial controladorRedSocial;

    @BeforeEach
    public void init() {
        // preparacion
     this.controladorRedSocial = new ControladorRedSocial();
    }


}
