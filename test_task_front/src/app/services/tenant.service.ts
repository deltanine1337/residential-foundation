import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tenant} from "../model/tenant";

@Injectable()
export class TenantService {

  constructor(private http: HttpClient) {
  }

  private url = "http://localhost:8080/tenant";

  getTenants(): Observable<Tenant[]> {
    return this.http.get<Tenant[]>(`${this.url}`);
  }

  getTenantsByTelNum(telNum: string): Observable<Tenant[]> {
    let params = new HttpParams().set('telNum', telNum);
    return this.http.get<Tenant[]>(this.url, {params});
  }

  getTenantsByFio(fio: string): Observable<Tenant[]> {
    let params = new HttpParams().set('fio', fio);
    return this.http.get<Tenant[]>(this.url, {params});
  }

  addTenant(tenant: Tenant): Observable<Tenant> {
    return this.http.post<Tenant>(this.url, tenant);
  }

  deleteTenant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  updateTenant(id: number, tenant: Tenant): Observable<Tenant> {
    return this.http.put<Tenant>(`${this.url}/${id}`, tenant);
  }
}
