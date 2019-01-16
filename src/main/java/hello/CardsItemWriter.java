package hello;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CardsItemWriter implements ItemWriter<Cards> {

	@Override
	public void write(List<? extends Cards> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(items.toString());
	}
	
}
