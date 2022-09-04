package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import uz.jl.springbootfeatures.domains.BoardColumn;
import uz.jl.springbootfeatures.dtos.column.ColumnCreateDTO;
import uz.jl.springbootfeatures.dtos.column.ColumnGetDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColumnMapper {


    BoardColumn fromDTO(ColumnCreateDTO dto);

    ColumnGetDTO toDTO(BoardColumn column);

    List<ColumnGetDTO> toDTO(List<BoardColumn> workSpaces);

}
