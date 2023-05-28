package com.chordnation.userservice.service;

import com.chordnation.userservice.domain.dto.SongDTO;
import com.chordnation.userservice.domain.enums.GuitarType;
import com.chordnation.userservice.domain.enums.Level;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "tab-service")
public interface TabFeignClient {
    @GetMapping("/songs/suggested")
    List<SongDTO> getSongs(@RequestParam(required = false, defaultValue = "") List<String> artist,
                           @RequestParam(required = false, defaultValue = "") List<String> genres,
                           @RequestParam Level level,
                           @RequestParam GuitarType guitarType);
}
