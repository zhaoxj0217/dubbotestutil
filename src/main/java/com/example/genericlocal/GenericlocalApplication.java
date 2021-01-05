package com.example.genericlocal;

import com.example.genericlocal.demo.swing.SwingArea;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GenericlocalApplication {

    public GenericlocalApplication() {
        SwingArea.getInstance().initUI();
    }
    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(GenericlocalApplication.class)
                .headless(false).run(args);
    }


}
