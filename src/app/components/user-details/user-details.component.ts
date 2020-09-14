import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserRest } from 'src/app/common/user-rest';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  response: UserRest;
  hasUserId: boolean;
  userId: string;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private userService: UserService) { }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(
      () => this.showDetails()
    );

  }

  showDetails() {

    this.hasUserId = this.activatedRoute.snapshot.paramMap.has('userId');

    if(this.hasUserId)
      this.showUserDetails();
    else
      this.router.navigateByUrl('');

  }


  showUserDetails(){

    this.userId = this.activatedRoute.snapshot.paramMap.get('userId');

    this.userService.getUserDetails(this.userId).subscribe(
      data => {
     //   console.log("Data Received -> "+JSON.stringify(data));
        this.response = data;
      }
    );

  }

}
