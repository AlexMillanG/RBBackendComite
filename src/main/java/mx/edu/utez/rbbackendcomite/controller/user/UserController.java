package mx.edu.utez.rbbackendcomite.controller.user;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.user.UserDto;
import mx.edu.utez.rbbackendcomite.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices service;

    @GetMapping
    public ResponseEntity<ApiResponseDto> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> findOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable Long id, @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("usersByGroup/{groupId}")
    public ResponseEntity<ApiResponseDto> getUsersByGroup(@PathVariable Long groupId) {
        return service.getUsersByGroup(groupId);
    }

    @GetMapping("usersByRole/{roleId}")
    public ResponseEntity<ApiResponseDto> getUsersByRole(@PathVariable Long roleId) {
        return service.getUsersByRole(roleId);
    }

}
