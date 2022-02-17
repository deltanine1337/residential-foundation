import {Component, OnInit, ViewChild} from '@angular/core';
import {HouseService} from "../../services/house.service";
import {House} from "../../model/house";
import {DistrictService} from "../../services/district.service";
import {District} from "../../model/district";
import {SEARCH_HOUSE_CRITERIAS} from "../../consts/search.const";
import {ISearch} from "../../model/search";
import {ESearchHouseCriteria} from "../../model/enums/search.enum";
import {HouseModalComponent} from "../house-modal/house-modal.component";
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.scss'],
  providers: [DistrictService, HouseService]
})
export class HouseComponent implements OnInit {
  @ViewChild(HouseModalComponent)
  houseModalComponent: HouseModalComponent;
  houses: House[] = [];
  searchCriteria: ESearchHouseCriteria;
  criteria: ISearch[];
  searchText: string;
  isAdmin = AppComponent.isAdmin;

  constructor(private houseService: HouseService, private districtService: DistrictService) {
  }

  ngOnInit(): void {
    this.loadHouses();
    this.loadDistricts();
    this.criteria = SEARCH_HOUSE_CRITERIAS;
    this.houseModalComponent.isDistrictChanged = false;
  }

  public loadDistricts(): void {
    this.districtService.getDistricts().subscribe(
      (items) => this.houseModalComponent.districts = items,
      (error) => console.error(error)
    );
  }

  public loadHouses(): void {
    this.houseService.getHouses().subscribe(
      (items) => this.houses = items,
      (error) => console.error(error)
    );
    this.searchText = "";
  }

  public findHouses(input: string): void {
    if (this.searchText != "") {
      switch (this.searchCriteria) {
        case ESearchHouseCriteria.District:
          this.houseService.getHouses("", input).subscribe(
            (items) => this.houses = items,
            (error) => console.error(error)
          );
          break;
        case ESearchHouseCriteria.Street:
          this.houseService.getHouses(input, "").subscribe(
            (items) => this.houses = items,
            (error) => console.error(error)
          );
          break;
      }
    }
    else this.loadHouses();
  }

  public onCreate(): void {
    this.houseModalComponent.isUpdate = false;
    this.houseModalComponent.selectedHouse = new House(null, null, null, null, new District(0, ''));
    if (this.houseModalComponent.selectedHouse.district.districtId == 0) {
      this.houseModalComponent.isDistrictChanged = false;
    }
    this.houseModalComponent.streeet = "";
    this.houseModalComponent.houseeNumber = null;
    this.houseModalComponent.selectedDistrict = null;
  }

  public onEdit(house: House): void {
    this.houseModalComponent.isUpdate = true;
    this.houseModalComponent.selectedHouse = JSON.parse(JSON.stringify(house));
    this.houseModalComponent.streeet = this.houseModalComponent.selectedHouse.houseId.street;
    this.houseModalComponent.houseeNumber = this.houseModalComponent.selectedHouse.houseId.houseNumber;
    this.houseModalComponent.selectedDistrict = this.houseModalComponent.selectedHouse.district;
    this.houseModalComponent.isDistrictChanged = true;
  }

  public deleteHouse(street: string, houseNumber: number): void {
    this.houseService.deleteHouse(street, houseNumber).subscribe(
      () => this.loadHouses(),
      (error) => console.error(error)
    );
  }

  public onChangeSelectCriteria(selectedItem: string) {
    if (this.searchText != "") {
      this.findHouses(this.searchText)
    }
  }
}
