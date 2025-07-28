package mx.edu.utez.rbbackendcomite.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/eventtype")
@Tag(name = "Controlador de Event Tyoe" , description = "Operaciones realizadas con los event type")
@SecurityRequirement(name = "barerAuth")
public class eventTypeController {

}
