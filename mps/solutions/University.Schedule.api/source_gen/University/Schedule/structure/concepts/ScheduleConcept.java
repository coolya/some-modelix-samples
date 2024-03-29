package University.Schedule.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import University.Schedule.structure.Schedule;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;
import jetbrains.mps.lang.core.structure.concepts.BaseConceptConcept;

public class ScheduleConcept extends AbstractConcept<Schedule> {

  @NotNull
  @Override
  public Schedule createInstance(@NotNull INode node) {
    return new Schedule(node);
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final ScheduleConcept INSTANCE = new ScheduleConcept();
  private ScheduleConcept() {
    super(true, "University.Schedule.structure.Schedule", "Schedule", "96533389-8d4c-46f2-b150-8d89155f7fca/4128798754188010568");
  }

  @Override
  protected void doInit() {
    this.children.put("at", new MPSChildLink(this, DateAndTimeConcept.INSTANCE, false, false, "at"));
    this.superConcepts.add(BaseConceptConcept.INSTANCE);
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
