package com.basevaadin.application.web.views.admin.catalogo;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Catalogos")
@Route(value = "catalogos")
@Menu(icon = "line-awesome/svg/globe-solid.svg")
@RolesAllowed("ADMIN")
public class CatalogoView extends Div {
    TabSheet tabSheet = new TabSheet();

    public CatalogoView() {
        tabSheet.add("Dashboard",
                new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment",
                new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping",
                new Div(new Text("This is the Shipping tab content")));
        add(tabSheet);
    }
}
