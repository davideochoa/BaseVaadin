package com.basevaadin.application.app.views.admin.catalogo;

import com.basevaadin.application.app.constantes.Constante;
import com.basevaadin.application.app.data.DTO.RolDTO;
import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.service.RolService;
import com.basevaadin.application.app.service.UsuarioService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;
import java.util.List;

public class PanelCatalogoUsuario extends VerticalLayout {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private FormLayout formLayout = new FormLayout();
    private ComboBox<UsuarioDTO> nombrePropioComboBox = new ComboBox<>(Constante.nombrePropio);
    private TextField usernameTextField = new TextField(Constante.nombreUsuario);
    private ComboBox<RolDTO> rolComboBox = new ComboBox<>(Constante.rol);
    private Checkbox resetPasswordCheckbox = new Checkbox(Constante.reset_Password);
    private Button grabarUsuarioButton = new Button(Constante.grabar);
    private Button cancelButton = new Button(Constante.cancelar);
    HorizontalLayout buttonLayout = new HorizontalLayout(grabarUsuarioButton, cancelButton);

    private String usuarioNuevo = null;
    private UsuarioDTO usuarioDTO = null;
    public PanelCatalogoUsuario(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;

        configurarComponentes();
        configurarLayout();
    }

    private void configurarLayout() {
        formLayout.add(nombrePropioComboBox, usernameTextField, rolComboBox, resetPasswordCheckbox);

        add(formLayout, buttonLayout);
    }

    private void configurarComponentes() {
        getItemsComboBox();

        rolComboBox.setItemLabelGenerator(RolDTO::getNombre);
        nombrePropioComboBox.setAllowCustomValue(false);
        nombrePropioComboBox.setClearButtonVisible(false);

        nombrePropioComboBox.setItemLabelGenerator(UsuarioDTO::getNombrePropio);
        nombrePropioComboBox.setAllowCustomValue(true);
        nombrePropioComboBox.setClearButtonVisible(true);
        nombrePropioComboBox.addValueChangeListener(e -> {
            if(e.getValue() != null){
                usuarioDTO = e.getValue();
                usernameTextField.setValue(usuarioDTO.getNombreUsuario());
                rolComboBox.setValue(usuarioDTO.getRoles().iterator().next());
                resetPasswordCheckbox.setValue(usuarioDTO.getEsReseteadoPassword());

            }

        });
        nombrePropioComboBox.addCustomValueSetListener(e -> {
            if(e.getDetail() != null)
                usuarioNuevo = e.getDetail();
            else
                usuarioNuevo = null;
        });

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        grabarUsuarioButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        grabarUsuarioButton.addClickListener(event -> {

        });

        cancelButton.addClickListener(event -> {

        });

        setPadding(false);
    }

    private void getItemsComboBox() {
        nombrePropioComboBox.setItems(usuarioService.findAllByOrderByNombrePropioAsc());
        rolComboBox.setItems(rolService.findAllByOrderByNombreAsc());
    }
}
