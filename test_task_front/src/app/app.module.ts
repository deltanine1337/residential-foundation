import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DistrictComponent } from './components/district/district.component';
import { HouseComponent } from './components/house/house.component';
import { TenantComponent } from './components/tenant/tenant.component';

@NgModule({
  declarations: [
    AppComponent,
    DistrictComponent,
    HouseComponent,
    TenantComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
