package com.example.legomysqlapi.Webservice.Api;

import com.example.legomysqlapi.Model.LegoModel;
import com.example.legomysqlapi.Webservice.Service.Interface.ILegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lego")
public class LegoController {

    private final ILegoService legoService;

    @GetMapping
    public ResponseEntity<List<LegoModel>> getAllLegoWithParams(
            @RequestParam("categoryId") Optional<Long> categoryId,
            @RequestParam("ageFrom") Optional<Short> ageFrom,
            @RequestParam("ageTo") Optional<Short> ageTo

            )
    {
        if(categoryId.isPresent())
        {
            return ResponseEntity.ok().body(legoService.getAllByCategory(categoryId.get()));
        }
        else if(ageFrom.isPresent() && ageTo.isPresent())
        {
            return ResponseEntity.ok().body(legoService.getAllByAgeRange(ageFrom.get(), ageTo.get()));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/newest")
    public ResponseEntity<List<LegoModel>> getNewestLegoById()
    {
        return ResponseEntity.ok().body(legoService.getTopTwoOrderedById());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<LegoModel> getLegoById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(legoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LegoModel> addLego(LegoModel lego)
    {
        return ResponseEntity.ok().body(legoService.addLego(lego));
    }
}
