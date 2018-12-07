package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class VGSalesProcessor implements ItemProcessor<VGSales, VGSales> {
	
	private static final Logger log = LoggerFactory.getLogger(VGSalesProcessor.class);

	@Override
	public VGSales process(VGSales vgsales) throws Exception {
		String na = "N/A";
		//log.info("Year= " + vgsales.getYear());
		
		if (vgsales.getYear().equals(na)) {
			log.info(vgsales.getYear() + " Encountered");
			VGSales transformed = new VGSales();
			transformed.setRank(vgsales.getRank());
			transformed.setName(vgsales.getName());
			transformed.setPlatform(vgsales.getPlatform());
			transformed.setYear("NOT TODAY");
			transformed.setGenre(vgsales.getGenre());
			transformed.setPublisher(vgsales.getPublisher());
			transformed.setNaSales(vgsales.getNaSales());
			transformed.setEuSales(vgsales.getEuSales());
			transformed.setJpSales(vgsales.getJpSales());
			transformed.setOtherSales(vgsales.getOtherSales());
			transformed.setGlobalSales(vgsales.getGlobalSales());
			return transformed;
		}
		else {
			return vgsales;
		}
		
		
	}

}
