import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm:FormGroup
  users:User[] = [];
  constructor(private fb:FormBuilder,private service:AuthService,private router:Router){
    this.registerForm = this.fb.group({
      username:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required],
      confirmPassword:['',Validators.required],
      mobileNumber:['',Validators.required],
      userRole:['',Validators.required],
    })
  }
  onSubmit(){
    if(this.registerForm.valid){
      this.service.register(this.registerForm.value).subscribe((result)=>{
        this.registerForm.reset()
        alert("Registration successful")
        this.router.navigate(['/login'])
      },
      (error)=>{
        this.registerForm.reset()
        alert("Registration not done")
        this.router.navigate(['/error'],{queryParams:{errorMsg:'Registration not done due to exising emailId'}} )
      });

    }
  }
  ngOnInit(): void {
  }

}
