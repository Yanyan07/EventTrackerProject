import { Component, OnInit } from '@angular/core';
import { Hiker } from 'src/app/models/hiker';
import { SingleHiking } from 'src/app/models/single-hiking';
import { Trail } from 'src/app/models/trail';
import { HikeService } from 'src/app/services/hike.service';

@Component({
  selector: 'app-hike-list',
  templateUrl: './hike-list.component.html',
  styleUrls: ['./hike-list.component.css']
})
export class HikeListComponent implements OnInit {

  hikers: Hiker[] = [];
  hikeList: SingleHiking[] = [];
  editHike: SingleHiking | null = null;
  newHike = new SingleHiking(0,0,'',new Hiker(), new Trail());
  distance: number = 0;
  distanceHiker = new Hiker();
  favoriteTrail = new Trail();
  favoriteHiker = new Hiker();

  constructor(
    private hikeService: HikeService
  ) { }

  ngOnInit(): void {
    this.loadHikers();
  }

  loadHikers(){
    this.hikeService.index().subscribe(
      data => {
        this.hikers = data;
      },
      fail => {
        console.log('HikerListComponent.reloadHikers(): error getting hikers list');
        console.error(fail);
      }
    );
  }

  hikeDetails(hiker: Hiker){
    this.hikeService.show(hiker).subscribe(
      hikeList => {
        this.hikeList = hikeList;
        this.favoriteTrail = new Trail();
        this.distance = 0;
      },
      fail => {
        console.log('HikeListComponent.hikeDetails(): error getting hike list');
        console.error(fail);
      }
    );
  }

  updateHike(hike: SingleHiking){
    this.hikeService.update(hike).subscribe(
      (updated) => {
        this.hikeDetails(hike.hiker);
      },
      (fail) => {
        console.error('HikeListComponent.update(): Error updating Hike');
        console.error(fail);
      }
    );
  }

  setEditHike(hike: SingleHiking){
    this.editHike = Object.assign({}, hike);
  }

  addHike(newHike: SingleHiking){
    this.hikeService.create(newHike).subscribe(
      hike => {
        this.hikeDetails(hike.hiker);
        this.newHike = new SingleHiking(0,0,'',new Hiker(), new Trail());
      },
      fail => {
        console.error('HikeListComponent.addHike(): Error creating Hike');
        console.error(fail);
      }
    );
  }

  deleteHike(hike: SingleHiking){
    this.hikeService.destroy(hike).subscribe(
      good => {
        this.hikeDetails(hike.hiker);
      },
      evil => {
        console.error('HikeListComponent.destroy(): Error destroying Hike');
        console.error(evil);
      }
    );
    this.hikeDetails(hike.hiker);
  }

  getTotalDistance(hiker: Hiker){
    this.hikeService.totalDistance(hiker).subscribe(
      dist => {
        this.distance = dist;
        this.distanceHiker = hiker;
        this.hikeList = [];
        this.favoriteTrail = new Trail();
      },
      fail => {
        console.error('HikeListComponent.distance(): Error getting Hike distance');
        console.error(fail);
      }
    );
  }

  getFavoriteTrail(hiker: Hiker){
    this.hikeService.favoriteTrail(hiker).subscribe(
      fav => {
        this.favoriteTrail = fav;
        this.favoriteHiker = hiker;
        this.hikeList = [];
        this.distance = 0;
      },
      fail => {
        console.error('HikeListComponent.getFavoriteTrail(): Error getting favorite Trail');
        console.error(fail);
      }
    );
  }

}
