package br.com.will.book.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "exchange-service")
public interface ExchangeFeignClient {

    @GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
    public ExchangeResponse getExchange(
            @PathVariable(value = "amount") BigDecimal amount,
            @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to
    );
}
