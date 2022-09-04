package uz.jl.springbootfeatures.dtos.board;

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
public class BoardCreateDTO {
//    @NotNull(message = "Workspace Id is required")
    private Long workspaceId;
//    @NotBlank(message = "Title cannot be blankd")
    private String title;
}
