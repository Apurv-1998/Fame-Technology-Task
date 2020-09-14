import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isVerified: string;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signup() {
    this.router.navigate(['/signup']);
  }

  login() {
    this.router.navigate(['/login/'+1]);
  }

  checkLocalStorage(): boolean{
    this.isVerified = localStorage.getItem("VERIFIED_USER");

    return this.isVerified === "true";
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

}
