package com.basevaadin.application.constantes;

public class Constante {
    public enum Roles {
        SUPER_USUARIO("SuperUsuario"),
        ADMINISTRADOR("Administrador"),
        USUARIO("Usuario");

        private final String displayName;

        Roles(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
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
