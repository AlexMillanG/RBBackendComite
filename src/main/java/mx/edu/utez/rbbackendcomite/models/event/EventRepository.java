package mx.edu.utez.rbbackendcomite.models.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository <EventEntity, Long> {
    List<EventEntity> findByStatus(EventStatus status);
    List<EventEntity> findByGroupId(Long groupId);
    List<EventEntity> findByTypeId(Long typeId);
}
