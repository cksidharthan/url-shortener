export class Url {
  id: number;
  fullUrl: string;
  shortUrlString: string;

  constructor(
    id: number,
    fullUrl: string,
    shortUrlString: string
  ) {
    this.fullUrl = fullUrl;
    this.shortUrlString = shortUrlString;
    this.id = id;
  }

}
