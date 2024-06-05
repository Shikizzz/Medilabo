package com.medilabo.info.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {
    @Override
    public String convertToDatabaseColumn(Genre genre) {
        if (genre == null) {
            return null;
        }
        return genre.getGenre();
    }

    @Override
    public Genre convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Genre.values())
                .filter(c -> c.getGenre().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
