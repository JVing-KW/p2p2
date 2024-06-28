package jobplatform.fo.user.domain.repository;


import java.util.Optional;

import jobplatform.fo.user.domain.entity.MemberEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    MemberEntity findByMbrIdAndMbrPswrd(String mbrId, String mbrPswrd);

    @Query("SELECT m FROM MemberEntity m WHERE m.id = :userId")
    MemberEntity findByUserId(@Param("userId") String userId);

    MemberEntity findByMbrEmlAdrs(String mbrEmlAdrs);

    Optional<MemberEntity> findByMbrId(String mbrId);
}