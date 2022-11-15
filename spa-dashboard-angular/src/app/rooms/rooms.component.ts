import {Component, OnInit} from '@angular/core';

import {RoomService} from './room.service';
import {Room} from '../Container';

@Component({
    selector: 'app-rooms',
    templateUrl: './rooms.component.html',
    styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {
    rooms: Room[] = [];

    constructor(private roomService: RoomService) {
    }

    ngOnInit(): void {
        this.getRooms()
    }

    getRooms(): void {
        this.roomService.getRoomList().subscribe((data) => {
            this.rooms = data.rooms
        })
    }
}
