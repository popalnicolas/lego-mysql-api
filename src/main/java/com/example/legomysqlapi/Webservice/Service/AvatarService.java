package com.example.legomysqlapi.Webservice.Service;

import com.example.legomysqlapi.Model.AvatarModel;
import com.example.legomysqlapi.Repository.IAvatarRepository;
import com.example.legomysqlapi.Webservice.Service.Interface.IAvatarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AvatarService implements IAvatarService {

    private final IAvatarRepository avatarRepository;

    @Override
    public List<AvatarModel> getAllAvatars() {
        log.info("Getting all avatars from database");
        return avatarRepository.findAll();
    }
}
