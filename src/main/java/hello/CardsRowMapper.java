package hello;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CardsRowMapper implements RowMapper<Cards> {
	
	@Override
	public Cards mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cards cards = new Cards();
		
		cards.setId(rs.getInt(1));
		cards.setName(rs.getString(2));
		cards.setCardNumber(rs.getString(3).toString());
		cards.setExpDate(rs.getShort(4));
		return cards;
	}
	
}
