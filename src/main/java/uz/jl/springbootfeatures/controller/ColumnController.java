package uz.jl.springbootfeatures.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDTO;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDTO;
import uz.jl.springbootfeatures.dtos.column.ColumnUpdateDTO;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.ColumnService;

import javax.validation.Valid;
import java.util.List;

import static uz.jl.springbootfeatures.controller.ApiController.PATH;

@RestController
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService service;

    @GetMapping(PATH + "/column/all/{id}")
    public List<ColumnGetDTO> getAll(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getAll(id, userDetails.authUser().getId());
    }

    @PostMapping(PATH + "/column")
    public ApiResponse<Void> create(@Valid @RequestBody ColumnCreateDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        service.create(dto, userDetails.authUser().getId());
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/column/{id}")
    public ApiResponse<ColumnGetDTO> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @DeleteMapping(PATH + "/column/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200);
    }

    @PutMapping(PATH + "/column/update")
    public ApiResponse<ColumnGetDTO> update(@Valid @RequestBody ColumnUpdateDTO dto) {
        return new ApiResponse<>(service.update(dto));
    }

}
