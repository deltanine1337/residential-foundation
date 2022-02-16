import {Component, OnInit, ViewChild} from '@angular/core';
import {DistrictService} from "../../services/district.service";
import {District} from "../../model/district";
import {Router} from "@angular/router";
import {DistrictModalComponent} from "../district-modal/district-modal.component";

@Component({
  selector: 'app-district',
  templateUrl: './district.component.html',
  styleUrls: ['./district.component.scss'],
  providers: [DistrictService]
})
export class DistrictComponent implements OnInit {
  @ViewChild(DistrictModalComponent)
  districtModalComponent: DistrictModalComponent;
  districts: District[] = [];

  constructor(private districtService: DistrictService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadDistricts();
  }

  public loadDistricts(): void {
    this.districtService.getDistricts().subscribe(
      (items) => this.districts = items,
      (error) => console.error(error)
    );
  }

  public onCreate(): void {
    this.districtModalComponent.isUpdate = false;
    this.districtModalComponent.selectedDistrict = new District(0, '');
  }

  public onEdit(district: District): void {
    this.districtModalComponent.isUpdate = true;
    this.districtModalComponent.selectedDistrict = JSON.parse(JSON.stringify(district));
  }

  public deleteDistrict(id: number): void {
    this.districtService.deleteDistrict(id).subscribe(
      () => this.loadDistricts(),
      (error) => console.error(error)
    );
  }
}
