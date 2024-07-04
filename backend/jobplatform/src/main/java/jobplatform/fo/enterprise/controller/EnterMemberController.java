package jobplatform.fo.enterprise.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jobplatform.fo.enterprise.domain.dto.EnterLoginDTO;
import jobplatform.fo.enterprise.domain.dto.EnterRegisterDTO;
import jobplatform.fo.enterprise.service.EnterMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/enter")
public class EnterMemberController {

    @Autowired
    EnterMemberService enterMemberService;
    
    
    // ResponseEntity로 상태 코드만 전달,

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody EnterRegisterDTO enterMemberDTO){
        log.info("register 들어옴");
        if(enterMemberService.selectId(enterMemberDTO.getEntrprsId())){

            log.info(  "중복 id 있음");

            return ResponseEntity.badRequest().build();
        }

        try{
            enterMemberService.insert(enterMemberDTO);

        }catch (Exception e){
        log.info("register sql 오류 "+e);
        }

        return ResponseEntity.ok().build();
    };

    @GetMapping("/check")
    public ResponseEntity checkId(@RequestParam String entrprsId){
        log.info("check요청");
      if(enterMemberService.selectId(entrprsId)){
          log.info(entrprsId + " id 있음");
          return    ResponseEntity.badRequest().build();
      }
   return ResponseEntity.ok().build(); }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody EnterLoginDTO enterLoginDTO
    ) {
        log.info("login요청 들어옴");
        log.info(enterLoginDTO);
        Long pk = null;

        ResponseCookie cookie = ResponseCookie
                .from("entrprsId", enterLoginDTO.getEntrprsId())
                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();



        if(enterLoginDTO.getEntrprsId() != null && enterLoginDTO.getEntrprsPswrd() != null){

            try {
                pk =	enterMemberService.login(enterLoginDTO);

            }catch(Exception e) {

                return ResponseEntity.badRequest().body("jpa login 오류" + e);
            }

            if(pk == null ){
                return ResponseEntity.badRequest().body("아이디 정보가 없습니다" );
            }

        }
        Map map = new HashMap();

        map.put("pk",pk);
        map.put("entrprsId",enterLoginDTO.getEntrprsId());

        log.info("cookie"+ cookie);

        //body를 쓰면 build()를 안써도됨.
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(map);
    }
   
}
