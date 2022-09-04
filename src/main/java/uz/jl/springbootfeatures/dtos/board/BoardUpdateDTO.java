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
public class BoardUpdateDTO {
//    @NotNull(message = "Board Id is required")
    private Long id;
//    @NotBlank(message = "Title cannot be null")
    private String title;
//    @NotBlank(message = "Visibility is required")
    private String visibility;
}
