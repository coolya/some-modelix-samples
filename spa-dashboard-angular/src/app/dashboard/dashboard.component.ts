import {Component, OnInit} from '@angular/core';
import {Lecture, Room} from '../Container';
import {RoomService} from '../rooms/room.service';
import {LectureService} from "../lectures/lecture.service";

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    rooms: Room[] = [];
    lectures: Lecture[] = [];

    constructor(private roomService: RoomService,
                private lectureService: LectureService) {
    }

    ngOnInit(): void {
        this.getRooms();
        this.getLectures();
    }

    getRooms(): void {
        this.roomService.getRoomList().subscribe(rooms => this.rooms = rooms.rooms.slice(0, 3));
    }

    getLectures(): void {
        this.lectureService.getLectureList().subscribe(lecture => this.lectures = lecture.lectures.slice(0, 3));
    }
}
