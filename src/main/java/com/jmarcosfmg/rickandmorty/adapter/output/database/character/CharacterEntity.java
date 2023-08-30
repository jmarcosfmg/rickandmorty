package com.jmarcosfmg.rickandmorty.adapter.output.database.character;

import com.jmarcosfmg.rickandmorty.adapter.output.database.location.LocationEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    LocationEntity origin;
}
