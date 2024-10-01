package com.example.SpringSecurityProject.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//@Configuration anotasyonu, Java sınıfının Spring IoC konteyneri tarafından yönetilen bean'leri
// tanımlamak için kullanılacağını belirtir.
@Configuration
@EnableWebSecurity // Spring Security'yi etkinleştirir ve web güvenlik yapılandırmasını sağlar.servlet temelli güvenlik yapılandırılması
public class SecurityConfig {



//    Spring IoC  container tarafından yönetilen nesneleri ifade eder.

    // Kullanıcı bilgilerini bellek içinde yönetmek
//    @Bean
//
//    //Bu, kullanıcı detaylarının bellek içinde yönetilmesini sağlayan bir sınıftır ve genellikle kullanıcı kimlik
//    // doğrulama ve yetkilendirme işlemleri için kullanılır.
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails admin = User.builder() //User nesnesini yapılandırmak için bir builder nesnesi oluşturur.
//                .username("admin") //username() metodu, User.Builder nesnesine kullanıcı adını atar.
//                .password("{noop}*1213test") //{noop} şifre kodlama işlemi yapılmadığını belirtir.
//                .roles("ADMIN") //uygulama üzerindeki izin gruplarını ifade eder.
//                .build(); //Bu metod çağrıldığında, username ve roles ile yapılandırılmış bir ADMIN nesnesi oluşturulur
//                          // ve admin değişkenine atanır.
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}test1819*")
//                .roles("USER")
//                .build();
//
   // admin ve user kullanıcılarını hafızada saklayan ve onlara erişimi sağlayan bir InMemoryUserDetailsManager nesnesi oluştur.
//        return new InMemoryUserDetailsManager(admin,user);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    //Bu, kullanıcı detaylarının bellek içinde yönetilmesini sağlayan bir sınıftır ve genellikle kullanıcı kimlik
//    // doğrulama ve yetkilendirme işlemleri için kullanılır.
//    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder){
//        UserDetails admin = User.builder() //User nesnesini yapılandırmak için bir builder nesnesi oluşturur.
//                .username("admin") //username() metodu, User.Builder nesnesine kullanıcı adını atar.
//                .password(passwordEncoder.encode("*1213test")) //{noop} şifre kodlama işlemi yapılmadığını belirtir.
//                .roles("ADMIN") //uygulama üzerindeki izin gruplarını ifade eder.
//                .build(); //Bu metod çağrıldığında, username ve roles ile yapılandırılmış bir ADMIN nesnesi oluşturulur
//                          // ve admin değişkenine atanır.
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("test1819*"))
//                .roles("USER")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(admin,user);
//    }


    //Kullanıcı bilgilerini veritabanından çekmek
