import {Component, OnInit} from '@angular/core';
import {Lecture, LectureList, Room, RoomList} from '../Container';
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

    private setRooms(rooms: Room[]) {
        this.rooms = rooms.slice(0, 3);
    }

    private setLectures(lectures: Lecture[]) {
        this.lectures = lectures.slice(0, 3);
    }

    getRooms(): void {
        this.roomService.getRoomList().subscribe(rooms => this.setRooms(rooms.rooms));
        this.roomService.getRoomUpdates().subscribe(data => {
            if (data instanceof RoomList) {
                this.setRooms(data.rooms);
            } else if (data instanceof Room) {
                const roomIndex = this.rooms.findIndex(item => item.roomRef === data.roomRef)
                if (roomIndex !== -1) {
                    this.rooms[roomIndex] = data;
                }
            }
        });
    }

    getLectures(): void {
        this.lectureService.getLectureList().subscribe(lectures => this.setLectures(lectures.lectures));
        this.lectureService.getLectureUpdates().subscribe(data => {
            if (data instanceof LectureList) {
                this.setLectures(data.lectures);
            } else if (data instanceof Lecture) {
                const lectureIndex = this.lectures.findIndex(item => item.lectureRef === data.lectureRef)
                if (lectureIndex !== -1) {
                    this.lectures[lectureIndex] = data;
                }
            }
        });
    }
}
