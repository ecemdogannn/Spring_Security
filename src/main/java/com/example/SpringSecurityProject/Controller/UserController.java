package com.example.SpringSecurityProject.Controller;

import com.example.SpringSecurityProject.Entity.UserCourse;
import com.example.SpringSecurityProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    //Kullanıcı kaydı
    @PostMapping("/register")
    public UserCourse register (@RequestBody UserCourse user){ //Json verilerini UserCourse nesnesine dönüştürür.
    return service.register(user); //kullanıcıyı kaydetmek için servis sınıfından register metodunu çağırır.
    }

    //kullanıcıları listleme
    @GetMapping("/register")
    public List<UserCourse> users(){
        return service.getAllUsers(); // service üzerinden getAllUsers metodu çağrılır
    }

    //belirli kullanıcıyı getirme
    @GetMapping("/register/{username}")
    public UserCourse getUsers(@PathVariable String username) {  //URL'deki {username} değişkenini username parametresine aktarır.
        UserCourse theUser = service.findById(username);
        if (theUser == null) {
            throw new RuntimeException("no user - " + username);
        }
        return theUser;
    }

    //kullanıcı silme
   @DeleteMapping("/register/{username}")
    public String deleteUsers(@PathVariable String username){
       UserCourse theUser = service.findById(username);
    if (theUser == null){
        throw new RuntimeException("no user - " + username);
    }
    service.deletebyId(username);
    return "delete user - " + username;
   }

   //kullanıcı güncelleme
   @PutMapping("/register")
    public UserCourse UpdateTheUser(@RequestBody UserCourse user){
        UserCourse dbUsers = service.UpdateUser(user);
        return dbUsers;
   }

}
