package com.basevaadin.application.app.utils;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class UtilUI {

    public static Notification notification(NotificationVariant notificationVariant,
                                            Icon icon,
                                            String text,
                                            String descripcion,
                                            FlexComponent.Alignment alignment,
                                            Notification.Position position,
                                            int duration) {
        Notification notification = new Notification();

        if(notificationVariant != null)
            notification.addThemeVariants(notificationVariant);

        HorizontalLayout layout = new HorizontalLayout();

        if(descripcion != null){
            Div uploadSuccessful = new Div(new Text(text));
            Div info = new Div(uploadSuccessful, new Div(new Text(descripcion)));

            layout.add(info);
        }
        else{
            layout.add(icon,new Text(text));
        }

        layout.setAlignItems(alignment);

        notification.add(layout);
        notification.setPosition(position);
        notification.setDuration(duration);

        return notification;
    }


}
