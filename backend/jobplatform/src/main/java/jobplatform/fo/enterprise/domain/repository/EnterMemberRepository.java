package jobplatform.fo.enterprise.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jobplatform.fo.enterprise.domain.entity.EnterMemberEntity;

@Repository
public interface EnterMemberRepository extends JpaRepository<EnterMemberEntity,Long> {
	
	
	
	@Query(value ="SELECT m.entrprsPswrd FROM EnterMemberEntity m WHERE m.entrprsId = :entrprsId")
	String findByEntrprsId(String entrprsId);



	boolean existsByEntrprsPicEml(String entrprsPicEml);

	boolean existsByEntrprsId(String entrprsId);

//	@Query(value ="SELECT m.entrprsSq FROM EnterMemberEntity m WHERE m.entrprsId = :entrprsId")
//	String findByEntrprsSameID(String entrprsId);

	@Query(value ="SELECT m.entrprsSq FROM EnterMemberEntity m WHERE m.entrprsId = :entrprsId")
	Long findByEntrprsIdSq(String entrprsId);
	
	 // 기업이름 가져오기
    EnterMemberEntity findByEntrprsSq(long entrprsSq);


}
