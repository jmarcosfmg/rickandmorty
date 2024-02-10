package com.jmarcosfmg.rickandmorty.adapter.input.controller.character;


import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.CharacterInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.CreateCharacterRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.character.dto.UpdateCharacterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CharacterAPI {
    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna o personagem gerado"),
            @ApiResponse(responseCode = "400", description = "Request inválido"),
            @ApiResponse(responseCode = "404", description = "Localidade de origem ou destino não encontrada"),
            @ApiResponse(responseCode = "409", description = "Existe um conflito que impede a efetivação do fluxo"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    CharacterInfoResponse createCharacter(@Valid @RequestBody CreateCharacterRequest request);

    @PutMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update an existing character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o personagem gerado",
                    content = @Content(
                            schema = @Schema(implementation = UpdateCharacterRequest.class),
                            array = @ArraySchema(schema = @Schema(implementation = UpdateCharacterRequest.class))
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Request inválido"),
            @ApiResponse(responseCode = "404", description = "Localidade de origem ou destino não encontrada"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    ResponseEntity<?> updateCharacter(@Valid @RequestBody @Size(min = 1) List<UpdateCharacterRequest> request);

    @GetMapping
    @Operation(summary = "Get a character or list multiple characters",
            parameters = @Parameter(name = "id", description = "lista de ids", allowEmptyValue = true)
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o personagem gerado"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    Page<CharacterInfoResponse> getCharacter(
            @RequestParam(required = false) List<Integer> id,
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) @RequestBody(required = false) Pageable pageable);


    @DeleteMapping("/{ids}")
    @Operation(summary = "Delete one or more characters",
            parameters = @Parameter(name = "ids", description = "lista de ids", allowEmptyValue = false)
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Request inválido"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    void deleteCharacter(@PathVariable(required = true) @Size(min = 1) List<Integer> ids);
}
