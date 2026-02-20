import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking.model';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-manage-booking',
  templateUrl: './manage-booking.component.html',
  styleUrls: ['./manage-booking.component.css']
})
export class ManageBookingComponent implements OnInit {

  bookings: Booking[] = [];
  booking: Booking;
  searchTerm: string = '';
  statusFilter: string = '';

  constructor(private bookingService: BookingService) { }
  loadBookings() {
    this.bookingService.getBookings().subscribe((data) => {
      this.bookings = data.sort((a, b) => a.status === 'PENDING' ? -1 : 1);
    });
  }

  confirmBooking(id: number) {
    console.log(id);
    this.bookingService.getBookingById(id).subscribe(data => {
        this.bookingService.updateBookingStatus(id, 'CONFIRMED').subscribe(data => {
          this.loadBookings();
        });
      }
    );
  }

  cancelBooking(id: number) {
    console.log(id);
    this.bookingService.getBookingById(id).subscribe(
      data => {
        this.bookingService.updateBookingStatus(id, 'CANCELLED').subscribe(data => {
          this.loadBookings();
        })
      }
    );
  }
  ngOnInit(): void {
    this.loadBookings()
  }
  filteredBookings(): Booking[] {
    return this.bookings.filter(booking => {
      const matchesSearch = booking.bookingId.toString().includes(this.searchTerm) ||
                            booking.userId.toString().includes(this.searchTerm);
      const matchesStatus = this.statusFilter ? booking.status === this.statusFilter : true;
      return matchesSearch && matchesStatus;
    });
  }

}

