package com.basevaadin.application.constantes;

import lombok.Data;

@Data
public class Constante {
    public enum Roles {
        SUPER_USUARIO("SuperAdministrador","superadmin"),
        ADMINISTRADOR("Administrador","admin"),
        USUARIO("Usuario","user");

        private final String displayName;
        private final String value;

        Roles(String displayName,String value) {
            this.displayName = displayName;
            this.value = value;
        }

        public String getDisplayName(){
            return displayName;
        }

        public String getValue() {
            return value;
        }
    }

    public static final String nombrePropio = "Nombre";
    public static final String nombreUsuario = "Nombre de Usuario";
    public static final String rol = "Rol";
    public static final String reset_Password = "Reset Password";
    public static final String grabar = "Grabar";
    public static final String cancelar = "Cancelar";
    public static final String titleCatalogo = "Catalogos";
    public static final String routeCatalogo = "catalogos";
    public static final int dos = 2;

}
