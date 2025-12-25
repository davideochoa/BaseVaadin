package com.basevaadin.application.app.views.admin.catalogo;

import com.basevaadin.application.app.constantes.Constante;
import com.basevaadin.application.app.data.DTO.RolDTO;
import com.basevaadin.application.app.data.DTO.UsuarioDTO;
import com.basevaadin.application.app.service.RolService;
import com.basevaadin.application.app.service.UsuarioService;
import com.basevaadin.application.app.utils.UtilUI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.log4j.Log4j2;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY_INLINE;

@Log4j2
public class PanelCatalogoUsuario extends VerticalLayout {
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final FormLayout formLayout = new FormLayout();
    private final ComboBox<UsuarioDTO> nombrePropioComboBox = new ComboBox<>(Constante.nombrePropio);
    private final EmailField validEmailField = new EmailField();
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
        formLayout.add(nombrePropioComboBox, validEmailField, usernameTextField, rolComboBox, resetPasswordCheckbox);

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
                validEmailField.setValue(usuarioDTO.getCorreo());
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

        validEmailField.setLabel("Email address");
        validEmailField.getElement().setAttribute("name", "email");
        validEmailField.setErrorMessage("Enter a valid email address");
        validEmailField.setPlaceholder("nombre@dominio.com");
        validEmailField.setClearButtonVisible(true);
        validEmailField.setPrefixComponent(VaadinIcon.ENVELOPE.create());
        validEmailField.setRequiredIndicatorVisible(true);
        validEmailField.setPattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        grabarUsuarioButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        grabarUsuarioButton.addClickListener(event -> {
            usuarioDTO.setNombreUsuario(usernameTextField.getValue());
            usuarioDTO.setCorreo(validEmailField.getValue());
            usuarioDTO.getRoles().add(rolComboBox.getValue());
            usuarioDTO.setEsReseteadoPassword(resetPasswordCheckbox.getValue());

            Notification normalNotification = UtilUI.notification(null,
                    VaadinIcon.CHECK_CIRCLE.create(),
                    "Guardando registro!",
                    null,
                    FlexComponent.Alignment.CENTER,
                    Notification.Position.MIDDLE,
                    5000);

            normalNotification.open();

            if(usuarioDTO.getNombrePropio().length() > 0 &&
                    usuarioDTO.getNombreUsuario().length() > 0 &&
                    usuarioDTO.getCorreo().length() > 0 &&
                    usuarioDTO.getRoles().size() > 0){

                usuarioDTO = usuarioService.save(usuarioDTO);

                if(usuarioDTO != null){
                    if(usuarioDTO.getId() != null){
                        if(usuarioDTO.getId() > 0){
                            Notification successNotification = UtilUI.notification(NotificationVariant.LUMO_SUCCESS,
                                    VaadinIcon.CHECK_CIRCLE.create(),
                                    "Registro guardado!",
                                    null,
                                    FlexComponent.Alignment.CENTER,
                                    Notification.Position.MIDDLE,
                                    5000);

                            successNotification.open();
                            limpiar();
                            usuarioDTO = null;
                        }
                    }
                    else{

                    }
                }
                else{
                    Notification errorNotification = UtilUI.notification(NotificationVariant.LUMO_ERROR,
                            VaadinIcon.CHECK_CIRCLE.create(),
                            "Error al guardar  el registro!",
                            "Contacte al administrador del sistema",
                            FlexComponent.Alignment.CENTER,
                            Notification.Position.MIDDLE,
                            5000);

                    errorNotification.open();
                }


            }
        });

        cancelButton.addClickListener(event -> {
            limpiar();
        });

        setPadding(false);
    }

    public static Button createCloseBtn(Notification notification) {
        Button closeBtn = new Button(VaadinIcon.CLOSE_SMALL.create(),
                clickEvent -> notification.close());
        closeBtn.addThemeVariants(LUMO_TERTIARY_INLINE);

        return closeBtn;
    }

    private void limpiar() {
        nombrePropioComboBox.clear();
        usernameTextField.clear();
        validEmailField.clear();
        rolComboBox.clear();
        resetPasswordCheckbox.clear();
        usuarioDTO = new UsuarioDTO();
    }


    private void getItemsComboBox() {
        nombrePropioComboBox.setItems(usuarioService.findAllByOrderByNombrePropioAsc());
        rolComboBox.setItems(rolService.findAllByOrderByNombreAsc());
    }
}
