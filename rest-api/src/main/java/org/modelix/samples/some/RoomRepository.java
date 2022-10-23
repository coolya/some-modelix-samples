package org.modelix.samples.some;

import University.Schedule.structure.Room;
import University.Schedule.structure.Rooms;
import jakarta.inject.Singleton;
import jetbrains.mps.lang.core.structure.BaseConcept;
import org.modelix.model.api.INode;
import org.modelix.model.api.INodeReference;
import org.modelix.model.area.IArea;
import org.modelix.model.lazy.INodeReferenceSerializer;
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry;
import org.modelix.mps.rest.model.access.api.ModelView;

import javax.management.ConstructorParameters;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class RoomRepository {
	private final JavaInteropMPSRemoteClient client;
	private final List<String> modelsToLoad;

	public RoomRepository(JavaInteropMPSRemoteClient client, List<String> modelsToLoad) {
		this.client = client;
		this.modelsToLoad = modelsToLoad;
	}

	private Collection<IArea> loadModels() {
		try {
			List<ModelView> views = client.getViewModels().get(60, TimeUnit.SECONDS);
			List<String> modelIdsToLoad = views.stream().filter(x -> this.modelsToLoad.contains(x.getName())).map(ModelView::getModelId).collect(Collectors.toList());
			return client.loadModelAreas(modelIdsToLoad).get(60, TimeUnit.SECONDS).stream().map(x -> ((IArea) x)).collect(Collectors.toList());
		} catch (InterruptedException | TimeoutException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Room> getRooms() {
		Collection<IArea> areas = loadModels();
		return areas.stream()
				.map(IArea::getRoot)
				.flatMap(x -> StreamSupport.stream(x.getAllChildren().spliterator(), false))
//		        TODO: fix this - commented as getInstance broke with 2.0
// 				.map(MPSLanguageRegistry.Companion::<BaseConcept>getInstance)
				.filter(x -> x instanceof Rooms)
				.map(x -> (Rooms)x)
				.flatMap(x -> x.getChildren().getRooms().stream())
				.collect(Collectors.toList());
	}

	public Optional<Room> getRoomByRef(String ref) {
		var nodeReference = INodeReferenceSerializer.Companion.deserialize(ref);
		var iNode = client.resolveReference(nodeReference);
		if(iNode == null) {
			return Optional.empty();
		}
//		TODO: fix this - commented as getInstance broke with 2.0
//		var bc = MPSLanguageRegistry.Companion.<BaseConcept>getInstance(iNode);
//		if(bc instanceof Room) {
//			return Optional.of(((Room) bc));
//		}
		return Optional.empty();
	}
}
