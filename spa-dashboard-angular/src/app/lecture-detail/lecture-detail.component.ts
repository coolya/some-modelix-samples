import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

import {LectureService} from '../lectures/lecture.service';
import {Lecture, LectureList} from "../Container";

@Component({
    selector: 'app-lecture-detail',
    templateUrl: './lecture-detail.component.html',
    styleUrls: ['./lecture-detail.component.css']
})
export class LectureDetailComponent implements OnInit {
    lecture: Lecture | undefined;

    constructor(
        private route: ActivatedRoute,
        private lectureService: LectureService,
        private location: Location) {
    }

    ngOnInit(): void {
        this.getLecture();
    }

    getLecture(): void {
        const lectureRef: string = this.route.snapshot.paramMap.get('lectureRef')!
        this.lectureService.getLetcture(lectureRef).subscribe( lecture => this.lecture = lecture);
        this.lectureService.getLectureUpdates().subscribe(data => {
            if (data instanceof LectureList) {
                const lectureFromList = data.lectures.find(item => item.lectureRef === lectureRef)
                if (lectureFromList === undefined) {
                    // lecture got deleted
                    this.lecture = undefined;
                } else {
                    // lecture (re-)appeared
                    this.lecture = lectureFromList;
                }
            } else if (data instanceof Lecture) {
                if (data.lectureRef === lectureRef) {
                    this.lecture = data;
                }
            }
        });
    }

    goBack(): void {
        this.location.back();
    }
}
