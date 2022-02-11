import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {House} from "../model/house";
import {Observable} from "rxjs";

@Injectable()
export class HouseService {

  constructor(private http: HttpClient) {
  }

  private url = "http://localhost:8080/house";

  getHouses(): Observable<House[]> {
    return this.http.get<House[]>(`${this.url}/get`);
  }

  getHousesByDistrict(district: string): Observable<House[]> {
    return this.http.get<House[]>(`${this.url}/get?district=${district}`);
  }

  getHousesByStreet(street: string): Observable<House[]> {
    return this.http.get<House[]>(`${this.url}/get?street=${street}`);
  }

  addHouse(house: House): Observable<House> {
    return this.http.post<House>(`${this.url}/add`, house);
  }

  deleteHouse(street: string, houseNumber: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${street}/${houseNumber}`);
  }

  updateHouse(street: string, houseNumber: number, house: House): Observable<House> {
    return this.http.put<House>(`${this.url}/update/${street}/${houseNumber}`, house);
  }
}