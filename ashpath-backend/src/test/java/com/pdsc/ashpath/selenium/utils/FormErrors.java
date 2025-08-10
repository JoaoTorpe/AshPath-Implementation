package com.pdsc.ashpath.selenium.utils;

public class FormErrors {
    public static String UNAUTHORIZED_MSG = "email/password Inválido."; // 401
    public static String PENDING_APPROVAL_MSG = "Sua conta está pendente de aprovação."; // 403

    public static final String FULL_NAME_REQUIRED_MSG = "Full Name is required.";
    public static final String FULL_NAME_MAX_LENGTH_MSG = "Full Name must be less than 64 characters.";
    public static final String FULL_NAME_MIN_LENGTH_MSG = "O nome completo deve conter pelo menos três caracteres";

    public static final String EMAIL_REQUIRED_MSG = "E-mail is required.";
    public static final String EMAIL_INVALID_MSG = "Digite um endereço de e-mail válido.";

    public static final String SPECIALIZATION_REQUIRED_MSG = "Specialization is required.";
    public static final String SPECIALIZATION_MAX_LENGTH_MSG = "Specialization must be less than 64 characters.";
    public static final String SPECIALIZATION_MIN_LENGTH_MSG = "A especialização deve ter pelo menos 3 caracteres.";

    public static final String PASSWORD_REQUIRED_MSG = "Password is required.";
    public static final String PASSWORD_MIN_LENGTH_MSG = "A senha deve conter pelo menos 6 caracteres.";
    public static final String PASSWORD_MAX_LENGTH_MSG = "A senha deve conter no máximo 12 caracteres.";
    public static final String PASSWORD_PATTERN_MSG = "A senha deve conter um número, uma letra maiúscula e um caractere especial.";

    public static final String REPEAT_PASSWORD_REQUIRED_MSG = "Repeat Password is required.";
    public static final String REPEAT_PASSWORD_MIN_LENGTH_MSG = "A confirmação de senha deve conter pelo menos 6 caracteres.";
    public static final String REPEAT_PASSWORD_MAX_LENGTH_MSG = "Repeat Password must be at most 12 characters.";
    public static final String REPEAT_PASSWORD_MISMATCH_MSG = "As senhas precisam ser iguais.";
    public static final String REPEAT_PASSWORD_MUST_MATCH_MSG = "This field must match the password field.";

    // CreateDeceased form errors
    public static final String DECEASED_FULLNAME_MIN_LENGTH_MSG = "O nome completo deve conter pelo menos três caracteres.";
    public static final String DECEASED_BIRTH_DATE_REQUIRED_MSG = "A data de nascimento é obrigatória.";
    public static final String DECEASED_BIRTH_DATE_INVALID_MSG = "A data de nascimento é inválida.";
    public static final String DECEASED_BIRTH_DATE_NO_FUTURE_MSG = "A data de nascimento não pode ser futura.";
    public static final String DECEASED_DEATH_DATE_REQUIRED_MSG = "A data de falecimento é obrigatória.";
    public static final String DECEASED_DEATH_DATE_INVALID_MSG = "A data de falecimento é inválida.";
    public static final String DECEASED_DEATH_DATE_NO_FUTURE_MSG = "A data de falecimento não pode ser futura.";
    public static final String DECEASED_DEATH_DATE_AFTER_BIRTH_MSG = "A data de falecimento deve ser posterior à data de nascimento.";
    public static final String DECEASED_CAUSE_OF_DEATH_REQUIRED_MSG = "A causa da morte é obrigatória.";
    public static final String DECEASED_CAUSE_OF_DEATH_MIN_LENGTH_MSG = "A causa da morte deve conter pelo menos três caracteres.";
    public static final String DECEASED_FATHER_NAME_MIN_LENGTH_MSG = "O nome do pai deve conter pelo menos três caracteres.";
    public static final String DECEASED_MOTHER_NAME_MIN_LENGTH_MSG = "O nome da mãe deve conter pelo menos três caracteres.";
    public static final String DECEASED_DEATH_CERTIFICATE_REQUIRED_MSG = "O atestado de óbito é obrigatório.";
    public static final String DECEASED_FILE_SELECTION_MSG = "Por favor, selecione o atestado de óbito.";
    public static final String DECEASED_SUCCESS_MSG = "Falecido registrado com sucesso!";
}