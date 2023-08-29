package com.jmarcosfmg.rickandmorty.adapter.input.controller.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmarcosfmg.rickandmorty.adapter.input.controller.location.converter.ControllerMapper;
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

    @Autowired
    private ControllerMapper mapper;
    
    @PostMapping
    public LocationInfoResponse createLocation(@Valid @RequestBody CreateLocationRequest request){
        log.info("Starting to process a create location request - {}", request);

        CreateLocationOutput output = createLocationUseCase.execute(this.mapper.toInput(request));
        
        log.info("Finished processing a create location request - {}", request);
        return this.mapper.toResponse(output);
    }

    @PutMapping
    public ResponseEntity<?> updateLocation(@Valid @RequestBody @Size(min = 1) List<UpdateLocationRequest> request) {
        log.info("Starting to process an update location request - {}", request);
    
        List<LocationInfoResponse> output = updateLocationUseCase.execute(request.stream().map(r -> this.mapper.toInput(r)).toList())
            .parallelStream().map(o -> this.mapper.toResponse(o)).toList();
        log.info("Finished processing an update location request - {}", request);
        return ResponseEntity.ok().body((output.size() == 1)? output.get(0) : output);
    }
    
    @GetMapping
    public ResponseEntity<?> getLocation(@RequestParam(required = false) List<Integer> id) {
        log.info("Starting to process a get location request - {}", id);

        List<LocationInfoResponse> output = readLocationUseCase.execute((id == null) ? List.of() : id)
            .parallelStream().map(o -> this.mapper.toResponse(o)).toList();

        log.info("Finished processing a get location request - {}", id);
        return ResponseEntity.ok().body((output.size() == 1)? output.get(0) : output);
    }


    @DeleteMapping("/{ids}")
    public void deleteLocation(@PathVariable(required = true) @Size(min = 1) List<Integer> ids){     
        log.info("Starting to process a delete location request - {}", ids);
    
        deleteLocationUseCase.execute(ids);
        log.info("Finished processing a delete location request - {}", ids);
    }
}
