import { Component, OnInit } from '@angular/core';
import {HouseService} from "../../services/house.service";
import {House} from "../../model/house";
import {DistrictService} from "../../services/district.service";
import {District} from "../../model/district";

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrls: ['./house.component.scss'],
  providers: [DistrictService, HouseService]
})
export class HouseComponent implements OnInit {

  private houseService: HouseService;
  private districtService: DistrictService;

  houses: House[] = [];
  districts: District[] = [];
  selectedHouse = new House(null, 0, 0, 0, new District(0, ''));
  isUpdate: boolean;
  streeet: string;
  houseeNumber: number;
  searchCriteria: string;
  criteria: string[];
  selectedDistrict = new District(0, '');
  searchText: string;
  isDistrictChanged: boolean;


  constructor(houseService: HouseService, districtService: DistrictService) {
    this.houseService = houseService;
    this.districtService = districtService;
  }

  ngOnInit(): void {
    this.loadHouses();
    this.loadDistricts();
    this.criteria = ['по району', 'по улице'];
    this.isDistrictChanged = false;
  }

  public loadDistricts(): void{
    this.districtService.getDistricts().subscribe(
      (items) => this.districts = items,
      (error) => console.error(error)
    );
  }

  public loadHouses(): void{
    this.houseService.getHouses().subscribe(
      (items) => this.houses = items,
      (error) => console.error(error)
    );
    this.searchText = "";
  }

  public findHouses(input: string): void{
    if (this.searchText != "") {
      switch (this.searchCriteria) {
        case 'по району':
          this.houseService.getHousesByDistrict(input).subscribe(
            (items) => this.houses = items,
            (error) => console.error(error)
          );
          break;
        case 'по улице':
          this.houseService.getHousesByStreet(input).subscribe(
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
    this.selectedHouse = new House(null, 0, 0, 0, new District(0, ''));
    if (this.selectedHouse.district.districtId == 0) {
      this.isDistrictChanged = false;
    }
    this.streeet = "";
    this.houseeNumber = 0;
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
      'street' : this.streeet,
      'houseNumber' : this.houseeNumber
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
    this.searchCriteria = selectedItem;
    if (this.searchText != "") {
      this.findHouses(this.searchText)
    }
  }

  public onChangeSelectDistrict(selectedItem: any) {
    this.selectedDistrict = new District(selectedItem.districtId, selectedItem.districtName);
    this.isDistrictChanged = true;
  }
}
