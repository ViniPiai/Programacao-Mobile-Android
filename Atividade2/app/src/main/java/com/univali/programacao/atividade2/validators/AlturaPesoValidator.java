package com.univali.programacao.atividade2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlturaPesoValidator {
    private Pattern pattern;
    private Matcher matcher;
    private final String numberValidator = "[0-9]{2,3}";

    public AlturaPesoValidator(){
        pattern = Pattern.compile(numberValidator);
    }

    public boolean validate(final String value){
        matcher = pattern.matcher(value);
        return matcher.matches();
    }


}
