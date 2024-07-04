package jobplatform.fo.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jobplatform.fo.user.domain.vo.ApplyInfoDTO;
import jobplatform.fo.user.service.MypageService;

@RestController
@RequestMapping("/mypage")
public class S_MypageController {

   @Autowired
   MypageService mypageService;

   @SuppressWarnings("null")
   @PostMapping("/test")
   public ResponseEntity<?> test(@RequestBody Map<String, String> param)throws Exception {

      System.out.println(param.get("id"));

      List<ApplyInfoDTO> applyInfo = null;
      Map<String, Object> applyInfoMap = new HashMap<>();

      Integer mbrSq = mypageService.getId(param.get("id"));
      System.out.println(mbrSq);

      if( mbrSq == null) {
         return ResponseEntity.ok(List.of());
      }else {
         
         applyInfo = mypageService.getApplyInfo(mbrSq);
         Integer totalApplyCount = mypageService.getTotalApplyCount(mbrSq);
         applyInfoMap.put("applyInfo", applyInfo);
         applyInfoMap.put("totalApplyCount", totalApplyCount);

      }

      return ResponseEntity.ok(applyInfoMap);
   }
   
   
   @PostMapping("/delApply") // 지원취소
   public void delApply(@RequestBody Map<String, String> param) throws Exception{
      System.out.println(param.get("apySq"));
      Integer apySq = Integer.parseInt(param.get("apySq"));
      mypageService.delAppy(apySq);

      
   }

}
