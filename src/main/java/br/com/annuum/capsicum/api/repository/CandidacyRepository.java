package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Need;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidacyRepository extends CrudRepository<Candidacy, Long> {

    List<Candidacy> findAllByNeed(Need need);

    @Query("SELECT c.* \n" +
        "FROM candidacy c \n" +
        "INNER JOIN need n ON n.id = c.need_id \n" +
        "INNER JOIN movement_needs mn ON mn.needs_id = n.id \n" +
        "INNER JOIN movement m ON m.id = mn.movement_id \n" +
        "WHERE c.status_enum = 'PRESENT' \n" +
        "AND m.movement_status = 'CONCLUDE' \n" +
        "AND c.user_candidate_id = ?1 \n" +
        "AND NOT EXISTS(SELECT * FROM movement_evaluation me WHERE me.candidacy_id=c.id);")
    List<Candidacy> findCandidaciesWithEvaluationDebitsOfVolunteer(Long idVolunteer);

    @Query("SELECT c.* \n" +
        "FROM candidacy c \n" +
        "INNER JOIN movement_needs mn ON mn.needs_id = c.need_id \n" +
        "INNER JOIN movement m ON m.id = mn.movement_id \n" +
        "INNER JOIN user_volunteer uv ON c.user_candidate_id = uv.id \n" +
        "WHERE c.status_enum = 'PRESENT' \n" +
        "AND m.movement_status = 'CONCLUDE' \n" +
        "AND m.user_author_id = ?1 \n" +
        "AND NOT EXISTS(SELECT * FROM volunteer_evaluation ve WHERE ve.candidacy_id=c.id);")
    List<Candidacy> findCandidaciesWithEvaluationDebitsOfMovementAuthor(Long idMovementAuthor);


    boolean existsByNeedIdAndUserCandidateId(Long idNeed, Long idCandidate);
}
