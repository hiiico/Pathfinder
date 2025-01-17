package com.example.pathfinder.demo.uuid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class TestUuidService implements CommandLineRunner {
    private final TestUuidRepository testUuidRepository;

    @Autowired
    public TestUuidService(TestUuidRepository testUuidRepository) {
        this.testUuidRepository = testUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        TestUuidEntity testUuidEntity = new TestUuidEntity();
        testUuidEntity.setContent("test content");

        testUuidRepository.save(testUuidEntity);
    }
}
