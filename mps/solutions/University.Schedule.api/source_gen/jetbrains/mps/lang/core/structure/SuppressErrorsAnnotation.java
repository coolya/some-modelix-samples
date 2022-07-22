package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.jetbrains.annotations.Nullable;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3A00000000-0000-4000-0000-011c89590288%28jetbrains.mps.lang.core.structure%29%2F4222318806802425298
 */
public class SuppressErrorsAnnotation extends NodeAttribute implements ISuppressErrors {

  public class Properties extends NodeAttribute.Properties implements INodeHolder, ISuppressErrors.Properties {

    @NotNull
    @Override
    public INode getINode() {
      return SuppressErrorsAnnotation.this.getINode();
    }
    @Nullable
    public String getFilter() {
      String propertyValue = getINode().getPropertyValue("filter");
      return propertyValue;
    }
    @Nullable
    public String setFilter(@Nullable String value) {
      getINode().setPropertyValue("filter", value);
      return value;
    }
    @Nullable
    public String getMessage() {
      String propertyValue = getINode().getPropertyValue("message");
      return propertyValue;
    }
    @Nullable
    public String setMessage(@Nullable String value) {
      getINode().setPropertyValue("message", value);
      return value;
    }
    @Nullable
    public String getComment() {
      String propertyValue = getINode().getPropertyValue("comment");
      return propertyValue;
    }
    @Nullable
    public String setComment(@Nullable String value) {
      getINode().setPropertyValue("comment", value);
      return value;
    }
  }
  public class Children extends NodeAttribute.Children implements INodeHolder, ISuppressErrors.Children {

    @NotNull
    @Override
    public INode getINode() {
      return SuppressErrorsAnnotation.this.getINode();
    }
  }
  public class References extends NodeAttribute.References implements INodeHolder, ISuppressErrors.References {

    @NotNull
    @Override
    public INode getINode() {
      return SuppressErrorsAnnotation.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public SuppressErrorsAnnotation(INode node) {
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