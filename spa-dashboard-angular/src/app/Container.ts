export class URLLibrary {
    public static API_URL_ROOMS = 'http://0.0.0.0:8080/rooms';
    public static API_URL_LECTURES = 'http://localhost:8080/lectures';
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



