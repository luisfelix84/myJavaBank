package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();

    }

    private void bootStrap() {

       ApplicationContext ac = new ClassPathXmlApplicationContext();

       Controller controller = ac.getBean(LoginController.class);

        // start application
        controller.init();
    }
}
