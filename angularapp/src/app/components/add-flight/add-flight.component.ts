import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from 'src/app/models/flight.model';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css']
})
export class AddFlightComponent implements OnInit {

  flight: Flight = {
    flightNumber: '',
    airline: '',
    departureLocation: '',
    arrivalLocation: '',
    departureTime: '',
    arrivalTime: '',
    price: 0,
    totalSeats : 0
  };

  flightId: number = 0;
  flightForm: FormGroup;
  isEditing: boolean = false;
  constructor(private router: Router, private route: ActivatedRoute, private service: FlightService, private fb: FormBuilder) {
    this.flightForm = this.fb.group({
      flightNumber: ['', Validators.required],
      airline: ['', Validators.required],
      departureLocation: ['', Validators.required],
      arrivalLocation: ['', Validators.required],
      departureTime: ['', Validators.required],
      arrivalTime: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(0)]],
      totalSeats: [null, [Validators.required, Validators.min(0)]]
    }, {
      validators: [this.departureArrivalValidator, this.departureArrivalTimeValidator]
    });
  }
  departureArrivalValidator(group: AbstractControl): { [key: string]: any } | null {
    const departure = group.get('departureLocation')?.value;
    const arrival = group.get('arrivalLocation')?.value;
    return departure && arrival && departure.toLowerCase() === arrival.toLowerCase()
      ? { sameLocation: true }
      : null;
  }

  departureArrivalTimeValidator(group: AbstractControl): ValidationErrors | null {
    const departureTime = new Date(group.get('departureTime')?.value);
    const arrivalTime = new Date(group.get('arrivalTime')?.value);
    const now = new Date();
  
    if (departureTime < now || arrivalTime < now) {
      return { pastTime: true };
    }
  
    if (arrivalTime <= departureTime) {
      return { invalidSequence: true };
    }
  
    return null;
  }

  addNewFlight() {
    this.flight = this.flightForm.value;
    if (!this.isEditing) {
      this.service.addFlight(this.flight).subscribe(data => {
        alert("Flight added successfully!")
        this.router.navigate(['/flight-list']);
      })
    }
    else {
      this.service.updateFlight(this.flightId, this.flight).subscribe(data => {
        this.isEditing = false;
        this.router.navigate(['/flight-list']);
      })
      this.flightForm.reset();
    }
  }

  closeModal() {
    alert("Flight added successfully!")
    this.router.navigate(['/flight-list'])
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.flightId = params['id']
      this.isEditing = !!this.flightId
      this.service.getFlightById(this.flightId).subscribe((data) => {
        this.flightForm.patchValue(data)
      })
    })
  }

  get f() { return this.flightForm.controls; }

}
