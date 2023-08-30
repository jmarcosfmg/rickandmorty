package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.adapter.output.database.location.LocationEntity;
import com.jmarcosfmg.rickandmorty.application.config.Constants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String species;

    String gender;

    @ManyToOne
    LocationEntity origin;

    @ManyToOne
    LocationEntity location;

    @CreatedDate
    private LocalDate creationDate = ZonedDateTime.now(ZoneId.of(Constants.TIMEZONE)).toLocalDate();
}
