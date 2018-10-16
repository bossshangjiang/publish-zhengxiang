package com.example.dbservice;


        import com.example.dbservice.dao.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DbServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbServiceApplication.class, args);
    }

    @Autowired
    private UserRepository user;

    @GetMapping("/find")
    public List<User> find() {
        List<User> userList = user.findAll();
        return userList;
    }

    @GetMapping("/findbyname")
    public User findbyname(@RequestParam String name) {
        User s = user.findByName(name);
        return s;
    }

    //用户登录
    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    public String userLogin(@RequestParam String email, @RequestParam String pwd){
        System.out.println("DB"+email+" "+pwd);
        User dbuser=user.findByEmail(email);
        if (dbuser == null) {
            return "notexist";
        }
        else if (!dbuser.getPassword().equals(pwd)){
            return "fail";
        }
        else
            return "success";

    }

    //用户注册
    public @RequestMapping(value="/usersignup",method = RequestMethod.POST)
    String usersignup(@RequestParam("username") String username,
                      @RequestParam("userpwd") String userpwd,@RequestParam("useremail") String useremail){
        User dbuser=user.findByName(username);
        if(dbuser == null){
            User newuser= new User();
            newuser.setName(username);
            newuser.setPassword(userpwd);
            newuser.setEmail(useremail);
            user.save(newuser);
            System.out.println("DBsignup"+newuser.getName());

            System.out.println("DBsignup"+newuser.getEmail());
            return "success";
        }
        else
            return "exist";

    }
}