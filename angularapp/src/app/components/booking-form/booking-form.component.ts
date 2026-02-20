import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from 'src/app/models/booking.model';
import { Flight } from 'src/app/models/flight.model';
import { FlightService } from 'src/app/services/flight.service';
import { BookingService } from 'src/app/services/booking.service';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {

  flightDetails : Flight;
 
  booking: Booking = {
    flightId: null,
    userId: null,
    bookingDate: null,
    numberOfPassengers: null,
    status: 'PENDING'
  };
 
  constructor(private route: ActivatedRoute, private bs: BookingService, private as : AuthService,private router:Router, private fes: FlightService) {
    this.route.params.subscribe((params) => {
      this.booking.flightId = params['id'];
      this.fes.getFlightById(this.booking.flightId).subscribe(data => this.flightDetails = data);
  
    
    });
 
    this.booking.userId = this.as.getAuthenticatedUserId();
  }
 
  ngOnInit(): void {
  }
 
  addBooking(){
    this.bs.addBooking(this.booking).subscribe((data)=>{
      this.booking = data;
      console.log(data);
      this.router.navigate(["/my-history"])
     
    });
  }
  cancelBooking(){
    this.router.navigate(['/flight-list'])
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
 
