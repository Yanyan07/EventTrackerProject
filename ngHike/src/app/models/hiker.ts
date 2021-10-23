export class Hiker {
  id: number;
  name: string;
  gender: string

  constructor(
    id:number=0,
    name:string='',
    gender:string=''){

    this.id = id;
    this.name = name;
    this.gender = gender;
  }
}
