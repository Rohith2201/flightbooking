import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking.model';
import { AuthService } from 'src/app/services/auth.service';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-my-history',
  templateUrl: './my-history.component.html',
  styleUrls: ['./my-history.component.css']
})
export class MyHistoryComponent implements OnInit {

  bookingHistory: Booking[] = [];

  constructor(private bookingService: BookingService, private as : AuthService) { }
  loadBookings() {
    this.bookingService.getBookingByUserId(this.as.getAuthenticatedUserId()).subscribe((data) => {
      console.log(this.bookingHistory);
      this.bookingHistory = data;
    });
  }

  ngOnInit(): void {
    this.loadBookings()
  }

}
