package mx.edu.utez.rbbackendcomite.controller.group;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.group.GroupDto;
import mx.edu.utez.rbbackendcomite.services.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    @PostMapping
    public ResponseEntity<ApiResponseDto> save(@RequestBody GroupDto dto){
        return service.save(dto);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getOne(@PathVariable Long id){
        return service.getOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Long id){
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable Long id, @RequestBody GroupDto dto){
        return service.update(id, dto);
    }
}
