import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { ManageBookingComponent } from './components/manage-booking/manage-booking.component';
import { MyHistoryComponent } from './components/my-history/my-history.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BookingFormComponent } from './components/booking-form/booking-form.component';
import { ErrorComponent } from './components/error/error.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'add-flight/:id', component: AddFlightComponent },
  { path: 'add-flight', component: AddFlightComponent },
  { path: 'flight-list', component: FlightListComponent },
  { path: 'booking', component: ManageBookingComponent },
  { path: 'book-form', component: BookingFormComponent },
  { path: 'book-form/:id', component: BookingFormComponent },
  { path: 'my-history', component: MyHistoryComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'navbar', component: NavbarComponent },
  {path:'error',component:ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }