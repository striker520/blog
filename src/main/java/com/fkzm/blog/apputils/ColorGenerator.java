package com.fkzm.blog.apputils;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class ColorGenerator {
    private static final String[] COLORS=new String[]{"red","blue","yellow","teal","orange","violet","brown","pink","grey","olive","purple"};
    private static final Random RANDOM=new Random();
    public String generateColor(){
        return COLORS[RANDOM.nextInt(11)];
    }
}
