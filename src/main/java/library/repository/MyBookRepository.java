package library.repository;

import library.model.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyBookRepository extends JpaRepository<MyBook, Long> {
    @Query("SELECT mb.book.id FROM MyBook mb")
    List<Long> findAllBookIds();

    Boolean existsByBookId(Long bookId);
}