//    @Bean
////    UserDetailsService Kullanıcı bilgilerini Spring Security'ye sağlayan bir hizmettir.
////     kullanıcının kimlik bilgilerini ve yetkilerini (rollerini) almak için kullanılır.
//
////    DataSource Veritabanına bağlantı sağlayan bir yapıdır.
//
//    public UserDetailsService userDetailsService(DataSource dataSource){
//
//        //JdbcUserDetailsManager, JDBC kullanarak kullanıcı bilgilerini ve yetkilerini veritabanından alan bir sınıftır.
//
//        //JdbcUserDetailsManager sınıfından bir örnek oluşturur ve veritabanına bağlanmak için dataSource nesnesini kullanır.
//        // Bu, uygulamanın kullanıcı bilgilerini veritabanı üzerinden yapabilmesini sağlar.
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//
//        // Veritabanından kullanıcı bilgilerini almak için kullanılan SQL sorgusu
//        //users adındaki veritabanı tablosundan, belirtilen kullanıcı adına (username) sahip olan kayıtların username, password ve enabled bilgilerini çeker.
//        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
//
//        // Veritabanından kullanıcı yetkilerini almak için kullanılan SQL sorgusu
//        //authorities adındaki veritabanı tablosundan, belirtilen kullanıcı adına (username) sahip olan kayıtların username ve authority (yetki) bilgilerini çeker.
//        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
//
//        // yapılandırılmış JdbcUserDetailsManager nesnesi döndürülür.
//        return manager;
//    }
//
//
//
    //Spring uygulaması çalışırken, veritabanı ile etkileşim kurmak için bu nesne kullanılır.
    @Autowired
    private DataSource dataSource;

    //Bu metod, şifrelerin şifrelenmesi için kullanılacak PasswordEncoder bean'ini tanımlar.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //şifreleri BCrypt algoritması ile şifreler.
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        return manager;
    }

    //şifrelerin doğrulanması ve kullanıcı bilgilerinin alınması
    @Bean
    public AuthenticationProvider authenticationProvider() { //Kullanıcı doğrulama işlemlerini sağlar.
        //Kullanıcı doğrulama işlemleri için kullanılan bir sağlayıcıdır. Şifrelerin şifrelenmiş olup olmadığını kontrol eder
        // ve UserDetailsService'den  kullanıcı bilgilerini alır ve şifreleri bir PasswordEncoder ile doğrular.
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); //şifrelerin nasıl şifreleneceğini belirler ve DaoAuthenticationProvider, kullanıcı şifrelerini bu PasswordEncoder kullanarak doğrular.
        provider.setUserDetailsService(userDetailsService(dataSource));//Bu metod, veritabanı bağlantısını kullanarak kullanıcı bilgilerini çeker
        return provider;//Bu nesne, kullanıcı doğrulama işlemlerini yapacak ve şifreleri kontrol edecek.
    }



   @Bean

   //SecurityFilterChain :Spring Security tarafından kullanılan bir yapı olup, HTTP güvenlik filtrelerini yönetir ve belirli güvenlik kurallarını uygular.

   //HttpSecurity, HTTP istekleri için güvenlik yapılandırmasını tanımlamanızı sağlar. HttpSecurity nesnesi, güvenlik filtreleri, yetkilendirme, kimlik doğrulama
   // ve diğer güvenlik ayarlarını yapılandırmak için kullanılır.

   // throws Exception ise hataları fırlatır.

   // bu metodun içerisinde kimlik doğrulama ,yetkilendirme ve güvenlik filtreleri bulunur.

   public SecurityFilterChain  filterChain(HttpSecurity httpSecurity) throws Exception{

        //authorizeHttpRequests: Bu metod, HTTP istekleri için yetkilendirme kurallarını tanımlar.
        //configurer, yetkilendirme yapılandırmasını yapmak için kullanılan bir nesnedir ve Lambda ifadesi (fonksiyon) alarak yapılandırılmasını sağlar.
       httpSecurity.authorizeHttpRequests(configurer ->
              configurer
                      //requestMatchers: Bu metod, belirli HTTP istek yolları (URL'ler) ve yöntemleri (GET, POST vb.) için yetkilendirme kuralları tanımlar.
                      //hasAnyRole: Bu metod, kullanıcının belirtilen rollerden herhangi birine sahip olup olmadığını kontrol eder.
                      .requestMatchers(HttpMethod.GET, "/coursesApi/courses").hasAnyRole("ADMIN","USER")
                      .requestMatchers(HttpMethod.POST, "/coursesApi/courses").hasRole("ADMIN")
                      .requestMatchers(HttpMethod.PUT, "/coursesApi/courses").hasRole("ADMIN")
                      .requestMatchers(HttpMethod.DELETE, "/coursesApi/courses/**").hasRole("ADMIN") //** 'nın nedeni diğer alt yollarıda almak.
                      .requestMatchers(HttpMethod.GET, "/coursesApi/courses/**").hasAnyRole("ADMIN","USER")

                      .requestMatchers(HttpMethod.POST, "/register").hasRole("ADMIN") // sadece admin
                      .requestMatchers(HttpMethod.GET, "/register").permitAll() //Herkese açık
                      .requestMatchers(HttpMethod.DELETE, "/register/**").hasRole("ADMIN")
                      .requestMatchers(HttpMethod.GET, "/register/**").permitAll()
                      .requestMatchers(HttpMethod.PUT, "/register").hasRole("ADMIN")

                      // Swagger UI ve API dokümanlarına izin ver
                      //.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() //doğrulama gerektirmez
                      .requestMatchers("/swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**").authenticated() //Swagger URL'leri için doğrulama gerektirir
                      .requestMatchers("/coursesApi/**").authenticated()
       );

       // Bu ifade Spring Security'de HTTP Basic Authentication'ı varsayılan ayarlarla etkinleştiren bir yapılandırma satırıdır.
       //HTTP Basic Authentication, web uygulamalarında kullanıcı kimlik doğrulaması için kullanılan basit bir yöntemdir.
       httpSecurity.httpBasic(Customizer.withDefaults());

       //CSRF, kötü niyetli sitelerin kullanıcıların oturum bilgilerini kullanarak yetkisiz işlemler yapmasını sağlayan bir saldırı türüdür.
       //POST, PUT, DELETE gibi işlemler için CSRF token  gerekmektedir.
       httpSecurity.csrf(csrf -> csrf.disable());



       //HttpSecurity yapılandırmasını tamamlar ve bir SecurityFilterChain nesnesi döndürür.
      return httpSecurity.build();
   }

}
