package jetbrains.mps.lang.core.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import jetbrains.mps.lang.core.structure.IStubForAnotherConcept;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;

public class IStubForAnotherConceptConcept extends AbstractConcept<IStubForAnotherConcept> {

  @NotNull
  @Override
  public IStubForAnotherConcept createInstance(@NotNull INode node) {
    throw new UnsupportedOperationException("concept is abstract!");
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final IStubForAnotherConceptConcept INSTANCE = new IStubForAnotherConceptConcept();
  private IStubForAnotherConceptConcept() {
    super(true, "jetbrains.mps.lang.core.structure.IStubForAnotherConcept", "IStubForAnotherConcept", "ceab5195-25ea-4f22-9b92-103b95ca8c0c/155087542027447621");
  }

  @Override
  protected void doInit() {
    this.superConcepts.add(InterfacePartConcept.INSTANCE);
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