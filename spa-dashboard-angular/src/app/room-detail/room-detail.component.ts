import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

import {RoomService} from '../rooms/room.service';
import {Room, RoomList} from "../Container";

@Component({
    selector: 'app-room-detail',
    templateUrl: './room-detail.component.html',
    styleUrls: ['./room-detail.component.css']
})
export class RoomDetailComponent implements OnInit {
    room: Room | undefined;

    constructor(
        private route: ActivatedRoute,
        private roomService: RoomService,
        private location: Location) {
    }

    ngOnInit(): void {
        this.getRoom();
    }

    getRoom(): void {
        const roomRef: string = this.route.snapshot.paramMap.get('roomRef')!
        this.roomService.getRoom(roomRef).subscribe(room => this.room = room);
        this.roomService.getRoomUpdates().subscribe(data => {
            if (data instanceof RoomList) {
                const roomFromList = data.rooms.find(item => item.roomRef === roomRef)
                if (roomFromList === undefined) {
                    // Room got deleted
                    this.room = undefined;
                } else {
                    // Room (re-)appeared
                    this.room = roomFromList;
                }
            } else if (data instanceof Room) {
                if (data.roomRef === roomRef) {
                    this.room = data;
                }
            }
        });
    }

    goBack(): void {
        this.location.back();
    }
}
