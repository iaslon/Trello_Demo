package uz.jl.springbootfeatures.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.springbootfeatures.domains.WorkSpace;
import uz.jl.springbootfeatures.domains.auth.AuthUser;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceCreateDTO;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceGetDTO;
import uz.jl.springbootfeatures.dtos.workspace.WorkSpaceUpdateDTO;
import uz.jl.springbootfeatures.exceptions.GenericNotFoundException;
import uz.jl.springbootfeatures.mappers.WorkSpaceMapper;
import uz.jl.springbootfeatures.repository.WorkSpaceRepository;
import uz.jl.springbootfeatures.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkSpaceService {

    private final WorkSpaceRepository repository;
    private final WorkSpaceMapper mapper;

    public List<WorkSpaceGetDTO> getAll(Long id) {
        return mapper.toDTO(repository.findAll(id));
    }

    public void create(WorkSpaceCreateDTO dto, AuthUser user) {
        WorkSpace workSpace = mapper.fromCreateDto(dto);
        workSpace.setCreatedBy(user.getId());
        workSpace.setCreatedAt(LocalDateTime.now());
        workSpace.setDeleted(false);
        workSpace.setAuthUsers(List.of(user));
        workSpace.setWorkspaceStatus(WorkSpace.Status.FREE_TRIAL);

        repository.save(workSpace);
    }

    public WorkSpaceGetDTO get(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() ->
                new GenericNotFoundException("WorkSpace not Found!", 404)));
    }

    public void delete(Long id) {
        WorkSpace workSpace = repository.findById(id).orElseThrow(() -> new GenericNotFoundException("WorkSpace not found!", 404));
        workSpace.setDeleted(true);
        repository.save(workSpace);
    }

    public ApiResponse<WorkSpace> update(WorkSpaceUpdateDTO dto) {
        WorkSpace workSpace = repository.findById(dto.getId()).orElseThrow(() -> new GenericNotFoundException("WorkSpace not Found!", 404));
        workSpace.setName(dto.getName());
        workSpace.setDescription(dto.getDescription());
        workSpace.setType(WorkSpace.Type.valueOf(dto.getType()));
        repository.save(workSpace);
        return new ApiResponse<>(workSpace);
    }
}
