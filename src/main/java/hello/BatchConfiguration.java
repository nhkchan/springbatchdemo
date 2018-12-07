package hello;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	EntityManagerFactory emf;
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private LineAggregator<VGSales> createVGSalesLineAggregator() {
        DelimitedLineAggregator<VGSales> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
 
        FieldExtractor<VGSales> fieldExtractor = createVGSalesFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
 
        return lineAggregator;
    }
 
    private FieldExtractor<VGSales> createVGSalesFieldExtractor() {
        BeanWrapperFieldExtractor<VGSales> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"rank", "name", "platform","year","genre","publisher","naSales","euSales","jpSales","otherSales","globalSales"});
        return extractor;
    }    
    
    ItemReader<Person> jdbcReader() {
    	JdbcCursorItemReader<Person> dbReader = new JdbcCursorItemReader<>();
    	
		return null;
    }
    
    // tag::readerwriterprocessor[]
    public ItemReader<VGSales> reader() {
    		FlatFileItemReader<VGSales> reader = new FlatFileItemReader<>();
    		reader.setResource(new FileSystemResource("//Users//ken//Downloads//vgsales.csv"));
    		reader.setLinesToSkip(1);
            reader.setLineMapper(new DefaultLineMapper<VGSales>() {{
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setNames(new String[] {"rank", "name", "platform","year","genre","publisher","naSales","euSales","jpSales","otherSales","globalSales"});
                }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<VGSales>() {{
                    setTargetType(VGSales.class);
                }});
            }});
    		return reader;
    }

    @Bean
    public VGSalesProcessor processor() {
        return new VGSalesProcessor();
    }

    @Bean
	public FlatFileItemWriter<VGSales> flatFileItemWriter() {
		FlatFileItemWriter<VGSales> flatFileItemWriter = new FlatFileItemWriter<>();
		flatFileItemWriter.setShouldDeleteIfExists(true);
		flatFileItemWriter.setResource(new FileSystemResource("//Users//ken//Downloads//vgsales_writer.csv"));
		
		LineAggregator<VGSales> lineAggregator = createVGSalesLineAggregator();
		flatFileItemWriter.setLineAggregator(lineAggregator);
		return flatFileItemWriter;
	}
    
    @Bean
    public JpaItemWriter<VGSales> jpaWriter() {
    	
    	JpaItemWriter<VGSales> jpaWriter = new JpaItemWriter<>();
    	jpaWriter.setEntityManagerFactory(emf);
    	
		return jpaWriter;
    	
    }
    
    // end::readerwriterprocessor[]
    
    @Bean
/*    Job personEtl(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            FlatFileItemReader<Person> reader,
            JdbcBatchItemWriter<Person> writer) {
*/
    Job personEtl(JobBuilderFactory jobBuilderFactory,
    StepBuilderFactory stepBuilderFactory,
    JdbcCursorItemReader<Person> reader) {
    
    	Step step = stepBuilderFactory.get("database-reader")
    			.<Person, Person>chunk(5)
    			.reader(reader)
    			.build();
    	
    	Job job = jobBuilderFactory.get("etl")
        .start(step)
        .build();
    	
    	return job;
    	
    }
    
    
    // Working Job/Step implementation
    // tag::jobstep[]
    /*
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<VGSales, VGSales> chunk(10)
            .reader(reader())
            .processor(processor())
            //.writer(flatFileItemWriter())
            .writer(jpaWriter())
            .build();
    }
    */
    // end::jobstep[]
}