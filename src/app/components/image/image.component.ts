import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ImageRest } from 'src/app/common/image-rest';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  @Input() receivedImageId: string;
  @Input() receivedUserId: string;

  imageId: string;
  userId: string;

  response: ImageRest;
  base64Data: any;
  retrievedImage: any;

  deleteResponse: boolean;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private imageService: ImageService) { }

  ngOnInit(): void {
    this.imageId = this.receivedImageId;
    this.userId = this.receivedUserId;

    this.imageService.retrieveImage(this.imageId).subscribe(
      data => {
     //   console.log('Display Data -> '+JSON.stringify(data));
        this.response = data;
        this.base64Data = this.response.picByte;
        this.retrievedImage = 'data:image/jpeg;base64,'+this.base64Data;
      }
    );

  }


  updateImage(userId: string) {

    this.router.navigate(['/uploadImage/'+userId]);

  }

  deleteImage(imageId: string,userId: string){

    this.imageService.deleteImage(imageId,userId).subscribe(
      data => {
        this.deleteResponse = data;
        console.log('Data -> '+JSON.stringify(data));
        this.router.navigateByUrl('/showUserDetails/'+userId);
      }
    );

  }




}
