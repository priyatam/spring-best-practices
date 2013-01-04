package github.priyatam.springrest.integration;

import github.priyatam.springrest.domain.Policy;

/**
 * Third Party Integration Service
 */
public interface QuoteGenerator {

    Integer generateQuote(Policy policy);

}
