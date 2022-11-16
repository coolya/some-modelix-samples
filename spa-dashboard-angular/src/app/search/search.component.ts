import {Component, OnInit} from '@angular/core';

import {Subject} from 'rxjs';


import {RoomService} from '../rooms/room.service';
import {Lecture, Room} from "../Container";
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
    }

    filter() {
        this.filteredRooms = [...this.rooms.filter(room => room.name.includes(this.filterBy))];
        this.filteredLectures = [...this.lectures.filter(lecture => lecture.name.includes(this.filterBy))];
    }
}
