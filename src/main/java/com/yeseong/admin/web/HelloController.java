package com.yeseong.admin.web;

import com.yeseong.admin.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 해당 Contorller를 Json을 반환하는 Controller로 만들어줌.
@RestController
public class HelloController {

    // /hello로 들어오는 GET요청에 대한 응답
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션, name이라는 이름으로 넘긴 파라미터를 Sring name에 저장
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
