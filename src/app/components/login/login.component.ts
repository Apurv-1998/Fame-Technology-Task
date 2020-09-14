import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserRest } from 'src/app/common/user-rest';
import { LoginPayload } from 'src/app/common/login-payload';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  isVerified: boolean;
  verificationCode: number;
  userId: string;
  payload: LoginPayload;
  response: UserRest;
  hasVerificationCode: boolean;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private toaster: ToastrService,
              private userService: UserService) { 


    this.payload = {

      email: '',
      password: ''

    };


  }

  ngOnInit(): void {

    this.loginForm = this.formBuilder.group({
      email: ['',[Validators.required,Validators.email]],
      password: ['',Validators.required]
    });

    //Checking the url
    this.hasVerificationCode = this.activatedRoute.snapshot.paramMap.has('isVerified');

    if(this.hasVerificationCode){
      this.verificationCode = +this.activatedRoute.snapshot.paramMap.get('isVerified');

      console.log(this.verificationCode);

      if(this.verificationCode == 0)
        this.isVerified = false;
      else
        this.isVerified = true;
    }


  }

  onSubmit() {

      if(this.isVerified){
        this.isVerified = true;
        console.log("Login Form -> "+this.loginForm);
        
        this.payload.email = this.loginForm.get('email').value;
        this.payload.password = this.loginForm.get('password').value;

        this.userService.loginUser(this.payload).subscribe(
          data => {
            console.log("Data Received -> "+JSON.stringify(data));
            this.response = data;
            this.userId = this.response.userId;
            this.toaster.success("Login Successful");
            this.router.navigate(['/showUserDetails/'+this.userId]);
            localStorage.setItem("VERIFIED_USER",this.isVerified.toString());
          },
          error => {
            this.toaster.error("Invalid Credentials");
            throw(error);
          }
        );
    }

  }

}


