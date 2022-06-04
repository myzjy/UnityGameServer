package com.unitygameServer.LoginServer;

import com.zfoo.orm.OrmContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class LoginServer {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(LoginServer.class);
    }
}
