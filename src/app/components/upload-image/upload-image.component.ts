import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ImageRest } from 'src/app/common/image-rest';
import { ImageService } from 'src/app/services/image.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {

  selectedFile: File;

  response: ImageRest;

  constructor(private activatedRoute: ActivatedRoute,
              private router:Router,
              private imageService: ImageService) { }

  ngOnInit(): void {
  }

  onFileChange(event: { target: { files: File[]; }; }) {
    this.selectedFile = event.target.files[0];
  }

  upload() {

    console.log(this.selectedFile);

    const userId = this.activatedRoute.snapshot.paramMap.get('userId');

    const uploadImageFile = new FormData();
    uploadImageFile.append('file',this.selectedFile,this.selectedFile.name);

    this.imageService.uploadImageFile(uploadImageFile,userId).subscribe(
      data => {
       // console.log("Data Received -> "+JSON.stringify(data));
        this.response = data;

        this.router.navigate(['/showUserDetails/'+userId]);

      }
    );

  }

}
