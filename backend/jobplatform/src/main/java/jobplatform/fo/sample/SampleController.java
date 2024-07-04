package jobplatform.fo.sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/sample")
public class SampleController {


    @Autowired
    SampleService sampleService;

    @PostMapping("/sample")// @RequestBody
    public Map join(@RequestBody SampleEntity sampleEntity){
        //json형식으로 데이터를 보내주기 위해서 HashMap 생성
        Map<String, Long> map = new HashMap<>();
    log.info("sampleEntity : " + sampleEntity );
        Long id =  sampleService.insert(sampleEntity);

        map.put("id",id);
        log.info("id : "+ id);

   return map;
    }

    @GetMapping("/sample")
    public Map login(@RequestParam HashMap<String,String> params){
        log.info("params : " + params);
        String id=  params.get("id");

            Long num = (long)  Integer.parseInt(id);
//        Long id = map.get("id");


        log.info("id" + num);

       SampleEntity sampleEntity= sampleService.gett(num);

        Map<String, String> map = new HashMap<>();
        map.put("name",sampleEntity.getName());
        map.put("password",sampleEntity.getPassword());


        log.info("name :" +sampleEntity.getName());
        log.info("password :" +sampleEntity.getPassword());

        return map;
    }

    @DeleteMapping("/sample")
    public Map delete(@RequestParam HashMap<String,String> params){
        String id=  params.get("id");

        Long num = (long)  Integer.parseInt(id);

        SampleEntity sampleEntity= sampleService.gett(num);


        Map<String, String> map = new HashMap<>();
        map.put("name",sampleEntity.getName());
        map.put("password",sampleEntity.getPassword());
        sampleService.delete(num);
        return map;
    }
    @PutMapping("/sample")
    public void update(){

    }
}
