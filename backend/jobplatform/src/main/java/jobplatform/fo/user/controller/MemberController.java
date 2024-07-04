package jobplatform.fo.user.controller;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import jobplatform.fo.common.config.JwtTokenProvider;
import jobplatform.fo.user.domain.entity.MemberEntity;
import jobplatform.fo.user.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public MemberEntity login(@RequestBody Map<String, String> params, HttpSession session) {
        MemberEntity member = memberRepository.findByMbrIdAndMbrPswrd(params.get("mbrId"), params.get("mbrPswrd"));

        if (member != null) {
            System.out.println("로그인 시도:" + params.get("mbrId") + "," + params.get("mbrPswrd"));

            if (member.getDltChck()) {
                System.out.println("로그인 실패: 탈퇴한 회원");
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "탈퇴한 회원입니다.");
            }

            if (!member.getUseChck()) {
                System.out.println("로그인 실패: 사용 중지된 회원");
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "사용 중지된 회원입니다.");
            }

            // 세션에 사용자 정보 저장
            session.setAttribute("member", member);
            System.out.println("로그인 성공" + member);
            return member;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 시도입니다.");
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        System.out.println("로그아웃 완료");
        session.invalidate(); // 세션 무효화 (로그아웃)
        return ResponseEntity.ok("로그아웃되었습니다.");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMember(@RequestBody MemberEntity updatedMember) {
        try {
            MemberEntity existingMember = memberRepository.findById(updatedMember.getMbrSq())
                    .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. 회원 순번: " + updatedMember.getMbrSq()));

            // 업데이트할 필드 설정
            existingMember.setMbrEmlAdrs(updatedMember.getMbrEmlAdrs());
            existingMember.setMbrPswrd(updatedMember.getMbrPswrd());
            existingMember.setMbrMp(updatedMember.getMbrMp());
            existingMember.setUpdtMbrSq(updatedMember.getMbrSq());
            existingMember.setUpdtDtm(LocalDateTime.now()); // 수정 일시 설정

            MemberEntity updatedEntity = memberRepository.save(existingMember);
            return ResponseEntity.ok(updatedEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정보 업데이트 중 오류가 발생했습니다.");
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestBody MemberEntity deletedMember){
        try {
            // 회원조회
            MemberEntity existingMember = memberRepository.findById(deletedMember.getMbrSq())
                    .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. 회원순번:" + deletedMember.getMbrSq()));

            // 삭제,사용 여부 업데이트
            existingMember.setDltChck(true); // 삭제 여부 true
            existingMember.setUseChck(false); // 사용 여부 false
            existingMember.setDltDtm(LocalDateTime.now()); // 삭제 일시 설정

            // 회원 정보 업데이트
            memberRepository.save(existingMember);

            return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            // 회원을 찾지 못했을때
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원을 찾을 수 없습니다. 회원 순번:" + deletedMember.getMbrSq());
        }
        catch(Exception e) {
            // 기타 오류
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴 중 오류가 발생했습니다.");

        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody MemberEntity userData) {
        if (userData.getMbrEmlRcvChck() == null || userData.getMbrEmlRcvChck().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 수신 여부가 누락되었습니다.");
        }
        if (userData.getMbrPrvcyTrmsChck() == null || userData.getMbrPrvcyTrmsChck().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("개인정보 약관 체크가 누락되었습니다.");
        }
        if (userData.getGndrCtgryCd() == null || userData.getGndrCtgryCd().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("성별 유형 코드가 누락되었습니다.");
        }
        // 아이디 중복 체크
        Optional<MemberEntity> existingUser = memberRepository.findByMbrId(userData.getMbrId());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용 중인 아이디입니다.");
        }

        try {
            // admin일 경우 로직 추가해야함
            userData.setInsrtMbrSq(99); // 임시 값 설정
            userData.setDltChck(false);
            userData.setUseChck(true);

            MemberEntity savedUser = memberRepository.save(userData); // 처음 저장
            savedUser.setInsrtMbrSq(savedUser.getMbrSq());
            MemberEntity finalUser = memberRepository.save(savedUser); // 최종 저장

            return ResponseEntity.ok("환영합니다. " + finalUser.getMbrId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("등록에 실패했습니다: " + e.getMessage());
        }
    }

    // https://henniee.tistory.com/217 이메일 관련 참고 링크
    @ResponseBody
    @PostMapping("/emlRegister")
    public Map<String, Object> emlRegister(@RequestBody Map<String, String> request) {
        String mbrEmlAdrs = request.get("mbrEmlAdrs");

        Map<String, Object> map = new HashMap<>();

        // 이메일 주소가 null인지 확인
        if (mbrEmlAdrs == null || mbrEmlAdrs.isEmpty()) {
            map.put("error", "이메일 주소가 입력되지 않았습니다.");
            return map;
        }

        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        Properties prop = new Properties();

        mailSenderImpl.setHost("smtp.gmail.com");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername("walkingongreenball@gmail.com");		// 본인 또는 회사 아이디로 교체
        mailSenderImpl.setPassword("lopq jelw gspn fqux");				// 참고 링크에 따라 제공받은 비밀번호 사용
        prop.put("mail.smtp.auth", true);								// 이메일 서버에 인증 요구
        prop.put("mail.smtp.starttls.enable", true);  					// 암호화된 연결을 활성화. starttls는 이메일 전송중에 보안 계층을 추가하여 데이터의 기밀성을 보호

        mailSenderImpl.setJavaMailProperties(prop);


        MemberEntity member = memberRepository.findByMbrEmlAdrs(mbrEmlAdrs);
        if (member != null) {
            map.put("exist", "이미 존재하는 이메일입니다.");
        } else {
            Random random = new Random();
            String key = "";

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mbrEmlAdrs);

            // 입력 키를 위한 난수 생성 코드
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(26) + 65;
                key += (char) index;
            }
            for (int i = 0; i < 6; i++) {
                int numIndex = random.nextInt(10);
                key += numIndex;
            }

            String mail = "\n 회원가입 인증코드";
            message.setSubject("회원가입 인증코드 메일입니다.");	// 이메일 제목
            message.setText("인증번호는 " + key +" 입니다." + mail);	// 이메일 내용

            try {
                mailSenderImpl.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("key", key);
        }
        return map;
    }

    // https://henniee.tistory.com/217 이메일 관련 참고 링크
    @PostMapping("/findId")
    public ResponseEntity<String> findMbrId(@RequestBody MemberEntity userData) {
        try {
            if (userData.getMbrName() == null || userData.getMbrName().isEmpty()) {
                return ResponseEntity.badRequest().body("이름이 누락되었습니다.");
            }
            if (userData.getMbrEmlAdrs() == null || userData.getMbrEmlAdrs().isEmpty()) {
                return ResponseEntity.badRequest().body("이메일 주소가 누락되었습니다.");
            }

            // 이메일 주소로 회원 찾기
            MemberEntity mbr = memberRepository.findByMbrEmlAdrs(userData.getMbrEmlAdrs());

            // 이메일 주소와 이름 유효성 확인
            if (mbr == null || !mbr.getMbrName().equals(userData.getMbrName())) {
                return ResponseEntity.badRequest().body("일치하는 회원정보가 조회되지 않습니다.");
            }

            // 정상적인 경우에 회원 아이디 반환
            return ResponseEntity.ok("회원 아이디: " + mbr.getMbrId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회에 실패했습니다: " + e.getMessage());
        }
    }

    // https://henniee.tistory.com/217 이메일 관련 참고 링크
    @PostMapping("/findPswrd")
    public ResponseEntity<String> findMbrPswrd(@RequestBody MemberEntity userData) {
        try {
            if (userData.getMbrId() == null || userData.getMbrId().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디가 누락되었습니다.");
            }
            if (userData.getMbrName() == null || userData.getMbrName().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이름이 누락되었습니다.");
            }
            if (userData.getMbrEmlAdrs() == null || userData.getMbrEmlAdrs().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 주소가 누락되었습니다.");
            }

            // 이메일 주소로 회원 찾기
            MemberEntity mbr = memberRepository.findByMbrEmlAdrs(userData.getMbrEmlAdrs());

            // 이메일 주소와 이름, 아이디 유효성 확인
            if (mbr == null || !mbr.getMbrName().equals(userData.getMbrName()) || !mbr.getMbrId().equals(userData.getMbrId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일치하는 회원정보가 조회되지 않습니다.");
            }

            // 정상적인 경우에만 MemberEntity 반환
            return ResponseEntity.ok().body(mbr.getMbrPswrd());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("조회에 실패했습니다: " + e.getMessage());
        }
    }

    // https://henniee.tistory.com/217 이메일 관련 참고 링크
    @ResponseBody
    @PostMapping("/emlFind")
    public Map<String, Object> emlFind(@RequestBody Map<String, String> request) {
        String mbrEmlAdrs = request.get("mbrEmlAdrs");

        Map<String, Object> map = new HashMap<>();

        // 이메일 주소가 null인지 확인
        if (mbrEmlAdrs == null || mbrEmlAdrs.isEmpty()) {
            map.put("error", "이메일 주소가 입력되지 않았습니다.");
            return map;
        }

        // https://henniee.tistory.com/217 참고 링크
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        Properties prop = new Properties();

        mailSenderImpl.setHost("smtp.gmail.com");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername("walkingongreenball@gmail.com");
        mailSenderImpl.setPassword("lopq jelw gspn fqux");
        prop.put("mail.smtp.auth", true);	// 이메일 서버에 인증 요구
        prop.put("mail.smtp.starttls.enable", true);  // 암호화된 연결을 활성화. starttls는 이메일 전송중에 보안 계층을 추가하여 데이터의 기밀성을 보호

        mailSenderImpl.setJavaMailProperties(prop);


        MemberEntity member = memberRepository.findByMbrEmlAdrs(mbrEmlAdrs);
        if (member == null) {
            map.put("exist", "존재하지 않는 이메일 입니다.");
        } else {
            Random random = new Random();
            String key = "";

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mbrEmlAdrs);

            // 입력 키를 위한 난수 생성 코드
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(26) + 65;
                key += (char) index;
            }
            for (int i = 0; i < 6; i++) {
                int numIndex = random.nextInt(10);
                key += numIndex;
            }

            String mail = "\n 회원정보조회 인증코드";
            message.setSubject("회원정보조회 인증코드 메일입니다.");	// 이메일 제목
            message.setText("인증번호는 " + key +" 입니다." + mail);	// 이메일 내용

            try {
                mailSenderImpl.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("key", key);
        }
        return map;
    }

    // 네이버 로그인 콜백 처리
    // https://henniee.tistory.com/m/238 링크 참조
    @GetMapping("/loginNCallback")
    public ResponseEntity<Map<String, Object>> handleLoginNCallback(@RequestParam String code, @RequestParam String state) {
        try {
            // 네이버 API로 액세스 토큰 요청
            String clientId = "TIwA7WnbAvnjEwnbPGZm";	// 본인 또는 회사 아이디로 교체
            String clientSecret = "Qbh0YK_yrf"; 		// 참고 링크에 따라 제공받은 비밀번호 사용
            String tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
                    + clientId + "&client_secret=" + clientSecret + "&code=" + code + "&state=" + state;

            ResponseEntity<Map> tokenResponse = restTemplate.getForEntity(tokenUrl, Map.class);
            Map<String, Object> responseBody = tokenResponse.getBody();

            if (responseBody == null || responseBody.get("access_token") == null) {
                return new ResponseEntity<>(Map.of("message", "네이버 로그인 실패"), HttpStatus.UNAUTHORIZED);
            }

            String accessToken = (String) responseBody.get("access_token");
            String refreshToken = (String) responseBody.get("refresh_token");

            // 네이버 프로필 조회 메서드 호출
//            return getNaverUserProfile(accessToken, refreshToken);	// 회원가입, 로그인까지 진행하는 코드
            return ResponseEntity.ok().body(Map.of("message", "네이버 로그인 성공", "access_token", accessToken, "refresh_token", refreshToken));	// vue로 바로 반환하는 코드
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 네이버 사용자 프로필 가져오기 및 회원 처리
    private ResponseEntity<Map<String, Object>> getNaverUserProfile(String accessToken, String refreshToken) {
        try {
            String profileUrl = "https://openapi.naver.com/v1/nid/me";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> profileResponse = restTemplate.exchange(profileUrl, HttpMethod.GET, entity, String.class);

            if (profileResponse.getStatusCode().is2xxSuccessful()) {
                String responseBody = profileResponse.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                if (jsonNode.has("resultcode") && jsonNode.get("resultcode").asText().equals("00")) {
                    // 네이버 API로부터 정상적인 응답을 받았을 때
                    String email = jsonNode.path("response").path("email").asText();
                    String name = jsonNode.path("response").path("name").asText();
                    String mobile = jsonNode.path("response").path("mobile").asText();
                    String birthYear = jsonNode.path("response").path("birthyear").asText();
                    String birthday = jsonNode.path("response").path("birthday").asText();
                    String gender = jsonNode.path("response").path("gender").asText();

                    // 생년월일 처리
                    String birthDate = birthYear + birthday.replace("-", "");

                    MemberEntity member = new MemberEntity();
                    member.setMbrId(email); // 네이버에서 제공하는 이메일을 사용하여 아이디 설정
                    member.setMbrName(name);
                    member.setMbrEmlAdrs(email);
                    member.setMbrMp(mobile.replace("-", ""));
                    member.setMbrBd(birthDate);
                    member.setGndrCtgryCd(gender);

                    member.setSclCtgryCd("NAVER");
                    member.setDltChck(false);
                    member.setUseChck(true);
                    member.setMbrEmlRcvChck("N");    // 임시 값 설정
                    member.setMbrPrvcyTrmsChck("Y");// 임시 값 설정
                    member.setMbrPswrd(name);       // 임시 값 설정

                    // 회원 처리 메서드 호출
                    return processNaverMember(member, accessToken, refreshToken);
                } else {
                    return new ResponseEntity<>(Map.of("message", "네이버 프로필 정보 조회 실패"), HttpStatus.UNAUTHORIZED);
                }
            } else {
                return ResponseEntity.status(profileResponse.getStatusCode()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 네이버 회원 정보 처리
    private ResponseEntity<Map<String, Object>> processNaverMember(MemberEntity member, String accessToken, String refreshToken) {
        try {
            MemberEntity existingMember = memberRepository.findByMbrEmlAdrs(member.getMbrEmlAdrs());
            if (existingMember != null) {
                // 이미 존재하는 회원이면 로그인 성공으로 처리
                Map<String, Object> response = Map.of("message", "네이버 로그인 성공", "access_token", accessToken, "refresh_token", refreshToken);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // 새로운 회원이면 회원 가입 처리 후 로그인 성공으로 처리
                member.setInsrtMbrSq(99); // 임시 값 설정
                MemberEntity savedUser = memberRepository.save(member); // 처음 저장
                savedUser.setInsrtMbrSq(savedUser.getMbrSq());
                memberRepository.save(savedUser);

                Map<String, Object> response = Map.of("message", "회원 가입 성공", "access_token", accessToken, "refresh_token", refreshToken);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 네이버 회원 정보 체크
    @GetMapping("/idCheckNaver")
    public ResponseEntity<Map<String, Boolean>> idCheck(@RequestParam String mbrEmlAdrs) {
        Map<String, Boolean> result = new HashMap<>();
        // 이메일 중복 체크 로직
        MemberEntity existingMember = memberRepository.findByMbrEmlAdrs(mbrEmlAdrs);
        result.put("result", existingMember != null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 네이버 회원 가입 처리
    @PostMapping("/registerNaver")
    public ResponseEntity<Map<String, Object>> naverRegister(@RequestBody MemberEntity member) {
        Map<String, Object> result = new HashMap<>();
        MemberEntity existingMember = memberRepository.findByMbrEmlAdrs(member.getMbrEmlAdrs());
        if (existingMember != null) {
            result.put("status", 400); // 이메일 중복
            result.put("message", "이미 존재하는 이메일입니다.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            try {
                memberRepository.save(member);
                String token = jwtTokenProvider.createToken(member.getMbrId());

                result.put("status", 200); // 회원가입 성공
                result.put("token", token); // 생성된 토큰
                result.put("member", member);
            } catch (Exception e) {
                result.put("status", 500); // 회원가입 실패
                result.put("message", "회원가입 중 오류가 발생했습니다.");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // 네이버 로그인 처리
    @PostMapping("/loginNaver")
    public ResponseEntity<Map<String, Object>> naverLogin(@RequestBody MemberEntity member) {
        Map<String, Object> result = new HashMap<>();
        MemberEntity foundMember = memberRepository.findByMbrIdAndMbrPswrd(member.getMbrId(), member.getMbrPswrd());
        if (foundMember != null) {
            String token = jwtTokenProvider.createToken(foundMember.getMbrId());

            result.put("status", 200); // 로그인 성공
            result.put("token", token); // 생성된 토큰
            result.put("member", foundMember);
        } else {
            result.put("status", 401); // 로그인 실패
            result.put("message", "로그인 정보가 올바르지 않습니다.");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}