import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DistrictComponent} from "./components/district/district.component";
import {HouseComponent} from "./components/house/house.component";
import {TenantComponent} from "./components/tenant/tenant.component";

const routes: Routes = [
  { path: 'district', component: DistrictComponent },
  { path: 'house', component: HouseComponent },
  { path: 'tenant', component: TenantComponent },
  { path: '**', component: DistrictComponent }
];

@NgModule({
  imports: [
    RouterModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
