package com.basevaadin.application.app.views.admin.catalogo;

import com.basevaadin.application.app.constantes.Constante;
import com.basevaadin.application.app.service.RolService;
import com.basevaadin.application.app.service.UsuarioService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle(Constante.titleCatalogo)
@Route(Constante.routeCatalogo)
@Menu(order = Constante.dos, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@RolesAllowed("ADMIN")
public class CatalogoView extends Div {
    private TabSheet tabSheet = new TabSheet();

    public CatalogoView(UsuarioService usuarioService, RolService rolService) {
        tabSheet.add("Usuario", new PanelCatalogoUsuario(usuarioService, rolService));
        tabSheet.add("Payment",
                new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping",
                new Div(new Text("This is the Shipping tab content")));
        add(tabSheet);
    }


}
