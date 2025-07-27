package mx.edu.utez.rbbackendcomite.models.event;

import java.util.List;

public class AsingnUserToEventDto {
    private Long eventId;
    private List<Long> userIds;

    public AsingnUserToEventDto() {
    }

    public AsingnUserToEventDto(Long eventId, List<Long> userIds) {
        this.eventId = eventId;
        this.userIds = userIds;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
