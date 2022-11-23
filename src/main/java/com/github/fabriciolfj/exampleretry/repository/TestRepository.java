package com.github.fabriciolfj.exampleretry.repository;

import com.github.fabriciolfj.exampleretry.dto.Test;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestRepository {

    public Optional<Test> find() {
        //throw new RuntimeException("error");
        return Optional.empty();
    }
}
