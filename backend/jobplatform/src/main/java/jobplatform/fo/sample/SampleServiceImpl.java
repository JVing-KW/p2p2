package jobplatform.fo.sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class SampleServiceImpl implements SampleService {


    @Autowired
    SampleRepository sampleRepository;

    @Override
    public Long insert(SampleEntity sampleEntity) {


    Long id =   sampleRepository.save(sampleEntity).getId();

        return id;
    }

    @Override
    public void delete(Long id) {
//값이 있으면 SampleEntity 타입에 객체 반환 없으면 false로 반환
     Optional<SampleEntity> sampleEntity=  sampleRepository.findById(id);
        if(sampleEntity.isPresent()){
            sampleRepository.deleteById(id);
        }
    }

    @Override
    public void update(SampleEntity sampleEntity) {
        Long id =   sampleEntity.getId();

        Optional<SampleEntity> sample=  sampleRepository.findById(id);

        sampleRepository.save(sample.get());
    }


    @Override
    public  SampleEntity  gett(Long id) {

        Optional<SampleEntity> result=  sampleRepository.findById(id);
        log.info("result:"+result);
        SampleEntity sampleEntity = result.orElseThrow();

   return sampleEntity;
    }
}
