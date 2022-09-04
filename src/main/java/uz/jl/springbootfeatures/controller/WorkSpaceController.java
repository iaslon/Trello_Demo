package uz.jl.springbootfeatures.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.domains.WorkSpace;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceCreateDTO;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceGetDTO;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceUpdateDTO;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.WorkSpaceService;

import javax.validation.Valid;
import java.util.List;

import static uz.jl.springbootfeatures.controller.ApiController.PATH;

@RestController
@RequiredArgsConstructor
public class WorkSpaceController {

    private final WorkSpaceService service;

    @GetMapping(PATH + "/workspace")
    public ApiResponse<List<WorkSpaceGetDTO>> getAll(@AuthenticationPrincipal UserDetails userDetails){
        return new ApiResponse<>(service.getAll(userDetails.authUser().getId()));
    }

    @PostMapping(PATH + "/workspace")
    public ApiResponse<Void> create(@Valid @RequestBody WorkSpaceCreateDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails){
        service.create(dto,userDetails.authUser());
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/workspace/{id}")
    public ApiResponse<WorkSpaceGetDTO> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @DeleteMapping(PATH + "/workspace/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200);
    }

    @PutMapping(PATH+"/workspace/update")
    public ApiResponse<WorkSpace> update(@Valid @RequestBody WorkSpaceUpdateDTO dto){
        return service.update(dto);
    }

}
