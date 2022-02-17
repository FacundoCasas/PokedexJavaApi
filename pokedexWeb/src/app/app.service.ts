import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const API_BASE = "http://localhost:8090/pokedex";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(
    private http: HttpClient
  ) { }

  findPaginated(offset:number){
    return this.http.get(`${API_BASE}/${offset}`);
  }   

  findById(id:number){
    return this.http.get(`${API_BASE}/pokemon/${id}`);
  }

  changeLanguage(language:string){
    return this.http.post(`${API_BASE}/language`,language);
  }
}
