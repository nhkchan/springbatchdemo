package hello;

import java.sql.Timestamp;
import java.time.Instant;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	
	@Autowired
	public DataSource datasource;
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    JobLauncher jl;
    
    CardsItemReader cir = new CardsItemReader();
    
    @Bean
    public Job cardEtl(JobBuilderFactory jobBuilderFactory,
    StepBuilderFactory stepBuilderFactory) throws UnexpectedInputException, ParseException, Exception {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	Instant instant = timestamp.toInstant();
    	Step step = stepBuilderFactory.get("database-reader-" + instant.getEpochSecond())
    			.<Cards, Cards>chunk(10)
    			.reader(cir.jdbcReader(datasource))
    			.processor(new CardsItemProcessor())
    			.writer(new CardsItemWriter())
    			.build();
    	
    	log.info("Step Name: " + step.getName());
    	
    	Job job = jobBuilderFactory.get("pcf-sb-etl")
    			.incrementer(new RunIdIncrementer())
    			.flow(step)
    			.end()
		        .build();
    	
    	log.info("Job Name: " + job.getName());
    	return job;
    }
    
    
}