import { Injectable } from '@angular/core';
import { Booking } from '../models/booking.model';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { APP_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class BookingService {


  constructor(private http: HttpClient) { }


  getBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${APP_URL}/bookings`);
  }
  getBookingByUserId(userId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${APP_URL}/bookings/user/${userId}`);
  }
  getBookingById(bookingId: number): Observable<Booking> {
    return this.http.get<Booking>(`${APP_URL}/bookings/${bookingId}`);
  }
  deleteBooking(bookingId: number): Observable<void> {
    return this.http.delete<void>(`${APP_URL}/bookings/${bookingId}`);
  }
  addBooking(booking: any): Observable<Booking> {
    return this.http.post<Booking>(APP_URL + "/bookings", booking);
  }
  updateBooking(booking: Booking): Observable<Booking> {
    return this.http.put<Booking>(`${APP_URL}/bookings/${booking.bookingId}`, booking);
  }

  getAllBookings(): Observable<any> {
    return this.http.get(`${APP_URL}/bookings/all`);
  }

  updateBookingStatus(id:number,status:string): Observable<any> {
    return this.http.put(`${APP_URL}/bookings/${id}/${status}`,null);
  }


}