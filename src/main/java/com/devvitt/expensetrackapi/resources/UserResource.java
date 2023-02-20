package com.devvitt.expensetrackapi.resources;

import com.devvitt.expensetrackapi.domain.User;
import com.devvitt.expensetrackapi.services.UserService;
import com.devvitt.expensetrackapi.utils.Constant;
import com.devvitt.expensetrackapi.utils.UserAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Map<String,Object> userMap){
        String email = userMap.get("email").toString();
        String password = userMap.get("password").toString();
        User userLogged = userService.validateUser(email,password);
        return new ResponseEntity<>(generateJWT(userLogged),HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> createUser(@RequestBody Map<String,Object> userMap){
        User userData = new UserAdapter().getDataFromRequest(userMap);
        System.out.println(userData);
        User userCreated = userService.registerUser(userData);
        return new ResponseEntity<>(generateJWT(userCreated), HttpStatus.OK);
    }

    @GetMapping("/listOfUsers")

    public ResponseEntity<List<User>> getListOfUsers(@RequestParam(required = false) String id){
        List<User> list = userService.listOfUsers();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    private Map<String, String> generateJWT(User user){
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constant.API_SECRET_KEY)
                .setIssuedAt(new Date(timeStamp)).setExpiration(new Date(timeStamp + Constant.TOKEN_VALIDITY))
                .claim("userId", user.getUserId()).claim("email", user.getEmail())
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }
}
