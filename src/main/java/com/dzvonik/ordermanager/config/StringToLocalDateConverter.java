package com.dzvonik.ordermanager.config;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(MappingContext<String, LocalDate> context) {
        if (context.getSource() == null) {
            return null;
        }
        return LocalDate.parse(context.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}

