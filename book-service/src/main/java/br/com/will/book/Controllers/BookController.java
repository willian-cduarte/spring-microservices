package br.com.will.book.Controllers;

import br.com.will.book.integrations.ExchangeFeignClient;
import br.com.will.book.integrations.ExchangeResponse;
import br.com.will.book.model.Book;
import br.com.will.book.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("book-service")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    Environment environment;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ExchangeFeignClient exchangeFeignClient;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBookById(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "currency") String currency
    ) {

        logger.info(String.format("find book by id= %d", id));

        var optBook = bookRepository.findById(id);

        if (optBook.isEmpty())
            throw new RuntimeException("Book with id = " + id + " not be found");

        var book = optBook.get();

/*
        var book = bookRepository.getById(id);

        if (book == null)
            throw new RuntimeException("Book with id = " + id + " not be found");
*/
        /*
        Map<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);
        */

        logger.info(String.format("get exchange currency for value = %f", book.getPrice()));

        /*
        var exchange = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/exchange-service/{amount}/{from}/{to}",
                        ExchangeResponse.class,
                        params
                );
        */

        var exchange = exchangeFeignClient.getExchange(
                BigDecimal.valueOf(book.getPrice()),
                "USD",
                currency
        );

        book.setPrice(exchange.getConvertedValue());
        book.setCurrency(currency);
        var port = environment.getProperty("local.server.port");

        book.setEnvironment("Book port "+ port + " Exchange port " + exchange.getEnvironment());
        logger.info(String.format("response : %s", book.toString()));
        return book;
    }
}
