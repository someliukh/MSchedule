package com.example.mschedule.util;

import com.cemiltokatli.passwordgenerate.Password;
import com.cemiltokatli.passwordgenerate.PasswordType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordGeneration {

    public String generatePassword(PasswordType type, int min, int max) {
        Password password = Password.createPassword(type, min, max);
        return password.generate();
    }

}
