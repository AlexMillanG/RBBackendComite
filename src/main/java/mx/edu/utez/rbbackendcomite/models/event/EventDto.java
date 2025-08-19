package mx.edu.utez.rbbackendcomite.models.event;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class EventDto {
    @NotBlank(message = "El titulo no puede er vacío")
    @Size(min = 3, max = 100, message = "El titulo debe tener entre 3 y 100 carcteres")
    private String title;

    @NotNull(message = "LA fecha no debe de ser nula")
    @FutureOrPresent(message = "LA fecha debe ser hoy o en el futuro")
    private LocalDate date;

    @NotNull(message = "El id del tipo de evento no puede ser nulo")
    @Positive(message = "El id del tipo de evento debe ser un numero positivo")
    private Long typeId;

    @NotBlank(message = "El estado no puede estar vacío")
    private String status;

    @NotNull(message = "El ID del grupo no puede ser nulo")
    @Positive(message = "El ID del grupo debe ser un numero positivo")
    private Long groupId;

    private List<@Positive(message = "cada ID de participante debe ser un numero positivo") Long> participantIds;

    public EventEntity toEntity() {
        EventEntity event = new EventEntity();
        event.setTitle(this.title);
        event.setDate(this.date);
        event.setStatus(EventStatus.valueOf(this.status));
        return event;
    }

    public EventDto(String title, LocalDate date, Long typeId, String status, Long groupId) {
        this.title = title;
        this.date = date;
        this.typeId = typeId;
        this.status = status;
        this.groupId = groupId;
    }

    public EventDto() {
    }

    public @NotBlank(message = "El titulo no puede er vacío") @Size(min = 3, max = 100, message = "El titulo debe tener entre 3 y 100 carcteres") String getTitle() {
        return title;
    }

    public void setTitle(
            @NotBlank(message = "El titulo no puede er vacío") @Size(min = 3, max = 100, message = "El titulo debe tener entre 3 y 100 carcteres") String title) {
        this.title = title;
    }

    public @NotNull(message = "LA fecha no debe de ser nula") @FutureOrPresent(message = "LA fecha debe ser hoy o en el futuro") LocalDate getDate() {
        return date;
    }

    public void setDate(
            @NotNull(message = "LA fecha no debe de ser nula") @FutureOrPresent(message = "LA fecha debe ser hoy o en el futuro") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "El id del tipo de evento no puede ser nulo") @Positive(message = "El id del tipo de evento debe ser un numero positivo") Long getTypeId() {
        return typeId;
    }

    public void setTypeId(
            @NotNull(message = "El id del tipo de evento no puede ser nulo") @Positive(message = "El id del tipo de evento debe ser un numero positivo") Long typeId) {
        this.typeId = typeId;
    }

    public @NotBlank(message = "El estado no puede estar vacío") String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "El estado no puede estar vacío") String status) {
        this.status = status;
    }

    public @NotNull(message = "El ID del grupo no puede ser nulo") @Positive(message = "El ID del grupo debe ser un numero positivo") Long getGroupId() {
        return groupId;
    }

    public void setGroupId(
            @NotNull(message = "El ID del grupo no puede ser nulo") @Positive(message = "El ID del grupo debe ser un numero positivo") Long groupId) {
        this.groupId = groupId;
    }
}
