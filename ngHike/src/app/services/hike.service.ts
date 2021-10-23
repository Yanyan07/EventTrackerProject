import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Hiker } from '../models/hiker';
import { catchError } from 'rxjs/operators';
import { SingleHiking } from '../models/single-hiking';
import { Trail } from '../models/trail';

@Injectable({
  providedIn: 'root'
})
export class HikeService {
  private baseUrl = 'http://localhost:8087/';
  private url = this.baseUrl + 'api/hikers';
  constructor(
    private http: HttpClient
  ) { }


  index(): Observable<Hiker[]> {
    return this.http.get<Hiker[]>(this.url).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('HikeService.index(): Error retrieving todo list');
      })
    );
  }
  // 'api/hikings/
  show(hiker: Hiker): Observable<SingleHiking[]> {
    return this.http.get<SingleHiking[]>(this.baseUrl+'api/hikings/'+hiker.id).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('HikeService.show(): Error retrieving singleHiking list');
      })
    );
  }

  //"hikings/{hikerId}/{trailId}/hiking"
  create(hike: SingleHiking) : Observable<SingleHiking>{
    return this.http.post<SingleHiking>(this.baseUrl+'api/hikings/'+hike.hiker.id+'/'+hike.trail.id+'/hiking', hike).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('HikeService.create(): Error creating todo');
      })
    );
  }

//"hikings/{hikerId}/{trailId}/{hikingId}/hiking"
  update(hike: SingleHiking): Observable<SingleHiking>{
    return this.http.put<SingleHiking>(this.baseUrl+'api/hikings/'+hike.hiker.id+'/'+hike.trail.id+'/'+hike.id+'/hiking', hike).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('HikeService.update(): Error updating todo');
      })
    );
  }

  //hikings/{hikerId}/{hikingId}/hiking
  destroy(hike: SingleHiking){
    return this.http.delete(this.baseUrl+'api/hikings/'+hike.hiker.id+'/'+hike.id+'/hiking').pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('HikeService.destroy(): Error destroying todo');
      })
    );
  }

  //hikers/distance/{hikerId}
  totalDistance(hiker: Hiker): Observable<number>{
    return this.http.get<number>(this.url+'/distance/'+hiker.id).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('HikeService.totalDistance(): Error getting distance');
      })
    );
  }

  //"hikers/favorite/{hikerId}"
  favoriteTrail(hiker: Hiker): Observable<Trail>{
    return this.http.get<Trail>(this.url+'/favorite/'+hiker.id).pipe(
      catchError((err : any) => {
        console.error(err);
        return throwError('HikeService.favoriteTrail(): Error getting favorite Trail');
      })
    );
  }


}
