import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

import {RoomService} from '../rooms/room.service';
import {Room} from "../Container";

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
        this.roomService.getRoom(roomRef).subscribe(
            room => this.room = room);
    }

    goBack(): void {
        this.location.back();
    }
}
