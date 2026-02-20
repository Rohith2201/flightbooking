import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ErrorComponent } from './components/error/error.component';
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { ManageBookingComponent } from './components/manage-booking/manage-booking.component';
import { MyHistoryComponent } from './components/my-history/my-history.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { HttpIntercepterAuthService } from './services/http-intercepter-auth.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ErrorComponent,
    FlightListComponent,
    ManageBookingComponent,
    MyHistoryComponent,
    AddFlightComponent,
    BookingFormComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [{
    provide:HTTP_INTERCEPTORS,
    useClass:HttpIntercepterAuthService,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }