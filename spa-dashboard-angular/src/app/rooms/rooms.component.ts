import {Component, OnInit} from '@angular/core';

import {RoomService} from './room.service';
import {Room, RoomList} from '../Container';

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
        this.populateRooms()
        this.roomService.getRoomUpdates().subscribe(data => {
            if (data instanceof RoomList) {
                this.rooms = data.rooms;
            } else if (data instanceof Room) {
                for (let index = 0; index < this.rooms.length; index++) {
                    if (this.rooms[index].roomRef === data.roomRef) {
                        this.rooms[index] = data;
                        break;
                    }
                }
            }
        });
    }

    populateRooms(): void {
        this.roomService.getRoomList().subscribe((data) => {
            this.rooms = data.rooms
        })
    }
}
