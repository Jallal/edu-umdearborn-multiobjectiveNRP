
package com.edu.umich.ISELab.webinterface.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index() {



        String[][] data = new String[300][300];

        //Our Taks
        data[0][0]="Task 1";
        data[1][0]="Task 2";
        data[2][0]="Task 3";
        data[3][0]="Task 4";
        data[4][0]="Task 5";
        data[5][0]="Task 6";
        data[6][0]="Task 7";
        data[7][0]="Task 8";
        data[8][0]="Task 9";


        //People
        data[0][1]="Jallal elhazzat";
        data[1][1]="Ashley Snyder";
        data[2][1]="Sri Padadda";
        data[3][1]="Steve Snyder";
        data[4][1]="John DOe";
        data[5][1]="Ed Doe";
        data[6][1]="Moe Ali";
        data[7][1]="Stephan Moe";
        data[8][1]="Steve Snyder";

        // X Axis timeline

        data[0][2]=String.valueOf(new Date(2019, 5, 1));
        data[1][2]=String.valueOf(new Date(2019, 5, 1));
        data[2][2]=String.valueOf(new Date(2019, 5, 1));
        data[3][2]=String.valueOf(new Date(2019, 5, 1));
        data[4][2]=String.valueOf(new Date(2019, 5, 1));
        data[5][2]=String.valueOf(new Date(2019, 5, 1));
        data[6][2]=String.valueOf(new Date(2019, 5, 1));
        data[7][2]=String.valueOf(new Date(2019, 5, 1));;
        data[8][2]=String.valueOf(new Date(2019, 5, 1));


        // Y Axis timeline
        data[0][3]=String.valueOf(new Date(2019, 5, 5));
        data[1][3]=String.valueOf(new Date(2019, 5, 10));
        data[2][3]=String.valueOf(new Date(2019, 5, 12));
        data[3][3]=String.valueOf(new Date(2019, 5, 6));
        data[4][3]=String.valueOf(new Date(2019, 5, 12));
        data[5][3]=String.valueOf(new Date(2019, 5, 15));
        data[6][3]=String.valueOf(new Date(2019, 5, 15));
        data[7][3]=String.valueOf(new Date(2019, 5, 10));;
        data[8][3]=String.valueOf(new Date(2019, 5, 10));



            /*[['Task 1', 'Jallal elhazzat', new Date(2019, 5, 1), new Date(2019, 5, 5)],
            ['Task 2', 'Ashley Snyder', new Date(2019, 5, 1), new Date(2019, 5, 10)],
            ['Task 3', 'Sri Padadda', new Date(2019, 5, 1), new Date(2019, 5, 12)],
            ['Task 4', 'Steve Snyder', new Date(2019, 5, 1), new Date(2019, 5, 6)],
            ['Task 5', 'Jallal elhazzat', new Date(2019, 5, 5), new Date(2019, 5, 12)],
            ['Task 6', 'Steve Snyder', new Date(2019, 5, 7), new Date(2019, 5, 15)]]

        }*/



        return "ajax";
    }

}