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

public class PanelCatalogoUsuario extends VerticalLayout {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final FormLayout formLayout = new FormLayout();
    private final ComboBox<UsuarioDTO> nombrePropioComboBox = new ComboBox<>(Constante.nombrePropio);
    private final TextField usernameTextField = new TextField(Constante.nombreUsuario);
    private final ComboBox<RolDTO> rolComboBox = new ComboBox<>(Constante.rol);
    private final Checkbox resetPasswordCheckbox = new Checkbox(Constante.reset_Password);
    private final Button grabarUsuarioButton = new Button(Constante.grabar);
    private final Button cancelButton = new Button(Constante.cancelar);
    HorizontalLayout buttonLayout = new HorizontalLayout(grabarUsuarioButton, cancelButton);

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
        rolComboBox.setAllowCustomValue(false);
        rolComboBox.setClearButtonVisible(false);

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
            if(e.getDetail() != null) {
                usuarioDTO = new UsuarioDTO();
                usuarioDTO.setNombrePropio(e.getDetail());
            }
            else
                usuarioDTO = new UsuarioDTO();
        });

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        grabarUsuarioButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        grabarUsuarioButton.addClickListener(event -> {
            String nombreUsuario = usernameTextField.getValue();
            RolDTO rolDTO = rolComboBox.getValue();
            boolean valorReset = resetPasswordCheckbox.getValue();


        });

        cancelButton.addClickListener(event -> {
            nombrePropioComboBox.clear();
            usernameTextField.clear();
            rolComboBox.clear();
            resetPasswordCheckbox.clear();
        });

        setPadding(false);
    }

    private void getItemsComboBox() {
        nombrePropioComboBox.setItems(usuarioService.findAllByOrderByNombrePropioAsc());
        rolComboBox.setItems(rolService.findAllByOrderByNombreAsc());
    }
}
