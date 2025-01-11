package com.basevaadin.application.web.views.admin.catalogo;

import com.basevaadin.application.constantes.Constante;
import com.basevaadin.application.data.DTO.UsuarioDTO;
import com.basevaadin.application.service.UsuarioService;
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
    private FormLayout formLayout = new FormLayout();
    private ComboBox<UsuarioDTO> nombrePropioComboBox = new ComboBox<>(Constante.nombrePropio);
    private TextField usernameTextField = new TextField(Constante.nombreUsuario);
    private ComboBox<String> rolComboBox = new ComboBox<>(Constante.rol);
    private Checkbox resetPasswordCheckbox = new Checkbox(Constante.reset_Password);
    private Button grabarUsuarioButton = new Button(Constante.grabar);
    private Button cancelButton = new Button(Constante.cancelar);
    HorizontalLayout buttonLayout = new HorizontalLayout(grabarUsuarioButton, cancelButton);

    private String usuarioNuevo = null;
    private UsuarioDTO usuarioDTO = null;
    public PanelCatalogoUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

        configurarComponentes();
        configurarLayout();
    }

    private void configurarLayout() {
        formLayout.add(nombrePropioComboBox, usernameTextField, rolComboBox, resetPasswordCheckbox);

        add(formLayout, buttonLayout);
    }

    private void configurarComponentes() {
        nombrePropioComboBox.setItemLabelGenerator(UsuarioDTO::getNombrePropio);
        nombrePropioComboBox.setItems(getItemsnombrePropioComboBox());
        nombrePropioComboBox.setAllowCustomValue(true);
        nombrePropioComboBox.setClearButtonVisible(true);
        nombrePropioComboBox.addValueChangeListener(e -> {
            if(e.getValue() != null){
                usuarioDTO = e.getValue();
                usernameTextField.setValue(usuarioDTO.getNombreUsuario());
                //rolComboBox.setValue(usuarioDTO.getRol());
                resetPasswordCheckbox.setValue(usuarioDTO.getEsReseteadoPassword());

            }

        });
        nombrePropioComboBox.addCustomValueSetListener(e -> {
            if(e.getDetail() != null)
                usuarioNuevo = e.getDetail();
            else
                usuarioNuevo = null;
        });

        rolComboBox.setItems(Arrays.stream(Constante.Roles.values())
                .map(Constante.Roles::getDisplayName)
                .toList());


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

    private List<UsuarioDTO> getItemsnombrePropioComboBox() {
        return usuarioService.findAllByOrderByNombrePropioAsc();
    }
}
