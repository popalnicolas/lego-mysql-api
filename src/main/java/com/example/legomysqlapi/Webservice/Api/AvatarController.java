package com.example.legomysqlapi.Webservice.Api;

import com.example.legomysqlapi.Model.AvatarModel;
import com.example.legomysqlapi.Webservice.Service.Interface.IAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lego/avatar")
public class AvatarController {

    private final IAvatarService avatarService;

    @GetMapping
    public ResponseEntity<List<AvatarModel>> getAllAvatars()
    {
        return ResponseEntity.ok().body(avatarService.getAllAvatars());
    }
}
