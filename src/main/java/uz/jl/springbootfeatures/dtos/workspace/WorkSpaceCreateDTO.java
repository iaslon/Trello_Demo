package uz.jl.springbootfeatures.dtos.workspace;

import lombok.*;
import uz.jl.springbootfeatures.domains.WorkSpace;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceCreateDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotBlank(message = "Type is required")
    private String type;
}
