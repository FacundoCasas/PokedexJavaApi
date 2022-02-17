import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  pokemons: any[] = [];
  pokemon = {
    id:null,
    nombre:"",
    foto:"",
    tipos:"",
    peso:0,
    informacion:"",
    descripcion:"",
    movimientos:"",
  }
  laguages:string[] = ["es","en"];
  language:string = "es";
  pokemonSelected:boolean = false;
  page:number = 0;
  max_pokemons = 9;
  pages:number[] = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16];
  paginated:number = 0;

  constructor(
    private appService: AppService,
  ){}

  ngOnInit(): void {
    this.findPaginated(this.paginated);
  }

  findPaginated(offset:number){
    this.appService.findPaginated(offset)
    .subscribe((data: any) => this.pokemons = data);
  }

  buttonPaginated(button:number){
    this.appService.findPaginated(button * this.max_pokemons)
      .subscribe((data: any) => this.pokemons = data); 
  }

  searchPokemon(id:number) {
    this.appService.findById(id)
    .subscribe((data: any) => this.pokemon = data);
    this.pokemonSelected = true;
  }
  
  changeSelect(){
    this.pokemonSelected = false;
  }

  changeLanguage(language:string){
    this.appService.changeLanguage(language)
    .subscribe((data: any) => this.language = data);
  }

}
