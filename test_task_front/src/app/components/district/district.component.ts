import { Component, OnInit } from '@angular/core';
import {DistrictService} from "../../services/district.service";
import {District} from "../../model/district";
import {Router} from "@angular/router";

@Component({
  selector: 'app-district',
  templateUrl: './district.component.html',
  styleUrls: ['./district.component.scss'],
  providers: [DistrictService]
})
export class DistrictComponent implements OnInit {

  private districtService: DistrictService;
  private router: Router;

  districts!: District[];
  selectedDistrict = new District(0, '');
  isUpdate!: boolean;

  constructor(districtService: DistrictService, router: Router) {
    this.districtService = districtService;
    this.router = router;
  }

  ngOnInit(): void {
    this.loadDistricts();
  }

  public loadDistricts(): void{
    this.districtService.getDistricts().subscribe(
      (items) => this.districts = items,
      (error) => console.error(error)
    );
  }

  public onCreate(): void {
    this.isUpdate = false;
    this.selectedDistrict = new District(0, '');
  }

  public onEdit(district: District): void {
    this.isUpdate = true;
    this.selectedDistrict = JSON.parse(JSON.stringify(district));
  }

  public addDistrict(): void {
    this.districtService.addDistrict(this.selectedDistrict).subscribe(
      () => this.loadDistricts(),
      (error) => console.error(error)
    );
  }

  public deleteDistrict(id: number): void {
    this.districtService.deleteDistrict(id).subscribe(
      () => this.loadDistricts(),
      (error) => console.error(error)
    );
  }

  public updateDistrict(): void {
    this.districtService.updateDistrict(this.selectedDistrict.districtId, this.selectedDistrict).subscribe(
      () => this.loadDistricts(),
      (error) => console.error(error)
    );
  }

}
