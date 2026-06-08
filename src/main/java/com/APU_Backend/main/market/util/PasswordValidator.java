package com.APU_Backend.main.market.util;

public class PasswordValidator {

    public static boolean esPasswordValida(String password) {

        /*
         * esta regex obliga a que la contraseña tenga al menos una minúscula,
         * una mayúscula, un número, un símbolo especial y mínimo 8 caracteres.
         */
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        return password.matches(regex);
    }
}