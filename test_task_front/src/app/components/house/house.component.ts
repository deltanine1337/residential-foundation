import {Component, OnInit} from '@angular/core';
import {HouseService} from "../../services/house.service";
import {House} from "../../model/house";
import {DistrictService} from "../../services/district.service";
import {District} from "../../model/district";
import {SEARCH_HOUSE_CRITERIAS} from "../../consts/search.const";
import {ISearch} from "../../model/search";
import {ESearchHouseCriteria} from "../../model/enums/search.enum";

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.scss'],
  providers: [DistrictService, HouseService]
})
export class HouseComponent implements OnInit {
  houses: House[] = [];
  districts: District[] = [];
  selectedHouse = new House(null, 0, 0, 0, new District(0, ''));
  isUpdate: boolean;
  streeet: string;
  houseeNumber: number;
  searchCriteria: ESearchHouseCriteria;
  criteria: ISearch[];
  selectedDistrict = new District(0, '');
  searchText: string;
  isDistrictChanged: boolean;



  constructor(private houseService: HouseService, private districtService: DistrictService) {
  }

  ngOnInit(): void {
    this.loadHouses();
    this.loadDistricts();
    this.criteria = SEARCH_HOUSE_CRITERIAS;
    this.isDistrictChanged = false;
  }

  public loadDistricts(): void {
    this.districtService.getDistricts().subscribe(
      (items) => this.districts = items,
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
    this.isUpdate = false;
    this.selectedHouse = new House(null, null, null, null, new District(0, ''));
    if (this.selectedHouse.district.districtId == 0) {
      this.isDistrictChanged = false;
    }
    this.streeet = "";
    this.houseeNumber = null;
    this.selectedDistrict = null;

  }


  public onEdit(house: House): void {
    this.isUpdate = true;
    this.selectedHouse = JSON.parse(JSON.stringify(house));
    this.streeet = this.selectedHouse.houseId.street;
    this.houseeNumber = this.selectedHouse.houseId.houseNumber;
    this.selectedDistrict = this.selectedHouse.district;
    this.isDistrictChanged = true;
  }

  public addHouse(): void {
    this.selectedHouse.houseId = {
      'street': this.streeet,
      'houseNumber': this.houseeNumber
    };
    this.selectedHouse.district = this.selectedDistrict;
    this.houseService.addHouse(this.selectedHouse).subscribe(
      () => this.loadHouses(),
      (error) => console.error(error)
    );
  }

  public deleteHouse(street: string, houseNumber: number): void {
    this.houseService.deleteHouse(street, houseNumber).subscribe(
      () => this.loadHouses(),
      (error) => console.error(error)
    );
  }

  public updateHouse(): void {
    this.selectedHouse.district = this.selectedDistrict;
    this.houseService.updateHouse(this.streeet, this.houseeNumber, this.selectedHouse).subscribe(
      () => this.loadHouses(),
      (error) => console.error(error)
    );
  }


  public onChangeSelectCriteria(selectedItem: string) {
    if (this.searchText != "") {
      this.findHouses(this.searchText)
    }
  }

  public onChangeSelectDistrict(selectedItem: any) {
    this.selectedDistrict = new District(selectedItem.districtId, selectedItem.districtName);
    this.isDistrictChanged = true;
  }
}
