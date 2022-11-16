import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

import {Lecture, LectureList, URLLibrary} from '../Container';
import {MessageService} from '../message.service';


@Injectable({providedIn: 'root'})
export class LectureService {

    constructor(
        private http: HttpClient,
        private messageService: MessageService) {
    }

    getLectureList(): Observable<LectureList> {
        return this.http.get<LectureList>(URLLibrary.API_URL_LECTURES)
            .pipe(
                tap(_ => this.log('fetched lectures')),
                catchError(this.handleError<LectureList>('getLectureList', new LectureList()))
            );
    }

    /** GET lecture by id. Will 404 if id not found */
    getLetcture(lectureRef: string): Observable<Lecture> {
        const url = `${URLLibrary.API_URL_LECTURES}/${lectureRef}`;
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

            console.error(error); // log to console
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
