import { Hiker } from "./hiker";
import { Trail } from "./trail";

export class SingleHiking {
  id: number;
  distance: number;
  hikingDate: string;
  hiker: Hiker;
  trail: Trail

  constructor(
    id: number=0,
    distance: number=0,
    hikingDate: string='',
    hiker: Hiker,
    trail: Trail
  ){
    this.id = id;
    this.distance = distance;
    this.hikingDate = hikingDate;
    this.hiker = hiker;
    this.trail = trail;
  }
}
