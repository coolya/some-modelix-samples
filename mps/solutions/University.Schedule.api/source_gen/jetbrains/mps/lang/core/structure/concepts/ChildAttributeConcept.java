package jetbrains.mps.lang.core.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import jetbrains.mps.lang.core.structure.ChildAttribute;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;

public class ChildAttributeConcept extends AbstractConcept<ChildAttribute> {

  @NotNull
  @Override
  public ChildAttribute createInstance(@NotNull INode node) {
    return new ChildAttribute(node);
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final ChildAttributeConcept INSTANCE = new ChildAttributeConcept();
  private ChildAttributeConcept() {
    super(false, "jetbrains.mps.lang.core.structure.ChildAttribute", "ChildAttribute", "ceab5195-25ea-4f22-9b92-103b95ca8c0c/709746936026466394");
  }

  @Override
  protected void doInit() {
    this.properties.put("role_DebugInfo", new MPSProperty(this, "role_DebugInfo"));
    this.properties.put("linkId", new MPSProperty(this, "linkId"));
    this.superConcepts.add(AttributeConcept.INSTANCE);
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
