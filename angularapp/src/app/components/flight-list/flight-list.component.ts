
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Flight } from 'src/app/models/flight.model';
import { AuthService } from 'src/app/services/auth.service';
import { FlightService } from 'src/app/services/flight.service';



@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {

  flights: Flight[] = [];
  errorMessage: string;
  userRole: string;
  filteredFlights: Flight[] = [];
  fromLocation: string = '';
  toLocation: string = '';
  startDate: string = '';
  endDate: string = '';
  minPrice: number | null = null;
  maxPrice: number | null = null;

  ngOnInit(): void {
    this.loadFlights()
    this.userRole = this.getUserRole();

  }
  constructor(private flightService: FlightService, private router: Router, private authService: AuthService) { }
  loadFlights() {
    this.flightService.getFlights().subscribe(
      data => {
        this.flights = data;
        this.filteredFlights = data;
      },
      (error) => {
        window.alert("No Flights are available")
      });
  }

  getUserRole() {
    return this.authService.getRole();
  }

  updateFlight(id: number): void {
    this.router.navigate(['/add-flight', id])
  }
  deleteFlight(id: number): void {
    this.flightService.deleteFlight(id).subscribe()
    this.loadFlights()
  }
  navigateToBooking(flightId: number) {
    this.router.navigate(['/book-form', flightId])
  }

  applyFilters(): void {
    this.filteredFlights = this.flights.filter(flight => {
      const matchesFrom = this.fromLocation
        ? flight.departureLocation.toLowerCase().includes(this.fromLocation.toLowerCase())
        : true;
  
      const matchesTo = this.toLocation
        ? flight.arrivalLocation.toLowerCase().includes(this.toLocation.toLowerCase())
        : true;
  
      const matchesDate = this.startDate && this.endDate
        ? new Date(flight.arrivalTime) >= new Date(this.startDate) &&
          new Date(flight.arrivalTime) <= new Date(this.endDate)
        : true;
  
      const matchesPrice =
        (this.minPrice !== null ? flight.price >= this.minPrice : true) &&
        (this.maxPrice !== null ? flight.price <= this.maxPrice : true);
  
      return matchesFrom && matchesTo && matchesDate && matchesPrice;
    });
  }


  bookFlight(flightId: number) {
    this.router.navigate(['/book-form'], { queryParams: { flightId } });
  }

  getAirlineLogo(airline: string): string | null {
    const logos: { [key: string]: string } = {
      'IndiGo': 'https://upload.wikimedia.org/wikipedia/commons/6/69/IndiGo_Airlines_logo.svg',
      'Air India': 'https://upload.wikimedia.org/wikipedia/commons/b/bf/Air_India_2023.svg',
      'Akasa Air': 'https://upload.wikimedia.org/wikipedia/commons/6/69/Akasa_Air_logo.svg',
      'Spice Jet':'https://upload.wikimedia.org/wikipedia/en/9/9c/SpiceJet_logo.svg',
      'China Southern' : 'https://upload.wikimedia.org/wikipedia/en/b/b4/China_Southern_Airlines_logo.svg',
      'Singapore Airlines' : 'https://upload.wikimedia.org/wikipedia/en/6/6b/Singapore_Airlines_Logo_2.svg',
      'Cebu Pacific': 'https://upload.wikimedia.org/wikipedia/en/c/cd/Cebu_Pacific_logo.svg',
      'All Nippon Airways':'https://upload.wikimedia.org/wikipedia/commons/8/8d/All_Nippon_Airways_Logo.svg',
      'Qatar Airways' : 'https://upload.wikimedia.org/wikipedia/en/9/9b/Qatar_Airways_Logo.svg',
      'Cathhay Pacific' : 'https://upload.wikimedia.org/wikipedia/en/1/17/Cathay_Pacific_logo.svg'

  
    };
    return logos[airline] || null;
  }

}

