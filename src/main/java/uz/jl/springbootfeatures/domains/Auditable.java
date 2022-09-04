package uz.jl.springbootfeatures.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Auditable {
    protected Long createdBy;
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected boolean isDeleted = false;
}
