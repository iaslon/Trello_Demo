package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.springbootfeatures.domains.BoardColumn;

import java.util.List;

public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {

    //    @Query(nativeQuery = true, value = "select * from public.board where is_deleted = false " +
//            "and id in (select board_id from public.board_member where member_id = :id)")
//    @Query(value = "select b from Board b where b.id in (select t.id from Board t join t.authUsers u where u.id = :id)")

//    @Query(value = "select c from BoardColumn c where c.deleted=false and c.board.id = :id " +
//            "and :userId in (select b from Board b join b.authUsers u where b.id = :id and u.id = :userId)")

//    @Query(nativeQuery = true, value = "select c.*n" +
//            "from board_column c\n" +
//            "where c.is_deleted = false\n" +
//            "  and c.board_id = :boardId\n" +
//            "  and exists(select t from board_member t where t.board_id = :boardId and t.member_id = :userId)")

    @Query(nativeQuery = true, value = "select c.* from board_column c where c.is_deleted = false and c.board_id = :boardId and exists(select t from board_member t where t.board_id = :boardId and t.member_id = :userId)")
    List<BoardColumn> findAllByFalse(@Param(value = "boardId") Long boardId, @Param(value = "userId") Long userId);
}
