package uz.jl.springbootfeatures.domains;


import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private BoardColumn column;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_member",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<AuthUser> users;

    @Builder(builderMethodName = "childBuilder")
    public Task(Long createdBy, LocalDateTime createdAt, boolean isDeleted, Long id, String title, String description) {
        super(createdBy, createdAt, isDeleted);
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
