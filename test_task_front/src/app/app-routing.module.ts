import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DistrictComponent} from "./components/district/district-list/district.component";
import {HouseComponent} from "./components/house/house-list/house.component";
import {TenantComponent} from "./components/tenant/tenant-list/tenant.component";
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";

const routes: Routes = [
  { path: 'district', component: DistrictComponent },
  { path: 'house', component: HouseComponent },
  { path: 'tenant', component: TenantComponent },
  { path: "login", component: LoginComponent},
  { path: "register", component: RegisterComponent},
  { path: "**", redirectTo: 'login'}
];

@NgModule({
  imports: [
    RouterModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
