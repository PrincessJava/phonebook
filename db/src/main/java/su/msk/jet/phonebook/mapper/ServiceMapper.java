package su.msk.jet.phonebook.mapper;

import org.apache.ibatis.annotations.*;
import su.msk.jet.phonebook.model.PersonService;

import java.util.List;

public interface ServiceMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "personId", column = "person_id"),
            @Result(property = "description", column = "description")
    })

    @Select("SELECT * FROM user_services WHERE person_id = #{personId}")
    List<PersonService> showServices(int personId);

    @Insert({"INSERT INTO user_services(person_id, id, description) VALUES(#{personId}, #{id}, #{description})"})
    void addService(PersonService personService);

    @Delete("DELETE FROM user_services WHERE person_id = #{personId} AND id = #{id}")
    void removeService(@Param("personId") int personId, @Param("id") int id);

    @Delete("DELETE FROM user_services WHERE person_id = #{personId}")
    void removeAllServices(int personId);
}
