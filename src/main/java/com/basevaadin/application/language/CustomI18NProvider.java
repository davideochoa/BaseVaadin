package com.basevaadin.application.language;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class CustomI18NProvider implements I18NProvider {

    @Override
    public List<Locale> getProvidedLocales() {
        // Define los idiomas que soporta tu app
        return List.of(Locale.ENGLISH, new Locale("es"));
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        // Si el locale es nulo o no está soportado, usamos Inglés por defecto
        if (locale == null) {
            locale = Locale.ENGLISH;
        }

        String value;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n/messages", locale);
            value = bundle.getString(key);
        } catch (MissingResourceException e) {
            // Fallback duro a inglés
            ResourceBundle bundle = ResourceBundle.getBundle("i18n/messages", Locale.ENGLISH);
            value = bundle.getString(key);
        }

        if (params.length > 0) {
            return String.format(value, params);
        }
        return value;
    }
}
