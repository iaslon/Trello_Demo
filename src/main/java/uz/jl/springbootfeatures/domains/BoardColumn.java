package uz.jl.springbootfeatures.domains;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardColumn extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int orderColumn;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Board board;

    @Builder(builderMethodName = "childBuilder")
    public BoardColumn(Long createdBy, LocalDateTime createdAt, boolean isDeleted, Long id, String title, int orderColumn) {
        super(createdBy, createdAt, isDeleted);
        this.id = id;
        this.title = title;
        this.orderColumn = orderColumn;
    }
}
