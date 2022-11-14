import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { SearchComponent } from "./search/search.component";

import { RoomsComponent } from './rooms/rooms.component';
import { RoomDetailComponent } from './room-detail/room-detail.component';

import { LecturesComponent } from './lectures/lectures.component';
import { LectureDetailComponent } from "./lecture-detail/lecture-detail.component";

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'search', component: SearchComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'rooms/:id', component: RoomDetailComponent },
  { path: 'rooms', component: RoomsComponent },
  { path: 'lectures', component: LecturesComponent },
  { path: 'lectures/:id', component: LectureDetailComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
