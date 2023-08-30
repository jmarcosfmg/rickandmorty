package com.jmarcosfmg.rickandmorty.adapter.output.database.location;

import com.jmarcosfmg.rickandmorty.adapter.output.database.character.CharacterEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "locations")
@EntityListeners(AuditingEntityListener.class)
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String dimension;

    @OneToMany
    private List<CharacterEntity> residents;    

    @CreatedDate
    private Date creationDate;
}
