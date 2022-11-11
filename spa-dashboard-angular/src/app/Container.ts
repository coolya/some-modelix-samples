export class Room {
  roomRef!: string
  name!: string
  maxPlaces!: number
  hasRemoteEquipment!: boolean
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



