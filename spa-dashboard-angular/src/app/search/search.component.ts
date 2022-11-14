import {Component, OnInit} from '@angular/core';

import {Observable, Subject} from 'rxjs';

import {
    debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';


import {RoomService} from '../rooms/room.service';
import {Lecture, Room, RoomList} from "../Container";
import {LectureService} from "../lectures/lecture.service";

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
    rooms: Room[] = [];
    lectures: Lecture[] = [];

    filterBy!: string;
    filteredRooms: Room[] = [];
    filteredLectures: Lecture[] = [];

    private searchTerms = new Subject<string>();

    constructor(private roomService: RoomService,
                private lectureService: LectureService) {
    }

    // Push a search term into the observable stream.
    search(term: string): void {
        this.searchTerms.next(term);
    }

    ngOnInit(): void {

        this.roomService.getRoomList().subscribe(rooms => {
                this.rooms = rooms.rooms;
                this.filteredRooms = [...this.rooms];
            }
        );
        this.lectureService.getLectureList().subscribe(lecture => {
                this.lectures = lecture.lectures;
                this.filteredLectures = [...this.lectures]
            }
        );

        // this.rooms = this.searchTerms.pipe(
        //   // wait 300ms after each keystroke before considering the term
        //   debounceTime(300),
        //
        //   // ignore new term if same as previous term
        //   distinctUntilChanged(),
        //
        //   // switch to new search observable each time the term changes
        //   switchMap((term: string) =>
        //       this.roomService.searchRooms(term)),
        // );
        // this.lectures = this.searchTerms.pipe(
        //   // wait 300ms after each keystroke before considering the term
        //   debounceTime(300),
        //
        //   // ignore new term if same as previous term
        //   distinctUntilChanged(),
        //
        //   // switch to new search observable each time the term changes
        // //   switchMap((term: string) =>
        // //       this.lectureService.searchLectures(term)),
        //     // );
        //
        //       switchMap((term: string) =>
        //     this.lectureService.searchLectures(term)),
        // );
    }

    filter() {
        this.filteredRooms = [...this.rooms.filter(room => room.name.includes(this.filterBy))];
        this.filteredLectures = [...this.lectures.filter(lecture => lecture.name.includes(this.filterBy))];
    }
}
