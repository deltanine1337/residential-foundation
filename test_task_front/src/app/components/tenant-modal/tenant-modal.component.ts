import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {HouseService} from "../../services/house.service";
import {TenantService} from "../../services/tenant.service";
import {House} from "../../model/house";
import {Tenant} from "../../model/tenant";
import {District} from "../../model/district";

@Component({
  selector: 'app-tenant-modal',
  templateUrl: './tenant-modal.component.html',
  styleUrls: ['./tenant-modal.component.scss']
})
export class TenantModalComponent implements OnInit {
  @Output()
  emitLoadTenants: EventEmitter<any> = new EventEmitter<any>();
  houses: House[] = [];
  selectedTenant = new Tenant();
  selectedHouse = new House(null, 0, 0, 0, new District(0, ''));
  isUpdate: boolean;
  isHouseChanged: boolean;
  phonePattern = "\\+{1}7{1}\\d{10}";

  constructor(private tenantService: TenantService) { }

  ngOnInit(): void {
  }

  public addTenant(): void {
    this.selectedTenant.houseDto = this.selectedHouse;
    this.tenantService.addTenant(this.selectedTenant).subscribe(
      () => this.emitLoadTenants.emit(),
      (error) => console.error(error)
    );
  }

  public updateTenant(): void {
    this.selectedTenant.houseDto = this.selectedHouse;
    this.tenantService.updateTenant(this.selectedTenant.tenantId, this.selectedTenant).subscribe(
      () => this.emitLoadTenants.emit(),
      (error) => console.error(error)
    );
  }


  public onChangeSelectHouse(selectedItem: any) {
    this.selectedHouse = new House(selectedItem.houseId, selectedItem.numberOfApartments, selectedItem.numberOfFloors,
      selectedItem.numberOfEntraces, selectedItem.district);
    this.isHouseChanged = true;
  }
}
