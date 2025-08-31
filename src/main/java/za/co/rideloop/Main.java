package za.co.rideloop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.rideloop.Domain.*;
import za.co.rideloop.Factory.CarFactory;
import za.co.rideloop.Factory.InvoiceFactory;

import java.util.Date;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {


       SpringApplication.run(Main.class ,args);

    }}

