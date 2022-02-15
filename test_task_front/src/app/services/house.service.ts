import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {House} from "../model/house";
import {Observable} from "rxjs";

@Injectable()
export class HouseService {

  constructor(private http: HttpClient) {
  }

  private url = "http://localhost:8080/house";

  getHouses(): Observable<House[]> {
    return this.http.get<House[]>(`${this.url}`);
  }

  getHousesByDistrict(district: string): Observable<House[]> {
    let params = new HttpParams().set('district', district);
    return this.http.get<House[]>(this.url, {params});
  }

  getHousesByStreet(street: string): Observable<House[]> {
    let params = new HttpParams().set('street', street);
    return this.http.get<House[]>(this.url, {params});
  }

  addHouse(house: House): Observable<House> {
    return this.http.post<House>(`${this.url}`, house);
  }

  deleteHouse(street: string, houseNumber: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${street}/${houseNumber}`);
  }

  updateHouse(street: string, houseNumber: number, house: House): Observable<House> {
    return this.http.put<House>(`${this.url}/${street}/${houseNumber}`, house);
  }
}
