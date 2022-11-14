import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import {filter, Observable, of, toArray} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import {Lecture, LectureList} from '../Container';
import { MessageService } from '../message.service';


@Injectable({ providedIn: 'root' })
export class LectureService {

  private apiURL = 'http://localhost:4010/lectures';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET lectures from the server */
  getLectures(): Observable<Lecture[]> {
    return this.http.get<Lecture[]>(this.apiURL)
      .pipe(
        tap(_ => this.log('fetched lectures')),
        catchError(this.handleError<Lecture[]>('getLectures', []))
      );
  }

  getLectureList(): Observable<LectureList> {
    return this.http.get<LectureList>("http://localhost:4010/lectures")
      .pipe(
      tap(_ => this.log('fetched lectures')),
      catchError(this.handleError<LectureList>('getLectureList', new LectureList()))
    );
  }

    /** GET lecture by lectureRef. Return `undefined` when lectureRef not found */
  getLecture404<Data>(lectureRef: string): Observable<Lecture> {
    const url = `${this.apiURL}/?lectureRef=${lectureRef}`;
    return this.http.get<Lecture[]>(url)
      .pipe(
        map(rooms => rooms[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} room lectureRef=${lectureRef}`);
        }),
        catchError(this.handleError<Lecture>(`getLecture lectureRef=${lectureRef}`))
      );
  }

  /** GET lecture by id. Will 404 if id not found */
  getLetcture(lectureRef: string): Observable<Lecture> {
    const url = `${this.apiURL}/${lectureRef}`;
    return this.http.get<Lecture>(url).pipe(
      tap(_ => this.log(`fetched lecture lectureRef=${lectureRef}`)),
      catchError(this.handleError<Lecture>(`getLecture lectureRef=${lectureRef}`))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a LectureService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`LectureService: ${message}`);
  }
}
