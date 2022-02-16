import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DistrictComponent } from './components/district/district.component';
import { HouseComponent } from './components/house/house.component';
import { TenantComponent } from './components/tenant/tenant.component';
import { HttpClientModule } from "@angular/common/http";
import { DistrictModalComponent } from './components/district-modal/district-modal.component';
import { HouseModalComponent } from './components/house-modal/house-modal.component';
import { TenantModalComponent } from './components/tenant-modal/tenant-modal.component';



@NgModule({
  declarations: [
    AppComponent,
    DistrictComponent,
    HouseComponent,
    TenantComponent,
    DistrictModalComponent,
    HouseModalComponent,
    TenantModalComponent
  ],
  imports: [
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserModule,
    NgSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
