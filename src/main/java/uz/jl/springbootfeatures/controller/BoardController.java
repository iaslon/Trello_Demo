package uz.jl.springbootfeatures.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.springbootfeatures.config.security.UserDetails;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.dtos.board.BoardCreateDTO;
import uz.jl.springbootfeatures.dtos.board.BoardGetDTO;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDTO;
import uz.jl.springbootfeatures.response.ApiResponse;
import uz.jl.springbootfeatures.services.BoardService;

import javax.validation.Valid;
import java.util.List;

import static uz.jl.springbootfeatures.controller.ApiController.PATH;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping(PATH + "/board/all/{workspaceId}")
    public List<BoardGetDTO> getAll(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable("workspaceId") Long workspaceId) {
        return service.getAll(userDetails.authUser().getId(),workspaceId);
    }

    @PostMapping(PATH + "/board")
    public ApiResponse<Void> create(@Valid @RequestBody BoardCreateDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        service.create(dto, userDetails.authUser());
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/board/{id}")
    public ApiResponse<BoardGetDTO> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @DeleteMapping(PATH + "/board/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ApiResponse<>(200);
    }

    @PutMapping(PATH + "/board/update")
    public ApiResponse<Board> update(@Valid @RequestBody BoardUpdateDTO dto) {
        return service.update(dto);
    }

}
