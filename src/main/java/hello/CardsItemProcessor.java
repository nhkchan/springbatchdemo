package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class CardsItemProcessor implements ItemProcessor<Cards, Cards> {

    private static final Logger log = LoggerFactory.getLogger(CardsItemProcessor.class);

    @Override
    public Cards process(final Cards cards) throws Exception {
        final String name = cards.getName().toUpperCase();
        //final Long cardNumber = cards.getCardNumber();

        log.info("Converting (" + cards.getName() + ") into (" + name + ")");
        cards.setId(cards.getId());
        cards.setName(name);
        cards.setCardNumber(cards.getCardNumber());
        cards.setExpDate(cards.getExpDate());
        log.info("Card Number: " + cards.getCardNumber());
        log.info("Expiry Date: " + cards.getExpDate());
        
        return cards;
        
    }

}