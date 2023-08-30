package com.jmarcosfmg.rickandmorty.adapter.input.controller.character;


import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.converter.CharacterControllerMapper;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.CreateCharacterRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.CharacterInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.UpdateCharacterRequest;
import com.jmarcosfmg.rickandmorty.application.usecase.character.CreateCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.DeleteCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.ReadCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.UpdateCharacter;
import com.jmarcosfmg.rickandmorty.application.usecase.character.dto.CreateCharacterOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/character")
public class CharacterController extends LogUtils {

    @Autowired
    private CreateCharacter createCharacterUseCase;

    @Autowired
    private UpdateCharacter updateCharacterUseCase;

    @Autowired
    private DeleteCharacter deleteCharacterUseCase;

    @Autowired
    private ReadCharacter readCharacterUseCase;

    @Autowired
    private CharacterControllerMapper mapper;

    @PostMapping
    public CharacterInfoResponse createCharacter(@Valid @RequestBody CreateCharacterRequest request){
        log.info("Starting to process a create character request - {}", request);

        CreateCharacterOutput output = createCharacterUseCase.execute(this.mapper.toInput(request));

        log.info("Finished processing a create character request - {}", request);

        return addSelfUrl(this.mapper.toResponse(output));
    }

    @PutMapping
    public ResponseEntity<?> updateCharacter(@Valid @RequestBody @Size(min = 1) List<UpdateCharacterRequest> request) {
        log.info("Starting to process an update character request - {}", request);

        List<CharacterInfoResponse> output = updateCharacterUseCase.execute(request.stream().map(r -> this.mapper.toInput(r)).toList())
                .parallelStream().map(o -> this.addSelfUrl(this.mapper.toResponse(o))).toList();

        log.info("Finished processing an update character request - {}", request);
        return ResponseEntity.ok().body((output.size() == 1)? output.get(0) : output);
    }

    @GetMapping
    public Page<CharacterInfoResponse> getCharacter(
            @RequestParam(required = false) List<Integer> id,
            @PageableDefault(page = 0, size = 20) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        log.info("Starting to process a get character request - {}", id);

        Page<CharacterInfoResponse> output = readCharacterUseCase.execute((id == null) ? List.of() : id, pageable)
                .map(o -> addSelfUrl(this.mapper.toResponse(o)));

        log.info("Finished processing a get character request - {}", id);
        return output;
    }


    @DeleteMapping("/{ids}")
    public void deleteCharacter(@PathVariable(required = true) @Size(min = 1) List<Integer> ids){
        log.info("Starting to process a delete character request - {}", ids);

        deleteCharacterUseCase.execute(ids);
        log.info("Finished processing a delete character request - {}", ids);
    }

    private CharacterInfoResponse addSelfUrl(CharacterInfoResponse response) {
        response.setUrl(linkTo(methodOn(CharacterController.class)
                .getCharacter(List.of(response.getId()), null)).toString());
        return response;
    }
}
