package ru.skypro.homework.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BasicAuthCorsFilterTest {
    BasicAuthCorsFilter basicAuthCorsFilter = new BasicAuthCorsFilter();
    @Test
    void doFilterInternal() {
        HttpServletResponse httpServletResponse;

    }
}