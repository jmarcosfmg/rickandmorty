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
import org.springframework.data.domain.PageRequest;
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
public class LocationController extends LogUtils implements LocationAPI {

    @Autowired
    private CreateLocation createLocationUseCase;

    @Autowired
    private UpdateLocation updateLocationUseCase;

    @Autowired
    private DeleteLocation deleteLocationUseCase;

    @Autowired
    private ReadLocation readLocationUseCase;

    private LocationControllerMapper mapper = Mappers.getMapper(LocationControllerMapper.class);

    @Override
    public LocationInfoResponse createLocation(@Valid @RequestBody CreateLocationRequest request) {
        log.info("Starting to process a CREATE location request - {}", request);

        CreateLocationOutput output = createLocationUseCase.execute(this.mapper.toInput(request));

        log.info("Finished processing a CREATE location request - {}", request);

        return addSelfUrl(this.mapper.toResponse(output));
    }

    @Override
    public ResponseEntity<?> updateLocation(List<UpdateLocationRequest> request) {
        log.info("Starting to process an UPDATE location request - {}", request);

        List<LocationInfoResponse> output = updateLocationUseCase.execute(request.stream().map(r -> this.mapper.toInput(r)).toList())
                .parallelStream().map(o -> this.addSelfUrl(this.mapper.toResponse(o))).toList();

        log.info("Finished processing an UPDATE location request - {}", request);
        return ResponseEntity.ok().body((output.size() == 1) ? output.get(0) : output);
    }

    @Override
    public Page<LocationInfoResponse> getLocation(List<Integer> id,
                                                  Integer size,
                                                  Integer page,
                                                  String[] sort,
                                                  String direction) {

        log.info("Starting to process a GET location request - {}", id);

        Pageable pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));

        Page<LocationInfoResponse> output = readLocationUseCase.execute((id == null) ? List.of() : id, pageRequest)
                .map(o -> addSelfUrl(this.mapper.toResponse(o)));

        log.info("Finished processing a GET location request - {}", id);
        return output;
    }


    @Override
    public void deleteLocation(List<Integer> ids) {
        log.info("Starting to process a DELETE location request - {}", ids);

        deleteLocationUseCase.execute(ids);
        log.info("Finished processing a DELETE location request - {}", ids);
    }

    private LocationInfoResponse addSelfUrl(LocationInfoResponse response) {
        response.setUrl(linkTo(methodOn(LocationController.class)
                .getLocation(List.of(response.getId()), null, null, null, null)).toString());
        return response;
    }
}
