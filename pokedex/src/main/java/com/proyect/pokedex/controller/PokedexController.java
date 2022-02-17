package com.proyect.pokedex.controller;

import com.proyect.pokedex.model.PokemonDao;
import com.proyect.pokedex.repo.PokedexRepository;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pokedex")
public class PokedexController {


    private PokedexRepository pokedexRepository = new PokedexRepository();

    @GetMapping("")
    NamedApiResourceList findAll(){
        return pokedexRepository.findAll();
    }

    @GetMapping("{offset}")
    List<PokemonDao> findPaginated(@PathVariable int offset){
        return pokedexRepository.paginated(offset);
    }

    @PostMapping("language")
    String changeLanguage(@RequestBody String language){
        return pokedexRepository.changeLanguage(language);
    }

    @GetMapping("pokemon/{id}")
    PokemonDao findById(@PathVariable int id){
        return pokedexRepository.findByIdFull(id);
    }

}
