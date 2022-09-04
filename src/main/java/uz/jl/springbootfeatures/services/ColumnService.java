package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDTO;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDTO;
import uz.jl.springbootfeatures.dtos.column.ColumnUpdateDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.ColumnMapper;
import uz.jl.springbootfeatures.repository.BoardRepository;
import uz.jl.springbootfeatures.repository.ColumnRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final BoardRepository boardRepository;
    private final ColumnRepository repository;
    private final ColumnMapper mapper;

    public List<ColumnGetDTO> getAll(Long id, Long userId) {
        return mapper.toDTO(repository.findAllByFalse(id, userId));
    }

    public ColumnGetDTO get(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("BoardColumn not Found!", 404)));
    }

    public ColumnGetDTO update(ColumnUpdateDTO dto) {
        BoardColumn boardColumn = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Column not found", 404);
        });

        boardColumn.setTitle(dto.getTitle());
        boardColumn.setOrderColumn(dto.getOrderColumn());

        return mapper.toDTO(repository.save(boardColumn));
    }

    public void create(ColumnCreateDTO dto, Long id) {
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found", 404);
        });

        BoardColumn column = mapper.fromDTO(dto);
        column.setBoard(board);
        column.setCreatedBy(id);
        column.setCreatedAt(LocalDateTime.now());
        column.setDeleted(false);

        repository.save(column);
    }

    public void delete(Long id) {
        BoardColumn column = repository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("Column not found!", 404));
        column.setDeleted(true);
        repository.save(column);
    }

}
