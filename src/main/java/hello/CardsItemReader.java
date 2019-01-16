package hello;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardsItemReader {
	
	private static final Logger log = LoggerFactory.getLogger(CardsItemReader.class);
	
	@Bean
    public JdbcCursorItemReader<Cards> jdbcReader(DataSource datasource) throws UnexpectedInputException, ParseException, Exception {
    	String sql = "SELECT ID,NAME,CARD_NUMBER,EXPIRY_DATE FROM CARDS";
    	JdbcCursorItemReader<Cards> jcir = new JdbcCursorItemReader<>();
    	jcir.setDataSource(datasource);
    	jcir.setSql(sql);
    	jcir.setRowMapper(new CardsRowMapper());
    	ExecutionContext executionContext=new ExecutionContext();
    	jcir.open(executionContext);
    	jcir.read();
    	jcir.close();
    	log.info(jcir.getDataSource().toString());
    	log.info(jcir.getSql());
    	return jcir;
    }

}
