package com.proyect.pokedex.repo;

import com.proyect.pokedex.model.PokemonDao;
import com.proyect.pokedex.util.ConverterPokemonToPokemonDao;
import me.sargunvohra.lib.pokekotlin.client.ClientConfig;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokedexRepository {

    static final int TOTAL_POKEMON = 151;
    static final int MAX_POKEMON = 9;
    static final String[] LANGUAJES = {"es","en"};

    private String language = "es";

    private ConverterPokemonToPokemonDao converterPokemonToPokemonDao = new ConverterPokemonToPokemonDao();
    private ClientConfig clientConfig = new ClientConfig();
    private PokeApi pokeApi = new PokeApiClient(clientConfig);

    public List<PokemonDao> paginated(int offset){
        if (offset < TOTAL_POKEMON){
            return findPokemonsOffset(offset);
        }else{
            throw new RuntimeException("Se Exedio Cantidad Disponible de pokemons");
        }
    }

    public PokemonDao findByIdFull(int id){
        if (id < TOTAL_POKEMON){
            return converterPokemonToPokemonDao.pokemonToPokemonDaoFull(findPokemon(id),findPokemonSpecies(id),language);
        }else{
            throw new RuntimeException("Se Exedio Cantidad Disponible de pokemons");
        }
    }

    public NamedApiResourceList findAll(){
        NamedApiResourceList pokemons = pokeApi.getPokemonList(1,TOTAL_POKEMON);
        return pokemons;
    }

    public String changeLanguage(String language){
        for (int i = 0; i < LANGUAJES.length ;i++){
            if (LANGUAJES[i].equals(language)){
                this.language = language;
            }
        }
        return this.language;
    }

    private List<PokemonDao> findPokemonsOffset(int offset){
        List<PokemonDao> pokemonsDao = new ArrayList<>();
        int offsetMax;
        if (offset > TOTAL_POKEMON - MAX_POKEMON){
            offsetMax = TOTAL_POKEMON;
        }else{
            offsetMax = offset + MAX_POKEMON;
        }
        PokemonDao pokemonAux;

        for (int i = 1; i < offsetMax - offset + 1; i++) {
            pokemonAux = this.findById(i + offset);
            pokemonsDao.add(pokemonAux);
        }
        return pokemonsDao;
    }

    private PokemonDao findById(int id){
        return converterPokemonToPokemonDao.pokemonToPokemonDao(findPokemon(id));
    }

    private Pokemon findPokemon(int id){
        return pokeApi.getPokemon(id);
    }

    private PokemonSpecies findPokemonSpecies(int id){
        return pokeApi.getPokemonSpecies(id);
    }
}
