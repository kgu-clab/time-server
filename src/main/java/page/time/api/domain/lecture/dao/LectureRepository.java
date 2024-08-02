package page.time.api.domain.lecture.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import page.time.api.domain.lecture.domain.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureRepositoryCustom {
}
