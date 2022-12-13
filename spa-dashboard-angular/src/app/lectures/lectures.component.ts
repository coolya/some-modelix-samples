import {Component, OnInit} from '@angular/core';

import {LectureService} from './lecture.service';
import {Lecture, LectureList} from '../Container';

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
        this.populateLectures()
        this.lectureService.getLectureUpdates().subscribe(data => {
            console.log(data);
            if (data instanceof LectureList) {
                this.lectures = data.lectures;
            } else if (data instanceof Lecture) {
                const lectureIndex = this.lectures.findIndex(item => item.lectureRef === data.lectureRef)
                if (lectureIndex === -1) {
                    // We don't know about this lecture yet
                    this.lectures.push(data);
                } else {
                    this.lectures[lectureIndex] = data;
                }
            }
        });
    }

    populateLectures(): void {
        this.lectureService.getLectureList().subscribe((data) => {
            this.lectures = data.lectures
        })
    }
}
