import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Url} from './objects/url';
import * as url from 'url';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef no-shadowed-variable
  public shortenUrl(urlObject: url) {
    return this.http.post<Url>('http://localhost:8090/url/', urlObject);
  }
}
