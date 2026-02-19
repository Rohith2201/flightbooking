import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { ErrorComponent } from './components/error/error.component';
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ManageBookingComponent } from './components/manage-booking/manage-booking.component';
import { MyHistoryComponent } from './components/my-history/my-history.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    AddFlightComponent,
    BookingFormComponent,
    ErrorComponent,
    FlightListComponent,
    HomeComponent,
    LoginComponent,
    ManageBookingComponent,
    MyHistoryComponent,
    NavbarComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
