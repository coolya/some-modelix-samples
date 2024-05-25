package University.Schedule.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import University.Schedule.structure.Student;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;
import jetbrains.mps.lang.core.structure.concepts.BaseConceptConcept;

public class StudentConcept extends AbstractConcept<Student> {

  @NotNull
  @Override
  public Student createInstance(@NotNull INode node) {
    return new Student(node);
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final StudentConcept INSTANCE = new StudentConcept();
  private StudentConcept() {
    super(false, "University.Schedule.structure.Student", "Student", "96533389-8d4c-46f2-b150-8d89155f7fca/1648392019017048449");
  }

  @Override
  protected void doInit() {
    this.properties.put("name", new MPSProperty(this, "name"));
    this.properties.put("semester", new MPSProperty(this, "semester"));
    this.children.put("born", new MPSChildLink(this, DateAndTimeConcept.INSTANCE, false, false, "born"));
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