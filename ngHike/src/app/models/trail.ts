export class Trail {
  id: number;
  name: string;
  length: number;
  imageUrl: string

  constructor(
    id: number = 0,
    name: string = '',
    length: number = 0,
    imageUrl: string = ''
  ){
    this.id = id;
    this.name = name;
    this.length = length;
    this.imageUrl = imageUrl;
  }
}
