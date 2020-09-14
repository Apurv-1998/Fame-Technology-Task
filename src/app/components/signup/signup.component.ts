import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { SignupPayload } from 'src/app/common/signup-payload';
import { UserRest } from 'src/app/common/user-rest';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  userId: string;
  payload: SignupPayload;
  response: UserRest;
  isVerified: boolean;


  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private toaster: ToastrService) { 


    this.payload = {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      dob: null
    };

  }

  ngOnInit(): void {

    this.signupForm = this.formBuilder.group({
      firstName: ['',Validators.required],
      lastName: ['',Validators.required],
      email: ['',[Validators.required,Validators.email]],
      password: ['',Validators.required],
      dob: ['',Validators.required]
    });


  }

  onSubmit() {

    console.log("Signup Form Data -> "+this.signupForm);

    this.payload.firstName = this.signupForm.get('firstName').value;
    this.payload.lastName = this.signupForm.get('lastName').value;
    this.payload.email = this.signupForm.get('email').value;
    this.payload.password = this.signupForm.get('password').value;
    this.payload.dob = this.signupForm.get('dob').value;


    this.userService.signupUser(this.payload).subscribe(
      data => {
        console.log("Data Received -> ", JSON.stringify(data));
        this.response = data;
        this.userId = this.response.userId;
        this.isVerified = this.response.isVerified;

        if(!this.isVerified){
          this.router.navigate(['/login/'+0]);
          this.toaster.success("Signup Successful");
        }
        else{
          this.toaster.error("User Already SignedUp");
        }
      },
      error => {
        this.toaster.error("Cannot Signup The User");
        throw(error);
      }
    );



  }

  discard() {
    this.router.navigateByUrl('');
  }

}
