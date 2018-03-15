package su.msk.jet.phonebook.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class PersonTest {

    private static Validator validator;

    @BeforeClass
    public static void setValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameIsNull() {
        Person person = new Person(1, null, "89123456789", null);

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        assertEquals("Имя не может быть пустым", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void nameWithWrongCharacters() {
        Person person = new Person(1, "@#$%^", "89123456789", null);

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        assertEquals("Имя должно содержать только буквы русского или английского алфавита",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void phoneNumberIsNull() {
        Person person = new Person(1, "cat", null, null);

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        assertEquals("Номер телефона должен быть задан", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void phoneNumberIncorrect() {
        Person person = new Person(1, "кот", "123", null);

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        assertEquals(1, constraintViolations.size());
        assertEquals("Номер телефона должен состоять только из цифр, кода страны и города (оператора) в скобках, и разделителей (тире, пробел)",
                constraintViolations.iterator().next().getMessage());
    }
}
