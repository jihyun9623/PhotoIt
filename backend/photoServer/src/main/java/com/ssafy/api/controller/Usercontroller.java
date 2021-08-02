package com.ssafy.api.controller;

        import io.swagger.annotations.Api;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api("User Controller API V1")
@CrossOrigin("*")
public class Usercontroller {
    private static final Logger logger= LoggerFactory.getLogger(Usercontroller.class);

//    @Autowired
//    private UserService userService;

        /*@GetMapping("/profile")
        public void getMyInfo(String userId, String passwd){
                UserDto userDto=new UserDto();
                userDto.getUserIdx();
        }*/

}
