package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import uz.jl.springbootfeatures.domains.Board;
import uz.jl.springbootfeatures.dtos.board.BoardCreateDTO;
import uz.jl.springbootfeatures.dtos.board.BoardGetDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardGetDTO toDTO(Board workSpace);

    List<BoardGetDTO> toDTO(List<Board> workSpaces);

}
