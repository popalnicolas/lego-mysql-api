package com.example.legomysqlapi.Webservice.Service;

import com.example.legomysqlapi.Model.LegoModel;
import com.example.legomysqlapi.Repository.ILegoRepository;
import com.example.legomysqlapi.Webservice.Service.Interface.ILegoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LegoService implements ILegoService {

    private final ILegoRepository legoRepository;

    @Override
    public List<LegoModel> getAllByCategory(long categoryId) {
        log.info("Getting all legos by category id: {}", categoryId);
        return legoRepository.findAllByCategory_CategoryId(categoryId);
    }

    @Override
    public List<LegoModel> getAllLegos() {
        log.info("Getting all legos from database");
        return legoRepository.findAll();
    }

    @Override
    public List<LegoModel> getAllByAgeRange(short age1, short age2) {
        log.info("Getting all legos in age range from {} to {}", age1, age2);
        return legoRepository.findAllByAgeBetween(age1, age2);
    }

    @Override
    public LegoModel getById(long legoId) {
        log.info("Getting lego by lego id: {} from database", legoId);
        return legoRepository.getById(legoId);
    }

    @Override
    public LegoModel addLego(LegoModel lego) {
        log.info("Saving new lego {} into database", lego.getLegoName());
        legoRepository.save(lego);
        return lego;
    }
}
