package jetbrains.mps.lang.core.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import jetbrains.mps.lang.core.structure.ReviewMigration;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;

public class ReviewMigrationConcept extends AbstractConcept<ReviewMigration> {

  @NotNull
  @Override
  public ReviewMigration createInstance(@NotNull INode node) {
    return new ReviewMigration(node);
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final ReviewMigrationConcept INSTANCE = new ReviewMigrationConcept();
  private ReviewMigrationConcept() {
    super(false, "jetbrains.mps.lang.core.structure.ReviewMigration", "ReviewMigration", "ceab5195-25ea-4f22-9b92-103b95ca8c0c/8703179436979359238");
  }

  @Override
  protected void doInit() {
    this.properties.put("reasonShort", new MPSProperty(this, "reasonShort"));
    this.properties.put("todo", new MPSProperty(this, "todo"));
    this.properties.put("readableId", new MPSProperty(this, "readableId"));
    this.superConcepts.add(NodeAttributeConcept.INSTANCE);
    this.superConcepts.add(MigrationAnnotationConcept.INSTANCE);
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