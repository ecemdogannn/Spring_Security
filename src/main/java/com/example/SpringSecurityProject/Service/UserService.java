package com.example.SpringSecurityProject.Service;

import com.example.SpringSecurityProject.Dao.UserRepo;
import com.example.SpringSecurityProject.Entity.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder; //şifrelerin güvenli bir şekilde şifrelenmesini ve doğrulanmasını sağlar

    public void updatePasswordsToBCrypt() {
        List<UserCourse> users = repo.findAll(); //tüm kullanıcıları alır
        for (UserCourse user : users) { //her kullanıcıyı dolaşır
            user.setPassword(passwordEncoder.encode(user.getPassword())); //olan kullanıcıların  mevcut şifrelerini şifreler
            repo.save(user); //güncellenmiş şekilde kayıt eder.
        }
    }


    //Şifrelenmiş şifre ile yeni kullanıcıyı veritabanına kaydeder
    public UserCourse register(UserCourse user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
     return repo.save(user);
    }

    //tüm kulanıcıları db den alır ve döndürür
    public List<UserCourse> getAllUsers() {
        return repo.findAll(); // UserRepo üzerinden findAll metodunu çağırır
    }


    // belirli kullanıcıyı getirir
    public UserCourse findById(String username) {

       UserCourse theUser = null;
        Optional<UserCourse> user = repo.findById(username); //kullanıcı adını kullanarak kullanıcıyı veritabanından bulur.

        if (user.isPresent()){
            theUser = user.get();
        }
        else{
            throw new RuntimeException("The specified id was not found- " + theUser);
        }
        return theUser;

    }

    //kullanıcı silme
    public void deletebyId(String username) {
        repo.deleteById(username);
    }

    //kullanıcı sadece şifre güncellemesi
    public  UserCourse UpdateUser(UserCourse user){
        Optional<UserCourse> theUser = repo.findById(user.getUsername());//kullanıcı adını kullanarak mevcut kullanıcı bulunur.
        if (theUser.isPresent()){ //kullanıcı varsa
            UserCourse UpdateUser = theUser.get(); //user nesnesindeki değeri alır ve  UpdateUser değişkenine atar.
            UpdateUser.setPassword(passwordEncoder.encode(user.getPassword())); //UpdateUser nesnesinin şifresini alır ve şifreler
            return repo.save(UpdateUser);// Güncellenmiş kullanıcıyı veritabanına kaydeder ve döndürür.
        }
      else { //eğer mevcut değilse
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          return repo.save(user); //yeni kullanıcıyı dbye ekle
        }
    }
}
