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

  constructor(private roomService: RoomService) {}

  ngOnInit(): void {
    this.getRooms()
  }

  getRooms(): void {
    this.roomService.getRoomList().subscribe((data) => {
      // console.log(data)
      this.rooms = data.rooms
    })
  }

  // getRooms(): void {
  //   this.roomService.getRooms()
  //     .subscribe(rooms => this.rooms = rooms);
  // }
  //
  // add(name: string): void {
  //   name = name.trim();
  //   if (!name) { return; }
  //   this.roomService.addHero({ name } as Room)
  //     .subscribe(hero => {
  //       this.rooms.push(hero);
  //     });
  // }
  //
  // delete(hero: Room): void {
  //   this.rooms = this.rooms.filter(h => h !== hero);
  //   this.roomService.deleteHero(hero.id).subscribe();
  // }
}
