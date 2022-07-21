package jetbrains.mps.lang.core.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import jetbrains.mps.lang.core.structure.ImplementationPart;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;

public class ImplementationPartConcept extends AbstractConcept<ImplementationPart> {

  @NotNull
  @Override
  public ImplementationPart createInstance(@NotNull INode node) {
    throw new UnsupportedOperationException("concept is abstract!");
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final ImplementationPartConcept INSTANCE = new ImplementationPartConcept();
  private ImplementationPartConcept() {
    super(true, "jetbrains.mps.lang.core.structure.ImplementationPart", "ImplementationPart", "ceab5195-25ea-4f22-9b92-103b95ca8c0c/1319728274783077719");
  }

  @Override
  protected void doInit() {
    this.superConcepts.add(ScopeFacadeConcept.INSTANCE);
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
