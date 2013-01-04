package github.priyatam.springrest.integration;

import github.priyatam.springrest.domain.Policy;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("quoteGenerator")
public class LocalQuoteGenerator implements QuoteGenerator {

    public Integer generateQuote(Policy policy) {
       return new Random().nextInt(2000);
    }
}
