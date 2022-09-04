package uz.jl.springbootfeatures.dtos.workspace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceGetDTO {
    private Long id;
    private String name;
    private String description;
    private String type;
}
