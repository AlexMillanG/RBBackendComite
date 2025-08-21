package mx.edu.utez.rbbackendcomite.models.userHasEvents;

import mx.edu.utez.rbbackendcomite.models.event.EventEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipantEntity, Long> {
    Optional<EventParticipantEntity> findByEventAndUser(EventEntity event, UserEntity user);
    List<EventParticipantEntity> findByUser(UserEntity user);

}
