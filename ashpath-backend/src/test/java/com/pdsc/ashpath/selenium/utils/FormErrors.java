package com.pdsc.ashpath.selenium.utils;

public class FormErrors {
    public static String UNAUTHORIZED_MSG = "Invalid email/password."; // 401

    public static final String FULL_NAME_REQUIRED = "Full Name is required.";
    public static final String FULL_NAME_MAX_LENGTH = "Full Name must be less than 64 characters.";
    public static final String FULL_NAME_MIN_LENGTH = "Full Name must have at least 3 characters.";

    public static final String EMAIL_REQUIRED = "E-mail is required.";
    public static final String EMAIL_MAX_LENGTH = "E-mail must be less than 64 characters.";
    public static final String EMAIL_INVALID = "Enter a valid e-mail address.";

    public static final String SPECIALIZATION_REQUIRED = "Specialization is required.";
    public static final String SPECIALIZATION_MAX_LENGTH = "Specialization must be less than 64 characters.";
    public static final String SPECIALIZATION_MIN_LENGTH = "Specialization must have at least 3 characters.";

    public static final String PASSWORD_REQUIRED = "Password is required.";
    public static final String PASSWORD_MIN_LENGTH = "Password must be at least 6 characters.";
    public static final String PASSWORD_MAX_LENGTH = "Password must be at most 12 characters.";
    public static final String PASSWORD_PATTERN = "Must include one number, one uppercase letter, and one special character.";

    public static final String REPEAT_PASSWORD_REQUIRED = "Repeat Password is required.";
    public static final String REPEAT_PASSWORD_MIN_LENGTH = "Repeat Password must be at least 6 characters.";
    public static final String REPEAT_PASSWORD_MAX_LENGTH = "Repeat Password must be at most 12 characters.";
    public static final String REPEAT_PASSWORD_MISMATCH = "Repeat Password must match the password.";
    public static final String REPEAT_PASSWORD_MUST_MATCH = "This field must match the password field.";
}