package com.basevaadin.application.language;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addSessionInitListener(sessionEvent -> {
            // Vaadin ya intenta obtener el locale del navegador aquí
            Locale browserLocale = sessionEvent.getRequest().getLocale();

            // Si el idioma del navegador no es español, forzamos inglés
            if (!browserLocale.getLanguage().equals("es")) {
                sessionEvent.getSession().setLocale(Locale.ENGLISH);
            } else {
                sessionEvent.getSession().setLocale(browserLocale);
            }
        });
    }
}
