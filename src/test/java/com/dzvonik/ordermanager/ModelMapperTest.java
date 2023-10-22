package com.dzvonik.ordermanager;

import com.dzvonik.ordermanager.config.ModelMapperConfig;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ModelMapperConfig.class)
public class ModelMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testStringToLocalDateConversion() {
        String dateString = "2023-10-25";
        LocalDate expectedDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate result = mapper.map(dateString, LocalDate.class);

        assertEquals(expectedDate, result);
    }

}
