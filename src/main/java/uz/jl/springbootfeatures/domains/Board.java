package uz.jl.springbootfeatures.domains;

import lombok.*;
import uz.jl.springbootfeatures.domains.auth.AuthUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.WORKSPACE;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private transient WorkSpace workspace;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "board_member",
            joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id")
    )
    private Collection<AuthUser> authUsers;

    @Builder(builderMethodName = "childBuilder")
    public Board(Long createdBy, LocalDateTime createdAt, boolean deleted, Long id, String title, Visibility visibility, WorkSpace workspace, Collection<AuthUser> authUsers) {
        super(createdBy, createdAt, deleted);
        this.id = id;
        this.title = title;
        this.visibility = visibility;
        this.workspace = workspace;
        this.authUsers = authUsers;
    }

    public enum Visibility {
        PRIVATE,
        PUBLIC,
        WORKSPACE
    }
}
