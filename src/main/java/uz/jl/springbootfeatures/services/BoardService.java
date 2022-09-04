package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.domains.WorkSpace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.board.BoardCreateDTO;
import uz.jl.springbootfeatures.dtos.board.BoardGetDTO;
import uz.jl.springbootfeatures.dtos.board.BoardUpdateDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.BoardMapper;
import uz.jl.springbootfeatures.repository.BoardRepository;
import uz.jl.springbootfeatures.repository.WorkSpaceRepository;
import uz.jl.springbootfeatures.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;
    private final WorkSpaceRepository workSpaceRepository;
    private final BoardMapper boardMapper;

    public List<BoardGetDTO> getAll(Long userId, Long workspaceId) {
        return boardMapper.toDTO(repository.findAllByFalse(userId,workspaceId));
    }

    public BoardGetDTO get(Long id) {
        return boardMapper.toDTO(repository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("Board not Found!", 404)));
    }

    public ApiResponse<Board> update(BoardUpdateDTO dto) {
        return null;
    }

    public void create(BoardCreateDTO dto, AuthUser authUser) {
        WorkSpace workSpace = workSpaceRepository.findById(dto.getWorkspaceId()).orElseThrow(
                () -> new GenericNotFoundException("WorkSpace not found", 404));

        Board board = Board.childBuilder()
                .title(dto.getTitle())
                .visibility(Board.Visibility.WORKSPACE)
                .createdAt(LocalDateTime.now())
                .createdBy(authUser.getId())
                .deleted(false)
                .workspace(workSpace)
                .authUsers(List.of(authUser))
                .build();

        repository.save(board);
    }

    public void delete(Long id) {
        Board board = repository.findById(id).orElseThrow(() -> new GenericNotFoundException("Board not found!", 404));
        board.setDeleted(true);
        repository.save(board);
    }
}
