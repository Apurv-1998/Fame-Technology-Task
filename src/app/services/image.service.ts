import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ImageRest } from 'src/app/common/image-rest';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  baseUrl: string = "http://localhost:8080/fullstack-task/api";

  constructor(private httpClient: HttpClient) { }



  retrieveImage(imageId: string): Observable<any> {

    const searchUrl = `${this.baseUrl}/images/retrieveImage/${imageId}`;

    return this.httpClient.get<ImageRest>(searchUrl);

  }

  uploadImageFile(file: FormData,userId: string): Observable<any> {

    const searchUrl = `${this.baseUrl}/images/${userId}/upload`;

    return this.httpClient.post(searchUrl,file);

  }

  deleteImage(imageId: string,userId: string): Observable<any> {

    const searchUrl = `${this.baseUrl}/images/${userId}/${imageId}/deleteImage`;

    return this.httpClient.delete(searchUrl);

  }
}
