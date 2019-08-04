package pl.kowalskiadam.designrun.app.method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MethodRepository extends JpaRepository<Method, Long> {

    List<Method> findByOwnerId(Long id);

    @Query("select m from Method m where m.owner.id = :id")
    List<Method> getByOwnerId(@Param("id") Long id);

    @Query("select m from Method m where m.owner.id = :id and m.hide =true")
    List<Method> getByOwnerIdHide(@Param("id") Long id);

    @Query("select m from Method m where m.owner.id = :id and m.hide =false")
    List<Method> getByOwnerIdAvailable(@Param("id") Long id);

}
