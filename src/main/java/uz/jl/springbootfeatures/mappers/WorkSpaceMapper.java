package uz.jl.springbootfeatures.mappers;

import org.mapstruct.Mapper;
import uz.jl.springbootfeatures.domains.WorkSpace;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceCreateDTO;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceGetDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkSpaceMapper {

    WorkSpace fromCreateDto(WorkSpaceCreateDTO dto);

    WorkSpaceGetDTO toDTO(WorkSpace workSpace);

    List<WorkSpaceGetDTO> toDTO(List<WorkSpace> workSpaces);

}
