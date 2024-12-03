package library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    @NotEmpty(message = "Hibás cím: a 'title' mező nem lehet üres.")
    private String title;
    @Column(name = "author", nullable = false)
    @NotEmpty(message = "Hibás író: a 'author' mező nem lehet üres.")
    private String author;
    @Column(name = "year", nullable = false)
    @NotNull(message = "Hibás év: a 'year' mező nem lehet üres.")
    @Range(min = 0, max = 2100, message = "Hibás évszám: a 'year' {min} és {max} értékek között kell lennie.")
    private Integer year;
    @Column(name = "price", nullable = false)
    @NotNull(message = "Hibás ár: a 'price' mező nem lehet üres.")
    @Range(min = 0, message = "Hibás ár: a 'price' {min} érték felett kell lennie.")
    private Integer price;
    @Column(name = "available", nullable = false)
    @NotNull(message = "Hibás elérhetőség: a 'available' mező nem lehet üres.")
    private Boolean available;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;
}
