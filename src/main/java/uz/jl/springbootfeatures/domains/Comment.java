package uz.jl.springbootfeatures.domains;

import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

    @Builder.Default
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private AuthUser authUser;

}
