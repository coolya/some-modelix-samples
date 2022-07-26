package org.modelix.samples.some.api;

public class RoomDto {
	private final String id;
	private final String name;
	private final Integer maxPeople;

	public RoomDto(String id,String name, Integer maxPeople) {
		this.id = id;
		this.name = name;
		this.maxPeople = maxPeople;
	}

	public Integer getMaxPeople() {
		return maxPeople;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}
