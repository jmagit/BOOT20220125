import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {
  constructor(private http: HttpClient) { }
  upload(url: string, file: File): Observable<any> {
    const formData = new FormData();
    formData.append("filetoupload", file, file.name);
    return this.http.post(url, formData)
  }
}

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  fileURL: string = "";
  loading: boolean = false;
  file: File | null = null;
  blob: any = null;

  constructor(private fileUploadService: FileUploadService, private http: HttpClient, private sanitizer: DomSanitizer) { }

  onChange(event: any) {
    this.file = event.target.files[0];
  }

  sendFormData() {
    if (!this.file) return;
    this.loading = !this.loading;
    console.log(this.file);
    this.fileUploadService.upload('http://localhost:4321/fileupload', this.file).subscribe({
      next: (data: any) => {
        if (data[0]?.url) {
          this.fileURL = data[0].url;
          this.loading = false;
        }
      },
      error: err => this.loading = false
    }
    );
  }

  sendBinary() {
    if (!this.file) return;
    this.loading = !this.loading;
    console.log(this.file);
    const reader = new FileReader();
    reader.readAsArrayBuffer(this.file);
    reader.onload = () => {
      let url = 'http://localhost:8001/api/empleados/2/photo';
      this.http.put(url, reader.result, { headers: { 'Content-Type': this.file ? this.file.type : '*/*' }, responseType: 'blob' }).subscribe({
        next: (data: any) => {
          this.fileURL = url;
          this.blob = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(data));
          this.loading = false;
        },
        error: err => { this.loading = false; console.error(err); }
      });
    };
  }

}
