package com.jmarcosfmg.rickandmorty.adapter.input.controller.location;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.converter.LocationControllerMapper;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.CreateLocationRequest;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.LocationInfoResponse;
import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.dto.UpdateLocationRequest;
import com.jmarcosfmg.rickandmorty.application.usecase.location.CreateLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.DeleteLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.ReadLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.UpdateLocation;
import com.jmarcosfmg.rickandmorty.application.usecase.location.dto.CreateLocationOutput;
import com.jmarcosfmg.rickandmorty.application.utils.LogUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.mapstruct.factory.Mappers;
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
@RequestMapping("/location")
public class LocationController extends LogUtils {

    @Autowired
    private CreateLocation createLocationUseCase;

    @Autowired
    private UpdateLocation updateLocationUseCase;

    @Autowired
    private DeleteLocation deleteLocationUseCase;

    @Autowired
    private ReadLocation readLocationUseCase;

    private LocationControllerMapper mapper = Mappers.getMapper(LocationControllerMapper.class);

    @PostMapping
    public LocationInfoResponse createLocation(@Valid @RequestBody CreateLocationRequest request) {
        log.info("Starting to process a create location request - {}", request);

        CreateLocationOutput output = createLocationUseCase.execute(this.mapper.toInput(request));

        log.info("Finished processing a create location request - {}", request);

        return addSelfUrl(this.mapper.toResponse(output));
    }

    @PutMapping
    public ResponseEntity<?> updateLocation(@Valid @RequestBody @Size(min = 1) List<UpdateLocationRequest> request) {
        log.info("Starting to process an update location request - {}", request);

        List<LocationInfoResponse> output = updateLocationUseCase.execute(request.stream().map(r -> this.mapper.toInput(r)).toList())
                .parallelStream().map(o -> this.addSelfUrl(this.mapper.toResponse(o))).toList();

        log.info("Finished processing an update location request - {}", request);
        return ResponseEntity.ok().body((output.size() == 1) ? output.get(0) : output);
    }

    @GetMapping
    public Page<LocationInfoResponse> getLocation(
            @RequestParam(required = false) List<Integer> id,
            @PageableDefault(page = 0, size = 20) @SortDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        log.info("Starting to process a get location request - {}", id);

        Page<LocationInfoResponse> output = readLocationUseCase.execute((id == null) ? List.of() : id, pageable)
                .map(o -> addSelfUrl(this.mapper.toResponse(o)));

        log.info("Finished processing a get location request - {}", id);
        return output;
    }


    @DeleteMapping("/{ids}")
    public void deleteLocation(@PathVariable(required = true) @Size(min = 1) List<Integer> ids) {
        log.info("Starting to process a delete location request - {}", ids);

        deleteLocationUseCase.execute(ids);
        log.info("Finished processing a delete location request - {}", ids);
    }

    private LocationInfoResponse addSelfUrl(LocationInfoResponse response) {
        response.setUrl(linkTo(methodOn(LocationController.class)
                .getLocation(List.of(response.getId()), null)).toString());
        return response;
    }
}
