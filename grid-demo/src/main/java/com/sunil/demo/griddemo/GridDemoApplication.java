package com.sunil.demo.griddemo;

import com.sunil.demo.griddemo.processors.GridProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GridDemoApplication implements CommandLineRunner {

    @Autowired
    private GridProcessor gridProcessor;

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(GridDemoApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        int exit = 1;
        Scanner scanner;
        while (exit == 1) {
            System.out.println("=============================================================");
            scanner = new Scanner(System.in);
            gridProcess(scanner);
            System.out.println("Enter 0 to exit or 1 to continue : ");
            exit = scanner.nextInt();
        }
    }

    private void gridProcess(Scanner scanner) {

        System.out.print("Enter number of rows ? ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns ? ");
        int columns = scanner.nextInt();
        System.out.print("Enter number of nodes ? ");
        int noOfNodes = scanner.nextInt();

        System.out.println("create Grid of (" + rows + " X " + columns + ") with " + noOfNodes + " nodes");

        gridProcessor.doProcessing(rows, columns, noOfNodes);

    }
}
