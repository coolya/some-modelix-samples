import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {DashboardComponent} from './dashboard/dashboard.component';

import {RoomsComponent} from './rooms/rooms.component';
import {RoomDetailComponent} from './room-detail/room-detail.component';

import {LecturesComponent} from './lectures/lectures.component';
import {LectureDetailComponent} from './lecture-detail/lecture-detail.component';

import {SearchComponent} from './search/search.component';
import {MessagesComponent} from './messages/messages.component';


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
    ],

    declarations: [
        AppComponent,
        MessagesComponent,

        DashboardComponent,
        SearchComponent,

        RoomsComponent,
        RoomDetailComponent,

        LecturesComponent,
        LectureDetailComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
