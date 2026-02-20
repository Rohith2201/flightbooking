
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Flight } from '../models/flight.model';
import { Observable, of } from 'rxjs';
import { APP_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }

  getFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${APP_URL}/flights`);
  }
  getFlightById(flightId: number): Observable<Flight> {
    return this.http.get<Flight>(`${APP_URL}/flights/${flightId}`);
  }
  addFlight(flight: Flight): Observable<Flight> {
    return this.http.post<Flight>(APP_URL + "/flights", flight);
  }
  updateFlight(flightId: any, flight: Flight): Observable<Flight> {
    return this.http.put<Flight>(APP_URL + "/flights/" + Number(flightId), flight)
  }
  deleteFlight(flightId: number): Observable<void> {
    return this.http.delete<void>(`${APP_URL}/flights/${flightId}`)
  }

}
