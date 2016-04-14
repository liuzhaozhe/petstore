package com.petstore.web.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;


public class ValidationCodeValidator extends FieldValidatorSupport {

    private String sessionValidationCode;
    private boolean trim = true;

    public String getSessionValidationCode() {
        return sessionValidationCode;
    }

    public void setSessionValidationCode(String sessionValidationCode) {
        this.sessionValidationCode = sessionValidationCode;
    }

    public boolean isTrim() {
        return trim;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

    @Override
    public void validate(Object object) throws ValidationException {
        Object objValidationCode = this.getFieldValue(sessionValidationCode, object);
        String fieldName = this.getFieldName();
        Object value = this.getFieldValue(fieldName, object);
        if (!(value instanceof String)) {
            this.addFieldError(fieldName, object);
        } else {
            String s = (String) value;
            if (this.trim) {
                s = s.trim();
            }

            if (s.length() == 0) {
                this.addFieldError(fieldName, object);
                return;
            }
            if (!value.equals(objValidationCode)) {
                this.addFieldError(fieldName, object);
            }
        }
    }
}
