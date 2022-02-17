package com.proyect.pokedex.util;

import com.proyect.pokedex.model.PokemonDao;
import me.sargunvohra.lib.pokekotlin.model.*;

import java.util.List;

public class ConverterPokemonToPokemonDao {

    private int languageFlavorTextEntries;
    private int languageGenus;

    public ConverterPokemonToPokemonDao(){};

    public PokemonDao pokemonToPokemonDao(Pokemon pokemon){
        PokemonDao pokemonDao;
        if(pokemon != null){
            pokemonDao = new PokemonDao();
            pokemonDao.setId(pokemon.getId());
            pokemonDao.setNombre(pokemon.getName());
            pokemonDao.setFoto(pokemon.getSprites().getFrontDefault());
            for (PokemonType tipo : pokemon.getTypes()) {
                pokemonDao.getTipos().add(tipo.getType().getName());
            }
            pokemonDao.setPeso(pokemon.getWeight());
            for (PokemonAbility habilidad : pokemon.getAbilities()) {
                pokemonDao.getHabilidades().add(habilidad.getAbility().getName());
            }
        }else{
            pokemonDao = null;
        }
        return pokemonDao;
    }

    public PokemonDao pokemonToPokemonDaoFull(Pokemon pokemon , PokemonSpecies pokemonSpecies, String language){
        changeLanguage(language);
        PokemonDao pokemonDao = pokemonToPokemonDao(pokemon);
        pokemonDao.setDescripcion(pokemonSpecies.getFlavorTextEntries().get(languageFlavorTextEntries).getFlavorText());
        pokemonDao.setInformacion(pokemonSpecies.getGenera().get(languageGenus).getGenus());
        List<PokemonMove> moves = pokemon.getMoves();
        for (int i = 0; i < 4; ++i) {
            pokemonDao.getMovimientos().add(moves.get(i).getMove().getName());
        }
        return pokemonDao;
    }

    private void changeLanguage(String language){
        switch(language)
        {
            case "es" :
                languageFlavorTextEntries = 26;
                languageGenus = 5;
                break;

            case "en" :
                languageFlavorTextEntries = 1;
                languageGenus = 7;
                break;
        }
    }
}
