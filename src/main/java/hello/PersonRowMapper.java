package hello;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper {

    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String LNAME_COLUMN = "lname";
    public static final String CREDIT_COLUMN = "credit";

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Person person = new Person();
    	
        person.setFirstName(rs.getString(NAME_COLUMN));
        person.setLastName(rs.getString(LNAME_COLUMN));

        return person;
    }
}