package com.chordnation.userservice.service;

import com.chordnation.userservice.domain.dto.SongDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "tab-service")
public interface TabFeignClient {
    @GetMapping("/songs")
    List<SongDTO> getSongs();
}
