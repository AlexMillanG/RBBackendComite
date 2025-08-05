package mx.edu.utez.rbbackendcomite.controller.event;

import jakarta.validation.Valid;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.event.EventDto;
import mx.edu.utez.rbbackendcomite.services.event.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponseDto> findAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> findById(@PathVariable Long id) {
        return eventService.getOne(id);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponseDto> save(@RequestBody @Valid EventDto payload) {
        return eventService.create(payload);
    }
}
