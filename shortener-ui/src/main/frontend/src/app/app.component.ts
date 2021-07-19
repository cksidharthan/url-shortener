import { Component } from '@angular/core';
import {AppService} from './app.service';
import {FormBuilder} from '@angular/forms';
import {Url} from './objects/url';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'shortener';

  constructor(public appService: AppService, private formBuilder: FormBuilder){}

  url: Url;
  value = '';
  shortUrl = '';
  shortUrlFlag = false;

  // tslint:disable-next-line:typedef
  shortenUrl() {
    const url = new Url(0, this.value, '');
    this.shortUrlFlag = true;
    this.appService.shortenUrl(url).subscribe(
      (data: any) => {
        this.shortUrl = 'http://localhost:8090/url/' + data.responsePayload.shortUrlString;
      }
    );
  }

}
