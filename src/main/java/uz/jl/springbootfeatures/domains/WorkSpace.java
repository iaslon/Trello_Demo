package uz.jl.springbootfeatures.domains;

import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpace extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type; // education,human resources,IT-management....

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status workspaceStatus = Status.FREE_TRIAL;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "workspace_member",
            joinColumns = @JoinColumn(name = "workspace_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id")
    )
    private Collection<AuthUser> authUsers;

    public enum Status{
        FREE_TRIAL,
        PREMIUM
    }

    public enum Type{
        EDUCATION,
        HUMAN,
        RESOURCES,
        IT_MANAGEMENT,
        BUSINESS
    }

    @Builder(builderMethodName = "childBuilder")
    public WorkSpace(Long createdBy, LocalDateTime createdAt, boolean isDeleted, Long id, String name, Type type, String description, Status workspaceStatus) {
        super(createdBy, createdAt, isDeleted);
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.workspaceStatus = workspaceStatus;
        this.authUsers = new ArrayList<>();
    }
}
