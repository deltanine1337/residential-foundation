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



@NgModule({
  declarations: [
    AppComponent,
    DistrictComponent,
    HouseComponent,
    TenantComponent
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
