package com.tablemaster_api;

import org.springframework.boot.SpringApplication;

public class TestTableMasterApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(TableMasterApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
