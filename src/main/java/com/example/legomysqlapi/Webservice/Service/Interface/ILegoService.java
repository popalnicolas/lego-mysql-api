package com.example.legomysqlapi.Webservice.Service.Interface;

import com.example.legomysqlapi.Model.LegoModel;

import java.util.List;

public interface ILegoService {
    List<LegoModel> getAllByCategory(long categoryId);
    List<LegoModel> getAllLegos();
    List<LegoModel> getAllByAgeRange(short age1, short age2);
    List<LegoModel> getTopTwoOrderedById();
    LegoModel getById(long legoId);

    LegoModel addLego(LegoModel lego);
}
