package mx.edu.utez.rbbackendcomite.models.event;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class EventDto {
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 3, max = 100, message = "El título debe tener entre 3 y 100 caracteres")
    private String title;

    @NotNull(message = "La fecha no debe de ser nula")
    @FutureOrPresent(message = "La fecha debe ser hoy o en el futuro")
    private LocalDate date;

    @NotNull(message = "El id del tipo de evento no puede ser nulo")
    @Positive(message = "El id del tipo de evento debe ser un número positivo")
    private Long typeId;

    @NotNull(message = "El estado no puede estar vacío")
    private EventStatus status;

    @NotNull(message = "El ID del grupo no puede ser nulo")
    @Positive(message = "El ID del grupo debe ser un número positivo")
    private Long groupId;

    private List<@Positive(message = "Cada ID de participante debe ser un número positivo") Long> participantIds;

    public EventEntity toEntity() {
        EventEntity event = new EventEntity();
        event.setTitle(this.title);
        event.setDate(this.date);
        event.setStatus(this.status);
        return event;
    }

    public EventDto(String title, LocalDate date, Long typeId, EventStatus status, Long groupId) {
        this.title = title;
        this.date = date;
        this.typeId = typeId;
        this.status = status;
        this.groupId = groupId;
    }

    public EventDto() {}

    // Getters y Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Long getTypeId() { return typeId; }
    public void setTypeId(Long typeId) { this.typeId = typeId; }

    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }

    public Long getGroupId() { return groupId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }

    public List<Long> getParticipantIds() { return participantIds; }
    public void setParticipantIds(List<Long> participantIds) { this.participantIds = participantIds; }
}
