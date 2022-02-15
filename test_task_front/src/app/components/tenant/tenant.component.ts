import { Component, OnInit } from '@angular/core';
import {HouseService} from "../../services/house.service";
import {TenantService} from "../../services/tenant.service";
import {House} from "../../model/house";
import {Tenant} from "../../model/tenant";
import {District} from "../../model/district";

@Component({
  selector: 'app-tenant',
  templateUrl: './tenant.component.html',
  styleUrls: ['./tenant.component.scss'],
  providers: [HouseService, TenantService]
})
export class TenantComponent implements OnInit {

  private houseService: HouseService;
  private tenantService: TenantService;

  houses: House[] = [];
  tenants: Tenant[] = [];
  selectedTenant = new Tenant();
  selectedHouse = new House(null, 0, 0, 0, new District(0, ''));
  isUpdate: boolean;
  isHouseChanged: boolean;

  searchCriteria!: string;
  criteria!: string[];
  searchText!: string;
  phonePattern = "\\+{1}7{1}\\d{10}";

  constructor(houseService: HouseService, tenantService: TenantService) {
    this.houseService = houseService;
    this.tenantService = tenantService;
  }

  ngOnInit(): void {
    this.loadTenants();
    this.loadHouses();
    this.criteria = ['по телефону', 'по ФИО'];
    this.isHouseChanged = false;
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
      (items) => this.houses = items,
      (error) => console.error(error)
    );
  }

  public findTenants(input: string): void {
    if (this.searchText != "") {
      switch (this.searchCriteria) {
        case 'по телефону':
          this.tenantService.getTenantsByTelNum(input).subscribe(
            (items) => this.tenants = items,
            (error) => console.error(error)
          );
          break;
        case 'по ФИО':
          this.tenantService.getTenantsByFio(input).subscribe(
            (items) => this.tenants = items,
            (error) => console.error(error)
          );
          break;
      }
    }
    else this.loadTenants();
  }

  public onCreate(): void {
    this.isUpdate = false;
    this.selectedTenant = new Tenant();
    if (this.selectedHouse.houseId == null){
      this.isHouseChanged = false;
    }
    this.selectedHouse = null;
  }

  public onEdit(tenant: Tenant): void {
    this.isUpdate = true;
    this.selectedTenant = JSON.parse(JSON.stringify(tenant));
    this.selectedHouse = this.selectedTenant.house;
  }

  public addTenant(): void {
    this.selectedTenant.house = this.selectedHouse;
    this.tenantService.addTenant(this.selectedTenant).subscribe(
      () => this.loadTenants(),
      (error) => console.error(error)
    );
  }

  public deleteTenant(id: number): void {
    this.tenantService.deleteTenant(id).subscribe(
      () => this.loadTenants(),
      (error) => console.error(error)
    );
  }

  public updateTenant(): void {
    this.selectedTenant.house = this.selectedHouse;
    this.tenantService.updateTenant(this.selectedTenant.tenantId, this.selectedTenant).subscribe(
      () => this.loadTenants(),
      (error) => console.error(error)
    );
  }

  public onChangeSelectCriteria(selectedItem: string) {
    this.searchCriteria = selectedItem;
    if (this.searchText != "") {
      this.findTenants(this.searchText)
    }
  }

  public onChangeSelectHouse(selectedItem: any) {
    this.selectedHouse = new House(selectedItem.houseId, selectedItem.numberOfApartments, selectedItem.numberOfFloors,
      selectedItem.numberOfEntraces, selectedItem.district);
    this.isHouseChanged = true;
  }
}
