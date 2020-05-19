package com.yeseong.admin.web;

import com.yeseong.admin.config.auth.LoginUser;
import com.yeseong.admin.config.auth.dto.SessionUser;
import com.yeseong.admin.service.posts.PostsService;
import com.yeseong.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model,
                        @LoginUser // 어노테이션 기반으로 Session값을 바로 받을 수 있게함.
                        SessionUser user) {
        //Model : 서버템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        // CustimOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했음.
        // 로그인 성공 시 httpSession.getAttribute에서 값을 가져올 수 있다.
        // @LoginUser때문에 없어도 됨.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
