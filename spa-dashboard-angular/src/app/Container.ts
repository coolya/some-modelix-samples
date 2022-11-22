export class URLLibrary {
    public static API_URL_UPDATES = 'ws://127.0.0.1:8090/updates'
    public static API_URL_ROOMS = 'http://127.0.0.1:8090/rooms';
    public static API_URL_LECTURES = 'http://127.0.0.1:8090/lectures';
}

export class Room {
    roomRef!: string
    name!: string
    maxPlaces!: number
    hasRemoteEquipment: boolean = false
}

export class RoomList {
    rooms!: Room[];
}

export class Lecture {
    lectureRef!: string
    name!: string
    description!: string
    maxParticipants!: number
    room!: string
}

export class LectureList {
    lectures!: Lecture[];
}
