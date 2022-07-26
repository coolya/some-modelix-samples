package org.modelix.samples.some;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.modelix.model.lazy.INodeReferenceSerializer;
import org.modelix.samples.some.api.RoomDto;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/rooms")
public class RoomsController {
	private final RoomRepository repo;

	public RoomsController(RoomRepository repo) {
		this.repo = repo;
	}

	@Get(uri = "/", processes = MediaType.APPLICATION_JSON)
	public HttpResponse<List<RoomDto>> get() {
		var data = repo.getRooms().stream().map(it -> new RoomDto(
				INodeReferenceSerializer.Companion.serialize(it.getINode().getReference()),
				it.getProperties().getName(),
				it.getProperties().getMaxPlaces()));
		return HttpResponse.ok(data.collect(Collectors.toList()));
	}

	@Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
	public HttpResponse<RoomDto> getById(String id) {
		var room = repo.getRoomByRef(id);
		if (room.isEmpty()) {
			return HttpResponse.notFound();
		}

		return room.map(it ->
				HttpResponse.ok(new RoomDto(
						INodeReferenceSerializer.Companion.serialize(it.getINode().getReference()),
						it.getProperties().getName(),
						it.getProperties().getMaxPlaces())))
				.orElse(HttpResponse.notFound());
	}
}
