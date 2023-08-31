package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.adapter.output.database.location.LocationEntity;
import com.jmarcosfmg.rickandmorty.application.config.Constants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "characters")
@EntityListeners(AuditingEntityListener.class)
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String species;

    String gender;

    String status;

    @ManyToOne
    LocationEntity origin;

    @ManyToOne
    LocationEntity location;

    @CreatedDate
    LocalDate creationDate = ZonedDateTime.now(ZoneId.of(Constants.TIMEZONE)).toLocalDate();
}
