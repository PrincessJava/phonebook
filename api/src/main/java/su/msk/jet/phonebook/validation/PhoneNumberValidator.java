package su.msk.jet.phonebook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        return s.matches("^((8|\\+7)[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?)[\\d\\- ]{7,10}$");
    }
}
