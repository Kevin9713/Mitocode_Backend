package com.mitocode.controller;

import com.mitocode.dto.VitalSignsDTO;
import com.mitocode.model.VitalSigns;
import com.mitocode.service.IVitalSignService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vitalsigns")
@RequiredArgsConstructor
public class VitalSignController {

    private final IVitalSignService vitalSignService;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<VitalSignsDTO>> findAll(){
        List<VitalSignsDTO> list = vitalSignService.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignsDTO> findById(@PathVariable("id") Integer id){
        VitalSigns obj = vitalSignService.findById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save (@Valid @RequestBody VitalSignsDTO dto){
        VitalSigns obj = vitalSignService.save(convertToEntity(dto)); //UTILIZANDO MAPPER
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSigns()).toUri();
        return ResponseEntity.created(location).build(); // --> TE CREA STATUS 201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSignsDTO> update (@Valid @PathVariable("id") Integer id, @RequestBody VitalSignsDTO dto){
        //dto.setIdSigns(id);
        VitalSigns obj = vitalSignService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        vitalSignService.delete(id);
        return ResponseEntity.noContent().build();
    }




    private VitalSignsDTO convertToDto (VitalSigns obj){
        return mapper.map(obj, VitalSignsDTO.class);
    }
    private VitalSigns convertToEntity (VitalSignsDTO dto){
        return mapper.map(dto, VitalSigns.class);
    }
}
