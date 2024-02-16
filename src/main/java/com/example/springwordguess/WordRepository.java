package com.example.springwordguess;
import com.example.springwordguess.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface WordRepository extends JpaRepository<Word, Long> {


    @Query(value = "SELECT * FROM tbl_word WHERE level = :level ORDER BY RAND() LIMIT 1", nativeQuery = true)
            Word findRandomWordByLevel(@Param("level") String level);
}
