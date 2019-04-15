package com.river.websocketchat.Controller;

import com.river.websocketchat.Handler.MyWebSocketHandler;
import com.river.websocketchat.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chat")
@AllArgsConstructor
public class ChatController {
    private static final String PREFIX= "/";
    private final MyWebSocketHandler socketHandler;

    Map<Long, User> users = new HashMap<>();

    @ModelAttribute
    public void initUserMap(){
        User u1 = new User();
        u1.setId(1L);
        u1.setName("张三");
        u1.setPassword("123456");
        users.put(u1.getId(), u1);

        User u2 = new User();
        u2.setId(2L);
        u2.setName("李四");
        u2.setPassword("123456");
        users.put(u2.getId(), u2);
    }

    @GetMapping("/login")
    public String Login(Model model){ return PREFIX + "login.html"; }

    //用户登入
    @PostMapping("/login")
    public String Login(User user, HttpServletRequest request){
        if(users.containsKey(user.getId())){
            User userVerify = users.get(user.getId());
            if(!user.getPassword().equals(userVerify.getPassword())){
                return "user or password is incorrect";
            }
        }
        else{
            return "user or password is incorrect";
        }
        request.getSession().setAttribute("uid", user.getId());
        request.getSession().setAttribute("name", users.get(user.getId()).getName());
        return "redirect:talk";
    }

    @GetMapping("/talk")
    public String chat(Model model, HttpServletRequest request){
        Object uid = request.getSession().getAttribute("uid");
        Object name = request.getSession().getAttribute("name");
        model.addAttribute("uid", uid);
        model.addAttribute("name", name);
        return PREFIX + "talk.html";
    }
}
