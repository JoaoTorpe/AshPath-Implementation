package com.pdsc.ashpath.selenium.utils;

public class FormErrors {
    public static String UNAUTHORIZED_MSG = "Invalid email/password."; // 401
    public static String PENDING_APPROVAL_MSG = "Your account is pending approval."; // 403

    public static final String FULL_NAME_REQUIRED_MSG = "Full Name is required.";
    public static final String FULL_NAME_MAX_LENGTH_MSG = "Full Name must be less than 64 characters.";
    public static final String FULL_NAME_MIN_LENGTH_MSG = "Full Name must have at least 3 characters.";

    public static final String EMAIL_REQUIRED_MSG = "E-mail is required.";
    public static final String EMAIL_MAX_LENGTH_MSG = "E-mail must be less than 64 characters.";
    public static final String EMAIL_INVALID_MSG = "Enter a valid e-mail address.";

    public static final String SPECIALIZATION_REQUIRED_MSG = "Specialization is required.";
    public static final String SPECIALIZATION_MAX_LENGTH_MSG = "Specialization must be less than 64 characters.";
    public static final String SPECIALIZATION_MIN_LENGTH_MSG = "Specialization must have at least 3 characters.";

    public static final String PASSWORD_REQUIRED_MSG = "Password is required.";
    public static final String PASSWORD_MIN_LENGTH_MSG = "Password must be at least 6 characters.";
    public static final String PASSWORD_MAX_LENGTH_MSG = "Password must be at most 12 characters.";
    public static final String PASSWORD_PATTERN_MSG = "Must include one number, one uppercase letter, and one special character.";

    public static final String REPEAT_PASSWORD_REQUIRED_MSG = "Repeat Password is required.";
    public static final String REPEAT_PASSWORD_MIN_LENGTH_MSG = "Repeat Password must be at least 6 characters.";
    public static final String REPEAT_PASSWORD_MAX_LENGTH_MSG = "Repeat Password must be at most 12 characters.";
    public static final String REPEAT_PASSWORD_MISMATCH_MSG = "Repeat Password must match the password.";
    public static final String REPEAT_PASSWORD_MUST_MATCH_MSG = "This field must match the password field.";
}