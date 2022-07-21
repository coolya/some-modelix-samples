package University.Schedule.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import University.Schedule.structure.Lecture;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;
import jetbrains.mps.lang.core.structure.concepts.BaseConceptConcept;
import jetbrains.mps.lang.core.structure.concepts.INamedConceptConcept;

public class LectureConcept extends AbstractConcept<Lecture> {

  @NotNull
  @Override
  public Lecture createInstance(@NotNull INode node) {
    return new Lecture(node);
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final LectureConcept INSTANCE = new LectureConcept();
  private LectureConcept() {
    super(false, "University.Schedule.structure.Lecture", "Lecture", "96533389-8d4c-46f2-b150-8d89155f7fca/4128798754188010560");
  }

  @Override
  protected void doInit() {
    this.properties.put("description", new MPSProperty(this, "description"));
    this.properties.put("maxParticipants", new MPSProperty(this, "maxParticipants"));
    this.children.put("schedule", new MPSChildLink(this, ScheduleConcept.INSTANCE, false, false, "schedule"));
    this.references.put("", new MPSReferenceLink(this, RoomConcept.INSTANCE, false, "room"));
    this.superConcepts.add(BaseConceptConcept.INSTANCE);
    this.superConcepts.add(INamedConceptConcept.INSTANCE);
  }
  @NotNull
  @Override
  protected HashMap<String, MPSProperty> myProperties() {
    return properties;
  }
  @NotNull
  @Override
  protected HashMap<String, MPSChildLink> myChildLinks() {
    return children;
  }
  @NotNull
  @Override
  protected HashMap<String, MPSReferenceLink> myReferenceLinks() {
    return references;
  }
  @NotNull
  @Override
  protected List<AbstractConcept<?>> mySuperConcepts() {
    return superConcepts;
  }
}
