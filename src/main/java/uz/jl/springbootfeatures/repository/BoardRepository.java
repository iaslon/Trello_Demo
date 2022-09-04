package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.springbootfeatures.domains.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //    @Query(nativeQuery = true, value = "select * from public.board where is_deleted = false " +
//            "and id in (select board_id from public.board_member where member_id = :id)")
//    @Query(value = "select b from Board b where b.workSpace.id=:workspaceId and exists(select t from WorkSpace t join t.authUsers u where u.id = :userId and t.id =:workspaceId)")
//    @Query(value = "select b from Board b where b.deleted=false and b.workspace.id=:workspaceId and b.id in (select t from Board t join t.authUsers u where u.id = :userId)")

    @Query(nativeQuery = true, value = "select b.*\n" +
            "from board b\n" +
            "where b.work_space_id = :workspaceId and b.is_deleted=false\n" +
            "  and exists(select t from workspace_member t where t.workspace_id = :workspaceId and t.member_id = :userId);")
    List<Board> findAllByFalse(@Param(value = "userId") Long userId, @Param(value = "workspaceId") Long workspaceId);
}
