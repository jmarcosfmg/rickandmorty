package com.jmarcosfmg.rickandmorty.adapter.input.controller.location;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.CreateLocationRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.LocationInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.UpdateLocationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

public interface LocationAPI {

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns the created location"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Application failed to create location"),
    })
    LocationInfoResponse createLocation(@Valid @RequestBody CreateLocationRequest request);

    @PutMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update one or more locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the created location"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Location was not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to update location"),
    })
    ResponseEntity<?> updateLocation(@Valid @RequestBody @Size(min = 1) List<UpdateLocationRequest> request);

    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve one or more locations",
            parameters = {
                    @Parameter(name = "page", description = "Page number (if applicable)", hidden = true),
                    @Parameter(name = "size", description = "Number of elements in a page", hidden = true),
                    @Parameter(name = "sort", description = "Location attribute used for sorting", hidden = true,
                            examples = {
                                    @ExampleObject("id"), @ExampleObject("name"),
                                    @ExampleObject("dimension"), @ExampleObject("creationDate")}),
                    @Parameter(name = "direction", description = "Direction used for sorting", hidden = true,
                            examples = {@ExampleObject("asc"), @ExampleObject("desc")})
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns found locations"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Location was not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to update location"),
    })
    Page<LocationInfoResponse> getLocation(
            @RequestParam(required = false) List<Integer> id,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "id") String[] sort,
            @RequestParam(required = false, defaultValue = "asc") String direction
    );


    @DeleteMapping(path = "/{ids}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete one or more locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations were deleted"),
            @ApiResponse(responseCode = "404", description = "One or more locations were not found"),
            @ApiResponse(responseCode = "500", description = "Application failed to delete location"),
    })
    void deleteLocation(@PathVariable(required = true) @Size(min = 1) List<Integer> ids);
}
