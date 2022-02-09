import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tenant} from "../model/tenant";

@Injectable()
export class TenantService {

  constructor(private http: HttpClient) {
  }

  private url = "http://localhost:8080/tenant";

  getTenants(): Observable<Tenant[]> {
    return this.http.get<Tenant[]>(`${this.url}/get`);
  }

  addTenant(tenant: Tenant): Observable<Tenant> {
    return this.http.post<Tenant>(`${this.url}/add`, tenant);
  }

  deleteTenant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }

  updateTenant(id: number, tenant: Tenant): Observable<Tenant> {
    return this.http.put<Tenant>(`${this.url}/update/${id}`, tenant);
  }
}
