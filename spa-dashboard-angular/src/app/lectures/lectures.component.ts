import {Component, OnInit} from '@angular/core';

import {LectureService} from './lecture.service';
import {Lecture} from '../Container';

@Component({
    selector: 'app-lectures',
    templateUrl: './lectures.component.html',
    styleUrls: ['./lectures.component.css']
})
export class LecturesComponent implements OnInit {
    lectures: Lecture[] = [];

    constructor(private lectureService: LectureService) {
    }

    ngOnInit(): void {
        this.getLectures()
    }

    getLectures(): void {
        this.lectureService.getLectureList().subscribe((data) => {
            this.lectures = data.lectures
        })
    }
}
