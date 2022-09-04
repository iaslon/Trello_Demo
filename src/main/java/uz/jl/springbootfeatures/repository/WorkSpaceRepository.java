package uz.jl.springbootfeatures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.springbootfeatures.domains.WorkSpace;

import java.util.List;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {

    @Query(nativeQuery = true, value = "select * from public.work_space where is_deleted = false " +
            "and id in (select workspace_id from public.workspace_member where member_id = :id)")
    List<WorkSpace> findAll(@Param(value = "id") Long id);

}
