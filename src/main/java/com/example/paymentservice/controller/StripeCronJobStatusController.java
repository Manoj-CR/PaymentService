package com.example.paymentservice.controller;

import com.example.paymentservice.service.StripeCronJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/stripeStatus")
public class StripeCronJobStatusController {

    @Autowired
    private StripeCronJobService stripeCronJobService;

    //Eevry 5 min it will do a cron job
//    @Scheduled(cron = "*/10 * * * * *")
//    @Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(cron = "0 0/1 * * * ?")
    public void getPendingTransactionStatus(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
        stripeCronJobService.getPendingTransactionStatus();



    }
}
