package br.com.will.exchange.controllers;

import br.com.will.exchange.model.Exchange;
import br.com.will.exchange.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/exchange-service")
public class ExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeRepository repository;

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Exchange getExchange(
            @PathVariable(value = "amount") BigDecimal amount,
            @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to
    ) {
        var exchange = repository.findByFromAndTo(from, to);
        if (exchange == null) throw new RuntimeException("Currency unsupported");

        var port = environment.getProperty("local.server.port");


        exchange.setConvertedValue(
                exchange.getConversionFactor()
                        .multiply(amount)
                        .setScale(2, RoundingMode.CEILING)
        );
        exchange.setAmount(amount.setScale(2, RoundingMode.CEILING));

        exchange.setEnvironment(port);
        return exchange;

    }
}
