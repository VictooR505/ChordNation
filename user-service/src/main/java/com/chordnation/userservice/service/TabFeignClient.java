package com.chordnation.userservice.service;

import com.chordnation.userservice.domain.dto.SongDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "tab-service")
public interface TabFeignClient {
    @GetMapping("/songs/filter")
    List<SongDTO> getSongs(@RequestParam(required = false) List<String> artist,
                           @RequestParam(required = false)  List<String> genre);
}
