import {Component, OnInit, ViewChild} from '@angular/core';
import {HouseService} from "../../services/house.service";
import {TenantService} from "../../services/tenant.service";
import {Tenant} from "../../model/tenant";
import {ISearch} from "../../model/search";
import {SEARCH_TENANT_CRITERIAS} from "../../consts/search.const";
import {ESearchTenantCriteria} from "../../model/enums/search.enum";
import {TenantModalComponent} from "../tenant-modal/tenant-modal.component";
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html',
  styleUrls: ['./tenant.component.scss'],
  providers: [HouseService, TenantService]
})
export class TenantComponent implements OnInit {
  @ViewChild(TenantModalComponent)
  tenantModalComponent: TenantModalComponent;
  tenants: Tenant[] = [];
  searchCriteria: ESearchTenantCriteria;
  criteria: ISearch[];
  searchText: string;
  isAdmin = AppComponent.isAdmin;

  constructor(private houseService: HouseService, private tenantService: TenantService) {
  }

  ngOnInit(): void {
    this.loadTenants();
    this.loadHouses();
    this.criteria = SEARCH_TENANT_CRITERIAS;
    this.tenantModalComponent.isHouseChanged = false;
  }

  public loadTenants(){
    this.tenantService.getTenants().subscribe(
      (items) => this.tenants = items,
      (error) => console.error(error)
    );
    this.searchText = "";
  }

  public loadHouses(){
    this.houseService.getHouses().subscribe(
      (items) => this.tenantModalComponent.houses = items,
      (error) => console.error(error)
    );
  }

  public findTenants(input: string): void {
    if (this.searchText != "") {
      switch (this.searchCriteria) {
        case ESearchTenantCriteria.TelNum:
          this.tenantService.getTenants(input, "").subscribe(
            (items) => this.tenants = items,
            (error) => console.error(error)
          );
          break;
        case ESearchTenantCriteria.Fio:
          this.tenantService.getTenants("", input).subscribe(
            (items) => this.tenants = items,
            (error) => console.error(error)
          );
          break;
      }
    }
    else this.loadTenants();
  }

  public onCreate(): void {
    this.tenantModalComponent.isUpdate = false;
    this.tenantModalComponent.selectedTenant = new Tenant();
    if (this.tenantModalComponent.selectedHouse.houseId == null){
      this.tenantModalComponent.isHouseChanged = false;
    }
    this.tenantModalComponent.selectedHouse = null;
  }

  public onEdit(tenant: Tenant): void {
    this.tenantModalComponent.isUpdate = true;
    this.tenantModalComponent.selectedTenant = JSON.parse(JSON.stringify(tenant));
    this.tenantModalComponent.selectedHouse = this.tenantModalComponent.selectedTenant.house;
  }

  public deleteTenant(id: number): void {
    this.tenantService.deleteTenant(id).subscribe(
      () => this.loadTenants(),
      (error) => console.error(error)
    );
  }

  public onChangeSelectCriteria(selectedItem: string) {
    if (this.searchText != "") {
      this.findTenants(this.searchText)
    }
  }
}
