package br.com.will.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false, length = 180)
    private String author;

    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @Column(name = "launchDate", nullable = false, length = 120)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(name = "price", nullable = false, length = 120)
    private Double price;

    @Transient
    private String currency;

    @Transient
    private String environment;

    public Book() {
    }

    public Book(Long id, String author, String title, Date launchDate, Double price, String currency, String environment) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.launchDate = launchDate;
        this.price = price;
        this.currency = currency;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(launchDate, book.launchDate) &&
                Objects.equals(price, book.price) &&
                Objects.equals(currency, book.currency) &&
                Objects.equals(environment, book.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, launchDate, price, currency, environment);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", launchDate=" + launchDate +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }
}