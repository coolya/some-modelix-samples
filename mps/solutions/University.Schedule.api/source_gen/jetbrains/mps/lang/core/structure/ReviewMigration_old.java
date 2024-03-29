package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.jetbrains.annotations.Nullable;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3A00000000-0000-4000-0000-011c89590288%28jetbrains.mps.lang.core.structure%29%2F2482611074346661065
 */
public class ReviewMigration_old extends NodeAttribute implements MigrationAnnotation_old {

  public class Properties extends NodeAttribute.Properties implements INodeHolder, MigrationAnnotation_old.Properties {

    @NotNull
    @Override
    public INode getINode() {
      return ReviewMigration_old.this.getINode();
    }
    @Nullable
    public String getReasonShort() {
      String propertyValue = getINode().getPropertyValue("reasonShort");
      return propertyValue;
    }
    @Nullable
    public String setReasonShort(@Nullable String value) {
      getINode().setPropertyValue("reasonShort", value);
      return value;
    }
    @Nullable
    public String getTodo() {
      String propertyValue = getINode().getPropertyValue("todo");
      return propertyValue;
    }
    @Nullable
    public String setTodo(@Nullable String value) {
      getINode().setPropertyValue("todo", value);
      return value;
    }
    @Nullable
    public String getReadableId() {
      String propertyValue = getINode().getPropertyValue("readableId");
      return propertyValue;
    }
    @Nullable
    public String setReadableId(@Nullable String value) {
      getINode().setPropertyValue("readableId", value);
      return value;
    }
  }
  public class Children extends NodeAttribute.Children implements INodeHolder, MigrationAnnotation_old.Children {

    @NotNull
    @Override
    public INode getINode() {
      return ReviewMigration_old.this.getINode();
    }
  }
  public class References extends NodeAttribute.References implements INodeHolder, MigrationAnnotation_old.References {

    @NotNull
    @Override
    public INode getINode() {
      return ReviewMigration_old.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public ReviewMigration_old(INode node) {
    super(node);
    this.properties = new Properties();
    this.children = new Children();
    this.references = new References();
  }
  public Properties getProperties() {
    return this.properties;
  }
  public Children getChildren() {
    return this.children;
  }
  public References getReferences() {
    return this.references;
  }
}
