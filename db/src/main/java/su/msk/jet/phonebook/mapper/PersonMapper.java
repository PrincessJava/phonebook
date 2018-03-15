package su.msk.jet.phonebook.mapper;

import org.apache.ibatis.annotations.*;
import su.msk.jet.phonebook.model.Person;
import su.msk.jet.phonebook.model.PersonService;

import java.util.List;


public interface PersonMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "username"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "services", javaType = List.class, column = "id", many = @Many(select = "getServices"))
    })
    @Select("SELECT * FROM phone_numbers WHERE id = #{id}")
    Person getPerson(int id);

    @SuppressWarnings("DefaultAnnotationParam")
    @Insert({"INSERT INTO phone_numbers(password, username, phone_number) VALUES(#{password}, #{name}, #{phoneNumber})"})
    @Options(keyProperty = "id", useGeneratedKeys = true, keyColumn = "id")
    void addPerson(Person person);

    @Update("UPDATE phone_numbers SET username = #{name}, phone_number = #{phoneNumber} WHERE id = #{id}")
    void updatePerson(Person person);

    @Delete("DELETE FROM phone_numbers WHERE id = #{id}")
    void removePerson(int id);

    @Results({
            @Result(property = "personId", column = "person_id"),
            @Result(property = "id", column = "id"),
            @Result(property = "description", column = "description")
    })
    @Select("SELECT * FROM user_services WHERE person_id = #{personId}")
    List<PersonService> getServices(int personId);
}
