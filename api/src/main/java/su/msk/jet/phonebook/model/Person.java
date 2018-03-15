package su.msk.jet.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import su.msk.jet.phonebook.validation.PhoneNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {

    @JsonIgnore
    private String password;

    private Integer id;

    @NotBlank(message = "Имя не может быть пустым")
    @Pattern(regexp = "[a-zA-Z[а-яА-Я]]+", message = "Имя должно содержать только буквы русского или английского алфавита")
    private String name;

    @NotBlank(message = "Номер телефона должен быть задан")
    @PhoneNumber
    private String phoneNumber;

    private List<PersonService> services;

    public Person(final Integer id, final String name, final String phoneNumber, final List<PersonService> services) {
        this(UUID.randomUUID().toString(), id, name, phoneNumber, services);
    }

    public Person(final Integer id, final String name, final String phoneNumber) {
        this(UUID.randomUUID().toString(), id, name, phoneNumber, null);
    }

    public void assignUuid() {
        password = UUID.randomUUID().toString();
    }
}
