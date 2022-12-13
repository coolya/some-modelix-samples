import { Injectable, OnInit } from "@angular/core";
import { Observable, Observer, Subject } from "rxjs";
import { URLLibrary } from "./Container";

@Injectable({providedIn: 'root'})
export class UpdateService {
    public updateSubject: Subject<MessageEvent>;

    constructor() {
        this.updateSubject = this.connectWebSocket(URLLibrary.API_URL_UPDATES);
    }

    connectWebSocket(url: string): Subject<MessageEvent> {
        const ws = new WebSocket(url);
        const observable =  new Observable((obs: Observer<MessageEvent>) => {
            ws.onmessage = obs.next.bind(obs);
            ws.onerror = obs.error.bind(obs);
            ws.onclose = obs.complete.bind(obs);
            return ws.close.bind(ws); 
        });

        const subject: Subject<MessageEvent> = new Subject();
        observable.subscribe(subject);

        return subject;
    }

}
